package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.CourseException;
import style.Style;
import model.Course;
import utility.DBUtil;

public class CourseDaoImpl implements CourseDao {

    // Add New Course into Database
    @Override
    public String addCourse(Course course) throws CourseException{

        String message = Style.RED+"Data Not Inserted..."+Style.RESET;

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps = conn .prepareStatement("insert into Course(courseName, courseFee, courseDesc) values(?,?,?)");

            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getCourseFee());
            ps.setString(3, course.getCourseDesc());

            int x = ps.executeUpdate();

            if(x>0) {
                message = Style.GREEN+"New Course Added Successfully.."+Style.RESET;
            }else {
                throw new CourseException(Style.RED_BACKGROUND+"Duplicate Entry"+Style.RESET);
            }

        }catch(SQLException e) {
//			e.printStackTrace();

            throw new CourseException(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);

        }

        return message;
    }


    // Search Course With Name
    @Override
    public Course searchCourse(String name) throws CourseException{

        Course course = null;

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps = conn .prepareStatement("Select * from Course where courseName = ?");

            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                int cid = rs.getInt("courseId");
                String cname = rs.getString("courseName");
                int cfee = rs.getInt("courseFee");
                String cdesc = rs.getString("courseDesc");

                course = new Course(cid, cname, cfee, cdesc);

            }else {
                throw new CourseException(Style.RED_BACKGROUND+"Course does not exist."+Style.RESET);
            }


        }catch(SQLException e) {
//			e.printStackTrace();

            throw new CourseException(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);
        }

        return course;
    }


    // See All Course Details Present in Database
    @Override
    public List<Course> getAllCourse() throws CourseException {

        List<Course> courses = new ArrayList<>();

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps = conn .prepareStatement("Select * from Course");


            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                int cid = rs.getInt("courseId");
                String cname = rs.getString("courseName");
                int cfee = rs.getInt("courseFee");
                String cdesc = rs.getString("courseDesc");

                Course course = new Course(cid, cname, cfee, cdesc);

                courses.add(course);

            }

            if(courses.size() == 0) {
                throw new CourseException(Style.RED_BACKGROUND+"No Course found.."+Style.RESET);
            }


        }catch(SQLException e) {
//			e.printStackTrace();

            throw new CourseException(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);
        }

        return courses;
    }


    // Update details of Course table
    @Override
    public String updateCourseDetails(String str, String set, String name) throws CourseException{

        String message = Style.RED+"Course Data Not Updated..."+Style.RESET;

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps = conn.prepareStatement("update Course set "+ str +" = ? where courseName = ?");

            ps.setString(1, set);
            ps.setString(2, name);

            int x = ps.executeUpdate();

            if(x>0) {
                message = Style.GREEN+"course Details Updated Successfully.."+Style.RESET;
            }else {
                throw new CourseException(Style.RED+"Course Not Exist"+Style.RESET);
            }

        } catch (SQLException e) {
            throw new CourseException(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);

        }

        return message;
    }


    // Delete details from Course table
    @Override
    public String deleteBatch(String cName) throws CourseException {

        String message = Style.RED+"Batch Data Not Updated..."+Style.RESET;

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps = conn.prepareStatement("delete from Course where courseName = ?");

            ps.setString(1, cName);

            int x = ps.executeUpdate();

            if(x>0) {
                message = Style.GREEN+"Course Deleted Successfully.."+Style.RESET;
            }else {
                throw new CourseException(Style.RED+"Course Not Exist"+Style.RESET);

            }
        }catch (SQLException e) {

            throw new CourseException(Style.RED+"Wrong Data Format"+Style.RESET);
        }

        return message;

    }
}
