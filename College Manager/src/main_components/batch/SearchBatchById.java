package main_components.batch;

import java.util.Scanner;

import dao.BatchDao;
import dao.BatchDaoImpl;
import exception.BatchException;
import exception.InputException;
import style.Style;
import model.Batch;


public class SearchBatchById {

    public static void searchBatchById() throws InputException {

        try {
            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);

            System.out.println(Style.CYAN+"Enter id of Batch"+Style.RESET);
            String id = sc.next();

            BatchDao dao = new BatchDaoImpl();

            try {
                Batch b = dao.searchBatchById(id);

                System.out.println();
                System.out.println(Style.ORANGE+"------------------------------------------------------------------------------------------------");
                System.out.printf("%8s %8s %6s %10s %10s %10s %10s", " BATCH ID |", "COURSE ID |", "FACULTY ID |", "FACULTY NAME |", "No. Of Students |", "Starting Date |", "Duration |");
                System.out.println();
                System.out.println("------------------------------------------------------------------------------------------------");
                System.out.printf("%5s %8s %11s %15s %14s %20s %15s", b.getBatchId(), b.getCourseId(), b.getFacultyId(), b.getFacultyName(), b.getNoOfStudents(), b.getBatchstartDate(), b.getDuration()+Style.RESET);
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
