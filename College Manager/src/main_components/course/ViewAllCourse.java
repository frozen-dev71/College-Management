package main_components.course;

import java.util.List;

import dao.CourseDao;
import dao.CourseDaoImpl;
import exception.CourseException;
import style.Style;
import model.Course;

public class ViewAllCourse {

    public static void viewAllCourse() {

        CourseDao dao = new CourseDaoImpl();

        try {
            List<Course> courses = dao.getAllCourse();

            courses.forEach( c -> {

                System.out.println();
                System.out.println(Style.ORANGE+"Course Id : "+ c.getCourseId());
                System.out.println("Course Name : " + c.getCourseName());
                System.out.println("Course Fee : " + c.getCourseFee() + " €.");
                System.out.println("Course Description : " + c.getCourseDesc());
                System.out.println("------------------------------"+Style.RESET);

            });
            System.out.println();

        } catch (CourseException e) {
            System.out.println();
            System.out.println(Style.RED_BACKGROUND+ e.getMessage()+Style.RESET);
            System.out.println();
        }

    }

}
