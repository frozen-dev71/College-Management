package main_components.course;


import dao.CourseDao;
import dao.CourseDaoImpl;
import exception.CourseException;
import model.Course;

public class SearchCourseByName {

    public static boolean searchByName(String name)  {

        boolean flag = false;

        CourseDao dao = new CourseDaoImpl();

        Course c;
        try {
            c = dao.searchCourse(name);
            if(c != null) {
                flag = true;
            }

        } catch (CourseException e) {
            return flag;

        }



        return flag;

    }

}
