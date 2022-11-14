package main_components.courseplan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.CoursePlanDao;
import dao.CoursePlanDaoImpl;
import exception.CoursePlanException;
import style.Style;
import utility.DBUtil;

public class UpdateCpDate {

    public static void updateDate(int facultyId) {


        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        try(Connection conn = DBUtil.provideConnection()){

            PreparedStatement ps = conn .prepareStatement("select batchId from batch where facultyId = ?");

            ps.setInt(1, facultyId);

            ResultSet rs = ps.executeQuery();
            List<String> arr = new ArrayList<>();
            while(rs.next()) {
                arr.add(rs.getString("batchId"));
            }

            if(arr.size()==0) {
                System.out.println(Style.YELLOW+facultyId + "is not Allocated to Any Batch"+Style.RESET);
                return;
            }

            System.out.println(Style.CYAN+"Choose Batch Id :"+Style.RESET);
            for(int i = 0; i < arr.size(); i++) {
                System.out.println((i+1)+". " +arr.get(i));
            }

            int ch = sc.nextInt();
            String batchId = arr.get(ch-1);

            System.out.println(Style.CYAN+"Enter the old day no : "+Style.RESET);
            int dayNo = sc.nextInt();

            System.out.println(Style.CYAN+"Enter the New day no :"+Style.RESET);
            int newDay = sc.nextInt();

            CoursePlanDao dao = new CoursePlanDaoImpl();

            try {
                String res = dao.updateDate(batchId, dayNo, newDay);
                System.out.println();
                System.out.println(res);
                System.out.println();

            } catch (CoursePlanException e) {
                System.out.println();
                System.out.println(Style.RED_BACKGROUND+ e.getMessage()+Style.RESET);
                System.out.println();

            }

        }catch (Exception e) {
            System.out.println();
            System.out.println(Style.RED+"Please Enter Right Input"+Style.RESET);
            System.out.println();
            updateDate(facultyId);
        }

    }

}
