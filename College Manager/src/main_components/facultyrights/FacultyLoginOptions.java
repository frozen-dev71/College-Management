package main_components.facultyrights;

import java.util.Scanner;

import style.Style;
import main_components.courseplan.UpdateStatus;
import main_components.courseplan.UpdateTopic;
import main_components.courseplan.ViewFacultyCoursePlan;
import model.Faculty;

public class FacultyLoginOptions {

    public static void facultyOption(Faculty faculty) {

        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);


        while(true) {
            System.out.println(Style.CYAN+"1. Fill Day Planner");
            System.out.println("2. Update Status Day Planner");
            System.out.println("3. View Course Plan");
            System.out.println("4. Settings");
            System.out.println("5. Log Out");
            System.out.println("6. Close the App"+Style.RESET);

            int ch = sc.nextInt();

            if(ch == 1) {
                UpdateTopic.updateTopic(faculty.getFacultyId());

            }else if(ch == 2) {
                UpdateStatus.updateStatus(faculty.getFacultyId());

            }else if(ch == 3) {
                ViewFacultyCoursePlan.viewByFaculty(faculty.getFacultyId());

            }else if(ch == 4) {
                FacultySettings.facultySettings(faculty);

            }else if(ch== 6) {
                System.out.println();
                System.out.println(Style.GREEN_BOLD_BRIGHT+"Come Back Again."+Style.RESET);
                System.exit(0);

            }else if(ch == 5) {
                break;

            }
            else {
                System.out.println();
                System.out.println(Style.RED+"Invalid Input Try Again!"+Style.RESET);
                System.out.println();
                facultyOption(faculty);
            }

        }

    }

}
