package main_components.facultyrights;

import java.util.Scanner;

import style.Style;
import main_components.faculty.UpdateFaculty;
import model.Faculty;

public class FacultySettings {

    public static void facultySettings(Faculty faculty) {

        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);


        while(true) {
            System.out.println(Style.CYAN+"1. Update profile");
            System.out.println("2. Update Password");
            System.out.println("3. Back");
            System.out.println("4. Close the App"+Style.RESET);

            int ch = sc.nextInt();

            if(ch == 1) {
                UpdateFaculty.UpdateById(faculty.getFacultyId());

            }else if(ch == 2) {
                UpdatePassword.chnagePass(faculty.getFacultyId());

            }else if(ch== 4) {
                System.out.println();
                System.out.println(Style.GREEN_BOLD_BRIGHT+"See You Soon..."+Style.RESET);
                System.exit(0);

            }else if(ch == 3) {
                break;

            }
            else {
                System.out.println();
                System.out.println(Style.RED+"Wrong Input Try Again!"+Style.RESET);
                System.out.println();
                facultySettings(faculty);
            }

        }

    }

}
