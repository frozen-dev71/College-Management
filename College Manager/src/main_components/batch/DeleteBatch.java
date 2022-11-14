package main_components.batch;

import java.util.Scanner;

import dao.BatchDao;
import dao.BatchDaoImpl;
import exception.BatchException;
import exception.InputException;
import style.Style;

public class DeleteBatch {

    public static void deleteBatch() throws InputException {

        try {
            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);

            System.out.println(Style.CYAN+"Enter id of Batch"+Style.RESET);
            String id = sc.next();

            BatchDao dao = new BatchDaoImpl();

            try {
                String res = dao.deleteBatch(id);
                System.out.println();
                System.out.println(res);
                System.out.println();

            } catch (BatchException e) {
                System.out.println();
                System.out.println(Style.RED_BACKGROUND+ e.getMessage()+Style.RESET);
                System.out.println();

            }

        }catch(Exception e) {
            throw new InputException(Style.RED+"Please Enter Right Input"+Style.RESET);

        }
    }

}
