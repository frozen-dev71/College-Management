package main_components.courseplan;

import java.util.Scanner;

import style.Style;

public class CoursePlanOptions {


    public static void CourseOptions() {

        try {
            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);

            while(true) {

                System.out.println(Style.CYAN+"1. Add Course Plan");
                System.out.println("2. Update Course Plan");
                System.out.println("3. View Course Plan");
                System.out.println("4. Delete Course Plan");
                System.out.println("5. Back");
                System.out.println("6. Close"+Style.RESET);

                int ch = sc.nextInt();

                if(ch == 1) {
                    System.out.println("Enter Faculty ID: ");
                    int facultyId = sc.nextInt();
                    if(CheckFacultyId.checkFacultyId(facultyId)) {
                        CreateCoursePlanFaculty.addCoursePlanMtd(facultyId);

                    }else {
                        System.out.println();
                        System.out.println(Style.RED+"No Faculty Present with FacultyId "+facultyId+Style.RESET);
                        System.out.println();
                    }

                }else if(ch == 2) {
                    UpdateOptions.CourseOptions();

                }else if(ch == 3) {
                    ViewOptions.viewOptions();

                }else if(ch == 4) {
                    System.out.println("Enter Faculty ID: ");
                    int facultyId = sc.nextInt();

                    if(CheckFacultyId.checkFacultyId(facultyId)) {
                        DeleteCoursePlan.deletePlan(facultyId);
                    }else {
                        System.out.println();
                        System.out.println(Style.RED+"No Faculty Present with FacultyId "+facultyId+Style.RESET);
                        System.out.println();
                    }

                }else if(ch == 5) {
                    break;

                }else if(ch == 6) {
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
