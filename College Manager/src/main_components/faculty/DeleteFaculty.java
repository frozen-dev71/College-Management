package main_components.faculty;

import java.util.Scanner;

import dao.FacultyDao;
import dao.FacultyDaoImpl;
import exception.FacultyException;
import exception.InputException;
import style.Style;

public class DeleteFaculty {

    public static void deleteFaculty() throws InputException {

        try {
            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);

            System.out.println(Style.CYAN+"Enter id of Faculty"+Style.RESET);
            int id = sc.nextInt();

            FacultyDao dao = new FacultyDaoImpl();

            try {
                String res = dao.deleteFaculty(id);
                System.out.println();
                System.out.println(res);
                System.out.println();

            } catch (FacultyException e) {
                System.out.println();
                System.out.println(Style.RED_BACKGROUND+ e.getMessage()+Style.RESET);
                System.out.println();

            }

        }catch(Exception e) {
            throw new InputException(Style.RED+"Please Enter Right Input"+Style.RESET);

        }
    }

}
