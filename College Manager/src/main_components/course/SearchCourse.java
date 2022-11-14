package main_components.course;

import java.util.Scanner;

import dao.CourseDao;
import dao.CourseDaoImpl;
import exception.CourseException;
import exception.InputException;
import style.Style;
import model.Course;

public class SearchCourse {

    public static void searchByCourse() throws InputException{

        try {
            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);

            System.out.println(Style.CYAN+"Enter Name of Course"+Style.RESET);
            String cname = sc.next();


            CourseDao dao = new CourseDaoImpl();

            try {
                Course c = dao.searchCourse(cname);
                System.out.println();
                System.out.println(Style.ORANGE+"Course Id : "+ c.getCourseId());
                System.out.println("Course Name : " + c.getCourseName());
                System.out.println("Course Fee : " + c.getCourseFee() + " Rs.");
                System.out.println("Course Description : " + c.getCourseDesc());
                System.out.println("------------------------------"+Style.RESET);
                System.out.println();

            } catch (CourseException e) {
                System.out.println();
                System.out.println(Style.RED_BACKGROUND+ e.getMessage()+Style.RESET);
                System.out.println();
            }
        }catch(Exception e) {
            throw new InputException(Style.RED+"Please Enter Right Input"+Style.RESET);

        }

    }

}
