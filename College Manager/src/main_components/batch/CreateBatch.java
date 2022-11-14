package main_components.batch;


import java.util.Scanner;

import dao.BatchDao;
import dao.BatchDaoImpl;
import exception.BatchException;
import exception.InputException;
import style.Style;
import model.Batch;

public class CreateBatch {


    public static void addBatchMtd() throws InputException{

        try {

            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);

            System.out.println(Style.CYAN+"Enter BatchId"+Style.RESET);
            String bId = sc.next();

            sc.nextLine();
            System.out.println(Style.CYAN+"Enter CourseId of the Batch"+Style.RESET);
            int cId = sc.nextInt();

            System.out.println(Style.CYAN+"Enter Students no. of the Batch"+Style.RESET);
            int noStud = sc.nextInt();

            System.out.println(Style.CYAN+"Enter Start date of the Batch(YYYY-MM-DD)."+Style.RESET);
            String date = sc.next();

            sc.nextLine();
            System.out.println(Style.CYAN+"Enter Batch Duration"+Style.RESET);
            String bDur = sc.nextLine();


            Batch batch = new Batch(bId, cId, noStud, date, bDur);

            BatchDao dao1 = new BatchDaoImpl();

            try {
                String str = dao1.addBatch(batch);
                System.out.println();
                System.out.println(str);
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
