package main_components.faculty;

import java.util.Scanner;

import exception.InputException;
import style.Style;

public class FacultyOptions {

    public static void facultyOptions() {

        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        while(true) {

            System.out.println(Style.CYAN+"1. Add Faculty");
            System.out.println("2. Update Faculty");
            System.out.println("3. Search Faculty");
            System.out.println("4. Delete Faculty");
            System.out.println("5. Back");
            System.out.println("6. Close"+Style.RESET);


            int ch = sc.nextInt();

            if(ch == 1) {
                try {
                    CreateFaculty.addFacultyMtd();

                } catch (InputException e) {
                    System.out.println();
                    System.out.println(e.getMessage());
                    System.out.println();

                }

            }else if(ch == 2) {
                UpdateOptions.updateOptions();

            }else if(ch == 3) {
                SearchOptions.searchOptions();

            }else if(ch == 4) {
                try {
                    DeleteFaculty.deleteFaculty();
                } catch (InputException e) {
                    System.out.println();
                    System.out.println(e.getMessage());
                    System.out.println();
                }

            }else if(ch== 6) {
                System.out.println();
                System.out.println(Style.BANANA_YELLOW+"See You Soon..."+Style.RESET);
                System.exit(0);

            }else if(ch == 5) {
                break;

            }else {
                System.out.println();
                System.out.println(Style.RED+"Wrong Input Try Again"+Style.RESET);
                System.out.println();

            }
        }

    }

}
