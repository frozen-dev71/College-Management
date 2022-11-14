package main_components.faculty;

import java.util.Scanner;

import dao.FacultyDao;
import dao.FacultyDaoImpl;
import exception.FacultyException;
import exception.InputException;
import style.Style;
import model.Faculty;

public class SearchFacultyByInt {

    public static void searchById() throws InputException{

        try {
            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);

            System.out.println(Style.CYAN+"Enter id of Faculty"+Style.RESET);
            int id = sc.nextInt();

            FacultyDao dao = new FacultyDaoImpl();

            try {
                Faculty f = dao.searchFacultyById(id);
                System.out.println();
                System.out.println(Style.ORANGE+"Faculty ID : " + f.getFacultyId());
                System.out.println("Faculty Name : " + f.getFname()+ " " + f.getLname());
                System.out.println("Faculty Address : " + f.getAddress() + ", " + f.getState() + ", " + f.getPin());
                System.out.println("Faculty Mobile : " + f.getMobile());
                System.out.println("Faculty Email : " + f.getEmail());
                System.out.println("Faculty Username : " + f.getUsername());
                System.out.println("------------------------------"+Style.RESET);
                System.out.println();

            } catch (FacultyException fe) {
                System.out.println();
                System.out.println(Style.RED_BACKGROUND+fe.getMessage()+Style.RESET);
                System.out.println();
            }
        }catch(Exception e) {
            throw new InputException(Style.RED+"Please Enter Right Input"+Style.RESET);

        }


    }

}
