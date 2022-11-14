package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import exception.CoursePlanException;
import style.Style;
import model.CoursePlan;
import utility.DBUtil;

public class CoursePlanDaoImpl implements CoursePlanDao {

    // Add New Course Plan into Database
    @Override
    public String addCoursePlan(String batchId, int dayNo) throws CoursePlanException {

        String message = Style.RED+"Data Not Inserted..."+Style.RESET;

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps1 = conn .prepareStatement("select batchstartDate from Batch where batchId = ?");

            ps1.setString(1, batchId);
            ResultSet rs = ps1.executeQuery();

            String dt = "";

            if(rs.next()) {
                Date date = rs.getDate("batchstartDate");
                dt = date.toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                Calendar c = Calendar.getInstance();

                try {
                    c.setTime(sdf.parse(dt));

                } catch (ParseException e) {
                    System.out.println((Style.RED_BACKGROUND+e.getMessage()+Style.RESET));

                }
                c.add(Calendar.DATE, dayNo-1);  // number of days to add

                dt = sdf.format(c.getTime());  // dt is now the new date
            }

            PreparedStatement ps = conn .prepareStatement("insert into Courseplan(batchId, daynumber, planDate) values(?, ?, ?)");

            ps.setString(1, batchId);
            ps.setInt(2, dayNo);
            ps.setString(3, dt);

            int x = ps.executeUpdate();

            if(x>0) {
                message = Style.GREEN+"New Course Plan Added Successfully.."+Style.RESET;
            }
        }catch(SQLException e) {
            throw new CoursePlanException(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);
        }

        return message;
    }


    // Update Status of Course table for Faculty Only
    @Override
    public String updateStatus(String batchId, int dayNo) throws CoursePlanException {

        String message = Style.RED+"Status Not Updated..."+Style.RESET;

        try(Connection conn = DBUtil.provideConnection()){
            PreparedStatement ps1 = conn .prepareStatement("select datediff(planDate,curdate()) as date from Courseplan where batchId = ? AND daynumber = ?");

            ps1.setString(1, batchId);
            ps1.setInt(2, dayNo);

            ResultSet rs = ps1.executeQuery();

            int diff =-1;
            if(rs.next()) {
                diff = rs.getInt(1);
            }

            if(diff<=0) {
                PreparedStatement ps = conn .prepareStatement("update Courseplan set status = true where batchId = ? AND daynumber = ?");

                ps.setString(1, batchId);
                ps.setInt(2, dayNo);

                int x = ps.executeUpdate();

                if(x>0) {
                    message = Style.GREEN+"Status Updated Successfully.."+Style.RESET;
                }else {
                    throw new CoursePlanException(Style.RED+"Day no "+dayNo+" is not Planned yet.."+Style.RESET);
                }
            }else {
                throw new CoursePlanException(Style.RED+"You Can't Change Status For a Future Date"+Style.RESET);
            }

        }catch(Exception e) {
            throw new CoursePlanException(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);
        }

        return message;
    }


    // Update topic of Course table for Faculty Only
    @Override
    public String updateTopic(String batchId, int dayNo, String topic) throws CoursePlanException {

        String message = Style.RED+"Status Not Updated..."+Style.RESET;

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps = conn .prepareStatement("update Courseplan set topic = ? where batchId = ? AND daynumber = ?");

            ps.setString(1, topic);
            ps.setString(2, batchId);
            ps.setInt(3, dayNo);

            int x = ps.executeUpdate();

            if(x>0) {
                message = Style.GREEN+"Status Updated Successfully.."+Style.RESET;

            }else {
                throw new CoursePlanException(Style.RED+"Day no "+dayNo+" is not Planned yet.."+Style.RESET);

            }

        }catch(SQLException e) {
            throw new CoursePlanException(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);

        }

        return message;
    }


    // Delete Day plan from Course table
    @Override
    public String deleteStatus(String batchId, int dayNo) throws CoursePlanException {

        String message = Style.RED+"Plan Not Deleted..."+Style.RESET;

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps = conn .prepareStatement("delete from Courseplan where batchId = ? AND daynumber = ?");

            ps.setString(1, batchId);
            ps.setInt(2, dayNo);

            int x = ps.executeUpdate();

            if(x>0) {
                message = Style.GREEN+"Course Plan Deleted Successfully.."+Style.RESET;

            }else {
                throw new CoursePlanException(Style.RED+"Day no "+dayNo+" is not Planned yet.."+Style.RESET);

            }

        }catch(SQLException e) {
            throw new CoursePlanException(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);
        }

        return message;

    }


    // View All Plans Date Wise
    @Override
    public List<CoursePlan> viewAllCoursePlanDateWise() throws CoursePlanException {

        List<CoursePlan> coursePlans = new ArrayList<>();

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps = conn .prepareStatement("SELECT * FROM Courseplan ORDER BY planDate");

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                int pid = rs.getInt("planId");
                String bid = rs.getString("batchId");
                int dNo = rs.getInt("daynumber");
                String topic = rs.getString("topic");
                Date date = rs.getDate("planDate");
                boolean staus = rs.getBoolean("status");

                String dt = date.toString();

                CoursePlan course = new CoursePlan(pid, bid, dNo, topic, dt, staus);

                coursePlans.add(course);

            }

            if(coursePlans.size() == 0)
                throw new CoursePlanException(Style.RED_BACKGROUND+"No Plan is Created till Now.."+Style.RESET);

        }catch(SQLException e) {
            throw new CoursePlanException(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);

        }

        return coursePlans;
    }


    // View Plans As Per Faculty
    @Override
    public List<CoursePlan> viewFacultyCoursePlan(int facultyId) throws CoursePlanException{

        List<CoursePlan> coursePlans = new ArrayList<>();

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps = conn.prepareStatement("Select c.* from Courseplan c, Batch b where c.batchId = b.batchId and b.facultyId = ? ORDER BY planDate");

            ps.setInt(1, facultyId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                int pid = rs.getInt("planId");
                String bid = rs.getString("batchId");
                int dNo = rs.getInt("daynumber");
                String topic = rs.getString("topic");
                Date date = rs.getDate("planDate");
                boolean staus = rs.getBoolean("status");

                String dt = date.toString();

                CoursePlan course = new CoursePlan(pid, bid, dNo, topic, dt, staus);

                coursePlans.add(course);

            }

            if(coursePlans.size() == 0)
                throw new CoursePlanException(Style.RED_BACKGROUND+"No Such Plan.."+Style.RESET);

        }catch(SQLException e) {
            throw new CoursePlanException(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);

        }

        return coursePlans;

    }


    // Update Date of Course table
    @Override
    public String updateDate(String batchId, int dayNo, int newDay) throws CoursePlanException {


        String message = Style.RED+"Status Not Updated..."+Style.RESET;

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps1 = conn .prepareStatement("select batchstartDate from Batch where batchId = ?");

            ps1.setString(1, batchId);
            ResultSet rs = ps1.executeQuery();

            String dt = "";

            if(rs.next()) {
                Date date = rs.getDate("batchstartDate");
                dt = date.toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                Calendar c = Calendar.getInstance();

                try {
                    c.setTime(sdf.parse(dt));

                } catch (ParseException e) {
                    System.out.println((Style.RED_BACKGROUND+e.getMessage()+Style.RESET));

                }
                c.add(Calendar.DATE, newDay-1);  // number of days to add

                dt = sdf.format(c.getTime());  // dt is now the new date
            }


            PreparedStatement ps = conn.prepareStatement("update Courseplan set daynumber = ? where batchId = ? AND daynumber = ?");

            ps.setInt(1, newDay);
            ps.setString(2, batchId);
            ps.setInt(3, dayNo);

            int x = ps.executeUpdate();

            if(x>0) {

                PreparedStatement ps3 = conn.prepareStatement("update Courseplan set planDate = ? where batchId = ? AND daynumber = ?");


                ps3.setString(1, dt);
                ps3.setString(2, batchId);
                ps3.setInt(3, newDay);

                int y = ps3.executeUpdate();

                if(y>0)
                    message = Style.GREEN+"Status Updated Successfully.."+Style.RESET;
                else
                    throw new CoursePlanException(Style.RED+"Day no "+dayNo+" is not Planned yet.."+Style.RESET);


            }else {
                throw new CoursePlanException(Style.RED+"Day no "+dayNo+" is not Planned yet.."+Style.RESET);

            }

        }catch(SQLException e) {
            throw new CoursePlanException(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);

        }

        return message;
    }


    // View Plans By Date
    @Override
    public List<CoursePlan> viewCourseByDate(String date) throws CoursePlanException {

        List<CoursePlan> coursePlans = new ArrayList<>();

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps = conn.prepareStatement("Select * from Courseplan where planDate = ? ");

            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                int pid = rs.getInt("planId");
                String bid = rs.getString("batchId");
                int dNo = rs.getInt("daynumber");
                String topic = rs.getString("topic");
                Date rdate = rs.getDate("planDate");
                boolean staus = rs.getBoolean("status");

                String dt = rdate.toString();

                CoursePlan course = new CoursePlan(pid, bid, dNo, topic, dt, staus);

                coursePlans.add(course);

            }

            if(coursePlans.size() == 0)
                throw new CoursePlanException(Style.RED_BACKGROUND+"No Plan for this Date"+Style.RESET);

        }catch(SQLException e) {
            throw new CoursePlanException(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);

        }

        return coursePlans;
    }




    // Update Status of Course table
    @Override
    public String updateStatusAdmin(String batchId, int dayNo) throws CoursePlanException {

        String message = Style.RED+"Status Not Updated..."+Style.RESET;

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps = conn .prepareStatement("update Courseplan set status = false where batchId = ? AND daynumber = ?");

            ps.setString(1, batchId);
            ps.setInt(2, dayNo);

            int x = ps.executeUpdate();

            if(x>0) {
                message = Style.GREEN+"Status Updated Successfully.."+Style.RESET;
            }else {
                throw new CoursePlanException(Style.RED+"Day no "+dayNo+" is not Planned yet.."+Style.RESET);
            }
        }catch(Exception e) {
            throw new CoursePlanException(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);
        }

        return message;

    }
}
