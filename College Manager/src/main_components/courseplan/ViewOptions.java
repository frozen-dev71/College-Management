package main_components.courseplan;

import java.util.Scanner;

import style.Style;

public class ViewOptions {

    public static void viewOptions() {

        try {

            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);

            while(true) {

                System.out.println(Style.CYAN+"1. View All Plan Day Wise");
                System.out.println("2. Search By Faculty");
                System.out.println("3. Search By Date");
                System.out.println("4. Back");
                System.out.println("5. Close"+Style.RESET);

                int ch = sc.nextInt();

                if(ch == 1) {
                    ViewAllPlanDayWise.viewAllPlan();

                }else if(ch == 2) {
                    System.out.println("Enter Faculty ID: ");
                    int facultyId = sc.nextInt();
                    if(CheckFacultyId.checkFacultyId(facultyId)) {
                        ViewFacultyCoursePlan.viewByFaculty(facultyId);

                    }else {
                        System.out.println();
                        System.out.println(Style.RED+"No Faculty Present with FacultyId "+facultyId+Style.RESET);
                        System.out.println();
                    }

                }else if(ch == 3) {
                    System.out.println("Enter Date(YYYY-MM-DD): ");
                    String date = sc.next();
                    ViewPlanByDate.viewAllPlan(date);

                }else if(ch == 4) {
                    break;

                }else if(ch == 5) {
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
            viewOptions();
        }

    }

}
