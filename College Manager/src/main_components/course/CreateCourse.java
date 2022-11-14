package main_components.course;

import java.util.Scanner;

import dao.CourseDao;
import dao.CourseDaoImpl;
import exception.CourseException;
import exception.InputException;
import style.Style;
import model.Course;

public class CreateCourse {

    public static void addCourceMtd() throws InputException{

        try {

            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);

            System.out.println(Style.CYAN+"Enter Name of the Course"+Style.RESET);
            String cname = sc.next();

            System.out.println(Style.CYAN+"Enter the Course Fee"+Style.RESET);
            int cfee = sc.nextInt();

            sc.nextLine();
            System.out.println(Style.CYAN+"Enter Couse Description"+Style.RESET);
            String cdesc = sc.nextLine();

            Course course = new Course(cname, cfee, cdesc);

            CourseDao dao = new CourseDaoImpl();

            String res;
            try {
                res = dao.addCourse(course);
                System.out.println();
                System.out.println(res);
                System.out.println();

            } catch (CourseException ce) {
                System.out.println();
                System.out.println(Style.RED_BACKGROUND+ce.getMessage()+Style.RESET);
                System.out.println();
            }

        }catch(Exception e) {
            throw new InputException(Style.RED+"Please Enter Right Input"+Style.RESET);

        }


    }

}
