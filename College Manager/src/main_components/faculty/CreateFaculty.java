package main_components.faculty;

import java.util.Scanner;

import dao.FacultyDao;
import dao.FacultyDaoImpl;
import exception.FacultyException;
import exception.InputException;
import style.Style;
import model.Faculty;

public class CreateFaculty {

    public static void addFacultyMtd() throws InputException{

        try {

            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);

            System.out.println(Style.CYAN+"Enter First Name of Faculty"+Style.RESET);
            String fname = sc.next();

            System.out.println(Style.CYAN+"Enter Last Name of Faculty"+Style.RESET);
            String lname = sc.next();

            sc.nextLine();
            System.out.println(Style.CYAN+"Enter Faculty Address"+Style.RESET);
            String address = sc.nextLine();

            System.out.println(Style.CYAN+"Enter Faculty State(Short Form)"+Style.RESET);
            String state = sc.next();

            System.out.println(Style.CYAN+"Enter Faculty Pincode"+Style.RESET);
            String pin = sc.next();

            System.out.println(Style.CYAN+"Enter Faculty Mobile No."+Style.RESET);
            String mobile = sc.next();

            System.out.println(Style.CYAN+"Enter Faculty Email"+Style.RESET);
            String email = sc.next();


            Faculty faculty = new Faculty(fname, lname, address, state, pin, mobile, email);

            FacultyDao dao = new FacultyDaoImpl();

            String result;
            try {
                result = dao.addFaculty(faculty);
                System.out.println();
                System.out.println(result);
                System.out.println();

            } catch (FacultyException e) {
                System.out.println();
                System.out.println(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);
                System.out.println();
            }

        }catch(Exception e) {
            throw new InputException(Style.RED+"Please Enter Right Input"+Style.RESET);

        }

    }

}
