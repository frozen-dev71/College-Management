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

public class UpdatePassword {

    public static void chnagePass(int facultyId) {

        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        try(Connection conn = DBUtil.provideConnection()){

            System.out.println(Style.CYAN+"Enter Old Password : "+Style.RESET);
            String oldPass = sc.next();

            System.out.println(Style.CYAN+"Enter New Password: "+Style.RESET);
            String newPass = sc.next();

            System.out.println(Style.CYAN+"Enter New Password : "+Style.RESET);
            String newPass2 = sc.next();


            PreparedStatement ps = conn .prepareStatement("select * from faculty where facultyId = ? And password = ?");
            ps.setInt(1, facultyId);
            ps.setString(2, oldPass);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                if(newPass.equals(newPass2)) {

                    FacultyRightsDao dao = new FacultyRightsDaoImpl();

                    try {
                        String res = dao.changePassword(facultyId, newPass2);
                        System.out.println();
                        System.out.println(res);
                        System.out.println();
                    } catch (FacultyRightsException e) {

                        System.out.println();
                        System.out.println(Style.RED_BACKGROUND+ e.getMessage()+Style.RESET);
                        System.out.println();
                    }

                }else {
                    System.out.println();
                    System.out.println(Style.RED+"New Password Mismatch.."+Style.RESET);
                    System.out.println();
                }

            }else {
                System.out.println();
                System.out.println(Style.RED+"Wrong Old Password.."+Style.RESET);
                System.out.println();

            }

        } catch (SQLException e) {
            System.out.println();
            System.out.println(Style.RED_BACKGROUND+ e.getMessage()+Style.RESET);
            System.out.println();
        }

    }

}
