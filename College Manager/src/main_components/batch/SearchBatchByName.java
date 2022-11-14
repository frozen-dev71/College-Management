package main_components.batch;

import java.util.List;
import java.util.Scanner;

import dao.BatchDao;
import dao.BatchDaoImpl;
import exception.BatchException;
import exception.InputException;
import style.Style;
import model.Batch;

public class SearchBatchByName {

    public static void searchBatchByName() throws InputException{


        try {
            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);

            System.out.println(Style.CYAN+"Enter Name of Batch"+Style.RESET);
            String bname = sc.next();

            BatchDao dao = new BatchDaoImpl();

            try {
                List<Batch> batches = dao.searchBatchByName(bname);

                System.out.println();
                System.out.println(Style.ORANGE+"------------------------------------------------------------------------------------------------");
                System.out.printf("%8s %8s %6s %10s %10s %10s %10s", " BATCH ID |", "COURSE ID |", "FACULTY ID |", "FACULTY NAME |", "No. Of Students |", "Starting Date |", "Duration |");
                System.out.println();
                System.out.println("------------------------------------------------------------------------------------------------");

                batches.forEach( b -> {
                    System.out.printf("%5s %8s %11s %15s %14s %20s %12s", b.getBatchId(), b.getCourseId(), b.getFacultyId(), b.getFacultyName(), b.getNoOfStudents(), b.getBatchstartDate(), b.getDuration());
                    System.out.println();

                });
                System.out.println();

            } catch (BatchException e) {

                System.out.println();
                System.out.println(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);
                System.out.println();
            }
        }catch(Exception e) {
            throw new InputException(Style.RED+"Please Enter Right Input"+Style.RESET);

        }

    }

}
