package dtbase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.FacultyRightsException;
import style.Style;
import model.Faculty;
import utility.DBUtil;

public class FacultyRightsDbImpl implements FacultyRightsDb {
    @Override
    public Faculty loginFaculty(String username, String password) throws FacultyRightsException {

        Faculty faculty = null;

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps= conn.prepareStatement("select * from Faculty where username = ?");

            ps.setString(1, username);

            ResultSet rs= ps.executeQuery();

            if(rs.next()) {

                PreparedStatement ps2 = conn.prepareStatement("select * from Faculty where username = ? and password = ?");

                ps2.setString(1, username);
                ps2.setString(2, password);

                ResultSet  rs2 = ps2.executeQuery();

                if(rs2.next()) {
                    int fid = rs.getInt("facultyId");
                    String fname = rs.getString("facultyFname");
                    String lname = rs.getString("facultyLname");
                    String address = rs.getString("facultyAddress");
                    String state = rs.getString("facultyState");
                    String pin = rs.getString("facultyPin");
                    String mobile = rs.getString("mobile");
                    String email = rs.getString("email");
                    String uname = rs.getString("username");

                    faculty = new Faculty(fid,fname,lname,address,state,pin,mobile,email,uname);
                }else

                    throw new FacultyRightsException(Style.RED+"Wrong Password"+Style.RESET);

            }else

                throw new FacultyRightsException(Style.RED+"No Such Faculty Present With this Username"+Style.RESET);


        } catch (SQLException e) {

            throw new FacultyRightsException(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);
        }

        return faculty;

    }




    @Override
    public String forgetPassword(String mobile, String email, String pass) throws FacultyRightsException {

        String message = Style.RED+"Sorry Not Able To Change Password"+Style.RESET;

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps = conn.prepareStatement("update Faculty set password = ? where mobile = ? and email = ?");

            ps.setString(1, pass);
            ps.setString(3, email);
            ps.setString(2, mobile);

            int x = ps.executeUpdate();

            if(x>0) {
                message = Style.GREEN+"Your Password Updated Successfully.."+Style.RESET;
            }

        }catch(SQLException e) {

            message = Style.RED_BACKGROUND+e.getMessage()+Style.RESET;

        }

        return message;

    }




    @Override
    public String changePassword(int faculty, String pass) throws FacultyRightsException {

        String message = Style.RED+"Password Not Updated..."+Style.RESET;

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps = conn.prepareStatement("update Faculty set password = ? where facultyId = ? ");

            ps.setString(1, pass);
            ps.setInt(2, faculty);

            int x = ps.executeUpdate();

            if(x>0) {
                message = Style.GREEN+"Your Password Updated Successfully.."+Style.RESET;
            }

        }catch(SQLException e) {

            message = Style.RED_BACKGROUND+e.getMessage()+Style.RESET;

        }

        return message;

    }


}
