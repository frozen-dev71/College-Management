package dtbase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.BatchException;
import style.Style;
import model.Batch;
import model.Report;
import utility.DBUtil;
public class BatchDbImpl implements BatchDb {
    // Add New Batch into Database
    @Override
    public String addBatch(Batch batch) throws BatchException {

        String message = Style.RED+"Data Not Inserted..."+Style.RESET;

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps = conn .prepareStatement("select courseName from Course where courseId = ?");

            ps.setInt(1, batch.getCourseId());

            ResultSet rs = ps.executeQuery();

            String cName = "";
            if(rs.next()) {
                cName = rs.getString("courseName");

            }else {
                throw new BatchException(Style.RED_BACKGROUND+"Course Id does not exist."+Style.RESET);
            }


            PreparedStatement ps1 = conn .prepareStatement("select count(courseId) from Batch where courseId = ?");

            ps1.setInt(1, batch.getCourseId());

            ResultSet rs1 = ps1.executeQuery();


            int count = 0;
            if(rs1.next()) {
                count = rs1.getInt(1);
            }else {
                throw new BatchException(Style.RED_BACKGROUND+"Course Id does not exist."+Style.RESET);
            }

            count++;
            String text = String.format("%03d", count);

            String batchId = cName + text;

            PreparedStatement ps2 = conn .prepareStatement("insert into Batch(batchId, courseId, noOfStudents, batchstartDate, duration) values(?,?,?,?,?)");

            ps2.setString(1, batch.getBatchId());
            ps2.setInt(2, batch.getCourseId());
            ps2.setInt(3,batch.getNoOfStudents());
            ps2.setString(4, batch.getBatchstartDate());
            ps2.setString(5, batch.getDuration());

            int x = ps2.executeUpdate();

            if(x>0) {
                message = Style.GREEN+"New Course Added Successfully.."+Style.RESET;

            }else {
                throw new BatchException(Style.RED_BACKGROUND+"Duplicate Entry"+Style.RESET);

            }

        }catch(SQLException e) {

            throw new BatchException(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);

        }

        return message;
    }



    // Search Batch With id
    @Override
    public Batch searchBatchById(String id) throws BatchException {

        Batch batch = null;

        try(Connection conn = DBUtil.provideConnection()){


            PreparedStatement ps = conn .prepareStatement("Select b.batchId, b.courseId, b.facultyId, f.facultyFname, b.noOfStudents, b.batchstartDate, b.duration from Batch b, Faculty f where b.facultyID = f.facultyID and b.batchId = ?");

            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                String bid = rs.getString("batchId");
                int cid = rs.getInt("courseId");
                int fid = rs.getInt("facultyId");
                String fName = rs.getString("facultyFname");
                int nos = rs.getInt("noOfStudents");
                Date date = rs.getDate("batchstartDate");
                String dur = rs.getString("duration");

                String sDate = date.toString();

                batch = new Batch(bid,cid,fid,fName,nos,sDate,dur);

            }else
                throw new BatchException(Style.RED_BACKGROUND+"Batch does not exist with this id "+ id + "."+Style.RESET);

        }catch(SQLException e) {

            throw new BatchException(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);

        }


        return batch;
    }



    // Search Batch With Name
    @Override
    public List<Batch> searchBatchByName(String name) throws BatchException {

        List<Batch> batches = new ArrayList<>();

        try(Connection conn = DBUtil.provideConnection()){


            PreparedStatement ps = conn .prepareStatement("Select b.batchId, b.courseId, b.facultyId, f.facultyFname, b.noOfStudents, b.batchstartDate, b.duration from Batch b, Faculty f where b.courseId = ("
                    + "Select courseId from course where courseName = ?) and b.facultyID = f.facultyID");

            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                String bid = rs.getString("batchId");
                int cid = rs.getInt("courseId");
                int fid = rs.getInt("facultyId");
                String fName = rs.getString("facultyFname");
                int nos = rs.getInt("noOfStudents");
                Date date = rs.getDate("batchstartDate");
                String dur = rs.getString("duration");

                String sDate = date.toString();

                Batch batch = new Batch(bid,cid,fid,fName,nos,sDate,dur);


                batches.add(batch);
            }
            if(batches.size() == 0)
                throw new BatchException(Style.RED_BACKGROUND+"Batch does not exist with this name "+ name + "."+Style.RESET);

        }catch(SQLException e) {

            throw new BatchException(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);

        }

        return batches;
    }



    // See All Batch Details Present in Database
    @Override
    public List<Batch> allBatch() throws BatchException {

        List<Batch> batches = new ArrayList<>();

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps = conn .prepareStatement("Select b.batchId, b.courseId, b.facultyId, f.facultyFname, b.noOfStudents, b.batchstartDate, b.duration  from Batch b , Faculty f where b.facultyID = f.facultyID;");

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                String bid = rs.getString("batchId");
                int cid = rs.getInt("courseId");
                int fid = rs.getInt("facultyId");
                String fName = rs.getString("facultyFname");
                int nos = rs.getInt("noOfStudents");
                Date date = rs.getDate("batchstartDate");
                String dur = rs.getString("duration");

                String sDate = date.toString();

                Batch batch = new Batch(bid,cid,fid,fName,nos,sDate,dur);
//				System.out.println(batch);

                batches.add(batch);
            }
            if(batches.size() == 0)
                throw new BatchException(Style.RED_BACKGROUND+"Batch does not exist."+Style.RESET);

        }catch(SQLException e) {

            throw new BatchException(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);

        }

        return batches;

    }



    // Update details of Batch table
    @Override
    public String updateBatch(String str, String set, String name) throws BatchException {

        String message = Style.RED+"Batch Data Not Updated..."+Style.RESET;

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps = conn.prepareStatement("update Batch set "+ str +" = ? where batchId = ?");

            ps.setString(1, set);
            ps.setString(2, name);

            int x = ps.executeUpdate();

            if(x>0) {
                message = Style.GREEN+"Batch Details Updated Successfully.."+Style.RESET;
            }else {
                throw new BatchException(Style.RED+"Batch Not Exist"+Style.RESET);
            }

        } catch (SQLException e) {

            throw new BatchException(Style.RED+"Wrong Data Format"+Style.RESET);
        }

        return message;
    }



    // Delete details from Batch table
    @Override
    public String deleteBatch(String batchId) throws BatchException {

        String message = Style.RED+"Batch Data Not Updated..."+Style.RESET;

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps = conn.prepareStatement("delete from Batch where batchId = ?");

            ps.setString(1, batchId);

            int x = ps.executeUpdate();

            if(x>0) {
                message = Style.GREEN+"Batch Deleted Successfully.."+Style.RESET;
            }else {
                throw new BatchException(Style.RED+"Batch Not Exist"+Style.RESET);
            }

        } catch (SQLException e) {

            throw new BatchException(Style.RED+"Wrong Data Format"+Style.RESET);
        }

        return message;
    }



    // Allocate Faculty in Batch
    @Override
    public String allocateFaculty(int fName, String batchId) throws BatchException {

        String message = Style.RED+"Faculty not allocated to "+batchId+" batch.."+Style.RESET;

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps = conn.prepareStatement("update Batch set facultyId = ? where batchId = ?");

            ps.setInt(1, fName);
            ps.setString(2, batchId);

            int x = ps.executeUpdate();

            if(x>0) {
                message = Style.GREEN+"Faculty allocated to "+batchId+" batch.."+Style.RESET;
            }else {
                throw new BatchException(Style.RED+"Batch doesn't Not Exist"+Style.RESET);
            }

        } catch (SQLException e) {

            throw new BatchException(Style.RED+"Wrong Data Format"+Style.RESET);
        }

        return message;
    }




    // Get all Detailed Batch Report
    @Override
    public List<Report> generateReport() throws BatchException {

        List<Report> reports = new ArrayList<>();

        try(Connection conn = DBUtil.provideConnection()){


            PreparedStatement ps = conn .prepareStatement("select b.batchId, b.courseId, f.facultyFname, b.noOfStudents, b.batchstartDate, b.duration, count(c.daynumber) as planned, "
                    + "(select count(c.status) where status is true) as Completed from Batch b inner join Faculty f inner join Courseplan c "
                    + "on b.facultyID = f.facultyID and b.batchid = c.batchId group by c.batchId;");

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                String bid = rs.getString("batchId");
                int cid = rs.getInt("courseId");
                String fName = rs.getString("facultyFname");
                int sno = rs.getInt("noOfStudents");
                Date date = rs.getDate("batchstartDate");
                String dur = rs.getString("duration");
                int pland = rs.getInt("noOfStudents");
                int comp = rs.getInt("noOfStudents");

                String sDate = date.toString();

                Report report = new Report(bid,cid,fName,sno,sDate,dur,pland,comp);

                reports.add(report);
            }
            if(reports.size()==0)
                throw new BatchException(Style.RED_BACKGROUND+"No Batche is Started."+Style.RESET);

        }catch(SQLException e) {

            throw new BatchException(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);

        }


        return reports;
    }

}
