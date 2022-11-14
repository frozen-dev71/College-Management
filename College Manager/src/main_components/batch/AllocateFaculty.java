package main_components.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import dao.BatchDao;
import dao.BatchDaoImpl;
import exception.BatchException;
import exception.FacultyException;
import style.Style;
import utility.DBUtil;

public class AllocateFaculty {

    public static void allocateFaculty() throws FacultyException {

        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);


        try(Connection conn = DBUtil.provideConnection()){
            System.out.println(Style.CYAN+"Enter the Faculty Id :"+Style.RESET);
            int set = sc.nextInt();

            PreparedStatement ps = conn.prepareStatement("select * from faculty where facultyId = ?");

            ps.setInt(1, set);

            ResultSet rs = ps.executeQuery();

            boolean flag = rs.next();
            if(flag) {
                while(flag) {
                    System.out.println(Style.CYAN+"Enter the Batch Id :"+Style.RESET);
                    String batchId = sc.next();

                    BatchDao dao = new BatchDaoImpl();

                    try {
                        String res = dao.allocateFaculty(set, batchId);
                        System.out.println();
                        System.out.println(res);
                        System.out.println();
                        flag = false;

                    } catch (BatchException e) {
                        System.out.println();
                        System.out.println(Style.RED_BACKGROUND+ e.getMessage()+Style.RESET);
                        System.out.println();

                    }

                }

            }else {
                System.out.println();
                System.out.println(Style.RED+"Faculty is Not Present.."+Style.RESET);
                System.out.println();
                allocateFaculty();
            }

        }catch(Exception ie) {
            System.out.println();
            System.out.println(Style.RED+"Wrong Input Try Again!"+Style.RESET);
            System.out.println();
            allocateFaculty();
        }

    }

}
