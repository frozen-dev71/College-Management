package main_components.facultyrights;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import dao.FacultyRightsDao;
import dao.FacultyRightsDaoImpl;
import exception.FacultyRightsException;
import style.Style;
import utility.DBUtil;

public class ForgetPassword {

    public static boolean forgetPass() {

        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        try(Connection conn = DBUtil.provideConnection()){

            System.out.println(Style.CYAN+"Enter Mobile No. : "+Style.RESET);
            String mobile = sc.next();

            System.out.println(Style.CYAN+"Enter Email No. : "+Style.RESET);
            String email = sc.next();



            PreparedStatement ps = conn .prepareStatement("select * from faculty where mobile = ? and email = ?");
            ps.setString(1, mobile);
            ps.setString(2, email);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                FacultyRightsDao dao = new FacultyRightsDaoImpl();

                System.out.println(Style.CYAN+"Enter New Password: "+Style.RESET);
                String newPass = sc.next();


                System.out.println(Style.CYAN+"Enter New Password Again : "+Style.RESET);
                String newPass2 = sc.next();


                if(newPass.equals(newPass2)) {


                    try {
                        String res = dao.forgetPassword(mobile, email, newPass2);
                        System.out.println();
                        System.out.println(res);
                        System.out.println();

                    } catch (FacultyRightsException e) {
                        System.out.println();
                        System.out.println(Style.RED_BACKGROUND+ e.getMessage()+Style.RESET);
                        System.out.println();
                        return false;
                    }

                }else {
                    System.out.println();
                    System.out.println(Style.RED+"New Password Mismatch.."+Style.RESET);
                    System.out.println();
                    return false;
                }


            }else {
                System.out.println();
                System.out.println(Style.RED+"Mobile Number or Email Not Found.."+Style.RESET);
                System.out.println();
                return false;

            }

        } catch (SQLException e) {
            System.out.println();
            System.out.println(Style.RED_BACKGROUND+ e.getMessage()+Style.RESET);
            System.out.println();
            return false;

        }
        return true;

    }

}
