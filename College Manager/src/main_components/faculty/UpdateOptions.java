package main_components.faculty;


import java.util.Scanner;

import style.Style;



public class UpdateOptions {

    public static void updateOptions() {

        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        while(true) {

            System.out.println(Style.CYAN+"1. Update By Id");
            System.out.println("2. Update By Name");
            System.out.println("3. Back");
            System.out.println("4. Close" +Style.RESET);

            int ch = sc.nextInt();

            if(ch == 1) {
                try {
                    System.out.println(Style.CYAN+"Enter id of Faculty"+Style.RESET);
                    int id = sc.nextInt();

                    UpdateFaculty.UpdateById(id);
                }catch(Exception e) {
                    System.out.println();
                    System.out.println(Style.RED+"Wrong Input Try Again"+Style.RESET);
                    System.out.println();
                    updateOptions();
                }

            }else if(ch == 2) {
                if(SearchFacultyByName.searchByName()) {

                    try {
                        System.out.println(Style.CYAN+"Enter id of Faculty"+Style.RESET);
                        int id = sc.nextInt();
                        UpdateFaculty.UpdateById(id);
                    }catch(Exception e) {
                        System.out.println();
                        System.out.println(Style.RED+"Wrong Input Try Again"+Style.RESET);
                        System.out.println();
                        updateOptions();
                    }
                }else {
                    updateOptions();
                }

            }else if(ch== 4) {
                System.out.println(Style.BANANA_YELLOW+"See You Soon..."+Style.RESET);
                System.exit(0);

            }else if(ch == 3) {
                break;

            }else {

                System.out.println(Style.RED+"Wrong Input Try Again"+Style.RESET);
            }

        }


    }

}
