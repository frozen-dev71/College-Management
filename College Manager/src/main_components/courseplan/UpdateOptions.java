package main_components.courseplan;

import java.util.Scanner;

import style.Style;

public class UpdateOptions {

    public static void CourseOptions() {

        try {

            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);

            while(true) {

                System.out.println(Style.CYAN+"1. Update Status");
                System.out.println("2. Update Day");
                System.out.println("3. Back");
                System.out.println("4. Close"+Style.RESET);

                int ch = sc.nextInt();

                if(ch == 1) {
                    System.out.println("Enter Faculty ID: ");
                    int facultyId = sc.nextInt();
                    if(CheckFacultyId.checkFacultyId(facultyId)) {
                        UpdateStatusByAdmin.changeStatusAdmin(facultyId);
                    }else {
                        System.out.println();
                        System.out.println(Style.RED+"No Faculty Present with FacultyId "+facultyId+Style.RESET);
                        System.out.println();
                    }

                }else if(ch == 2) {
                    System.out.println("Enter Faculty ID: ");
                    int facultyId = sc.nextInt();
                    if(CheckFacultyId.checkFacultyId(facultyId)) {
                        UpdateCpDate.updateDate(facultyId);
                    }else {
                        System.out.println();
                        System.out.println(Style.RED+"No Faculty Present with FacultyId "+facultyId+Style.RESET);
                        System.out.println();
                    }

                }else if(ch == 3) {
                    break;

                }else if(ch == 4) {
                    System.out.println();
                    System.out.println(Style.GREEN_BOLD_BRIGHT+"See You Soon..."+Style.RESET);
                    System.exit(0);

                }else {
                    System.out.println();
                    System.out.println(Style.RED+"Wrong Input Try Again!"+Style.RESET);
                    System.out.println();

                }

            }
        }catch (Exception e) {
            System.out.println();
            System.out.println(Style.RED+"Please Enter Right Input"+Style.RESET);
            System.out.println();
            CourseOptions();
        }

    }

}
