package main_components.faculty;

import java.util.Scanner;

import exception.InputException;
import style.Style;

public class SearchOptions {

    public static void searchOptions() {

        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        while(true) {

            System.out.println(Style.CYAN+"1. Search By Faculty Id");
            System.out.println("2. Search By Name");
            System.out.println("3. View All Faculty");
            System.out.println("4. Back" );
            System.out.println("5. Close"+Style.RESET);

            int ch = sc.nextInt();

            if(ch == 1) {

                try {
                    SearchFacultyByInt.searchById();
                } catch (InputException e) {
                    System.out.println();
                    System.out.println(e.getMessage());
                    System.out.println();
                }

            }else if(ch == 2) {

                SearchFacultyByName.searchByName();

            }else if(ch == 3) {

                AllFaculties.viewAll();

            }else if(ch == 4) {

                break;

            }else if(ch == 5) {
                System.out.println();
                System.out.println(Style.BANANA_YELLOW+"See You Soon..."+Style.RESET);
                System.exit(0);

            }
            else {
                System.out.println();
                System.out.println(Style.RED+"Wrong Input Try Again"+Style.RESET);
                System.out.println();
            }
            System.out.println();
        }


    }

}
