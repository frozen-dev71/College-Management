package main_components.faculty;

import java.util.List;
import java.util.Scanner;

import dao.FacultyDao;
import dao.FacultyDaoImpl;
import exception.FacultyException;
import style.Style;
import model.Faculty;

public class SearchFacultyByName {

    public static boolean searchByName() {


        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        System.out.println(Style.CYAN+"Enter First Name of Faculty"+Style.RESET);
        String fname = sc.next();

        FacultyDao dao = new FacultyDaoImpl();


        try {
            List<Faculty> facultys = dao.searchFacultyByName(fname);

            facultys.forEach( f -> {
                System.out.println();
                System.out.println(Style.ORANGE+"Faculty ID : " + f.getFacultyId());
                System.out.println("Faculty Name : " + f.getFname()+ " " + f.getLname());
                System.out.println("Faculty Address : " + f.getAddress() + ", " + f.getState() + ", " + f.getPin());
                System.out.println("Faculty Mobile : " + f.getMobile());
                System.out.println("Faculty Email : " + f.getEmail());
                System.out.println("Faculty Username : " + f.getUsername());
                System.out.println("------------------------------"+Style.RESET);

            });
            System.out.println();


        } catch (FacultyException fe) {
            System.out.println();
            System.out.println(Style.RED_BACKGROUND+fe.getMessage()+Style.RESET);
            System.out.println();
            return false;
        }
        return true;

    }

}
