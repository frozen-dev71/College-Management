package administrator;

import java.util.Scanner;

import style.Style;
import main_components.batch.BatchOptions;
import main_components.batch.GenerateReport;
import main_components.course.CourseChoice;
import main_components.courseplan.CoursePlanOptions;
import main_components.courseplan.ViewAllPlanDayWise;
import main_components.faculty.FacultyOptions;

public class AdminAccess {

    public static void adminOption() {

        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);


        System.out.println(Style.GREEN_BACKGROUND_BRIGHT+" Welcome to College Management Platform \n" );

        while(true) {

            System.out.println(Style.CYAN+"1. Courses");
            System.out.println("2. Faculty");
            System.out.println("3. Batch");
            System.out.println("4. Course Plan");
            System.out.println("5. View Day wise Planner");
            System.out.println("6. Generate Report");
            System.out.println("7. Log Out");
            System.out.println("8. Close the App"+Style.RESET);


            int ch = sc.nextInt();

            if(ch == 1) {
                CourseChoice.courseOptions();

            }else if(ch == 2) {
                FacultyOptions.facultyOptions();

            }else if(ch == 3) {
                BatchOptions.facultyOptions();

            }else if(ch == 4) {
                CoursePlanOptions.CourseOptions();

            }else if(ch == 5) {
                ViewAllPlanDayWise.viewAllPlan();

            }else if(ch == 6) {
                GenerateReport.viewAllBatch();

            }else if(ch== 8) {
                System.out.println();
                System.out.println(Style.GREEN_BOLD_BRIGHT+"Come Back Again."+Style.RESET);
                System.exit(0);

            }else if(ch == 7) {
                break;

            }
            else {
                System.out.println();
                System.out.println(Style.RED+"Wrong Input Try Again!"+Style.RESET);
                System.out.println();
                adminOption();
            }

        }


    }

}
