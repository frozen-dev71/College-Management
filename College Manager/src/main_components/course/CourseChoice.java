package main_components.course;

import java.util.InputMismatchException;
import java.util.Scanner;

import exception.InputException;
import style.Style;

public class CourseChoice {

    public static void courseOptions() {


        try {

            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);

            while(true) {

                System.out.println(Style.CYAN+"1. Add Course");
                System.out.println("2. Update Course");
                System.out.println("3. Search Course");
                System.out.println("4. Delete Course");
                System.out.println("5. Back");
                System.out.println("6. Close"+Style.RESET);

                int ch = sc.nextInt();

                if(ch == 1) {
                    try {
                        CreateCourse.addCourceMtd();

                    } catch (InputException e) {
                        System.out.println();
                        System.out.println(e.getMessage());
                        System.out.println();

                    }

                }else if(ch == 2) {

                    while(true) {
                        System.out.println(Style.CYAN+"Enter name of Course"+Style.RESET);
                        String name = sc.next();

                        boolean flag = SearchCourseByName.searchByName(name);
                        if(flag) {
                            //UpdateCourse.updateCourse(name);
                            break;
                        }else {
                            System.out.println();
                            System.out.println(Style.RED+"Course Name Doesn't Exist!"+Style.RESET);
                            System.out.println();
                        }
                    }

                }else if(ch == 3) {
                    CourseSearchChoice.courseSearchOptions();

                }else if(ch == 4) {
                    try {
                        DeleteCourse.deleteCourse();
                    } catch (InputException e) {
                        System.out.println();
                        System.out.println(e.getMessage());
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
            System.out.println();
        }catch(InputMismatchException ie) {
            System.out.println();
            System.out.println(Style.RED+"Wrong Input Try Again!"+Style.RESET);
            System.out.println();
            courseOptions();

        }

    }

}
