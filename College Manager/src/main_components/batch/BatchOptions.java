package main_components.batch;

import java.util.InputMismatchException;
import java.util.Scanner;

import exception.FacultyException;
import exception.InputException;
import style.Style;

public class BatchOptions {

    public static void facultyOptions() {

        try {
            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);

            while(true) {

                System.out.println(Style.CYAN+"1. Add Batch");
                System.out.println("2. Allocate Faculty");
                System.out.println("3. Update Batch");
                System.out.println("4. Search Batch");
                System.out.println("5. Delete Batch");
                System.out.println("6. Back");
                System.out.println("7. Close"+Style.RESET);

                int ch = sc.nextInt();

                if(ch == 1) {
                    try {
                        CreateBatch.addBatchMtd();
                    } catch (InputException e) {
                        System.out.println();
                        System.out.println(e.getMessage());
                        System.out.println();
                    }
                }
                else if(ch == 2) {
                    try {
                        AllocateFaculty.allocateFaculty();
                    } catch (FacultyException e) {
                        System.out.println();
                        System.out.println(e.getMessage());
                        System.out.println();
                    }

                }
                else if(ch == 3) {

                    System.out.println(Style.CYAN+"Enter name of BatchId"+Style.RESET);
                    String batchId = sc.next();

                    UpdateBatch.updateCourse(batchId);

                }else if(ch == 4) {
                    SearchBatchChoice.batchSearchOptions();

                }else if(ch == 5) {
                    try {
                        DeleteBatch.deleteBatch();
                    } catch (InputException e) {
                        System.out.println();
                        System.out.println(e.getMessage());
                        System.out.println();
                    }

                }else if(ch == 6) {
                    break;

                }else if(ch == 7) {
                    System.out.println();
                    System.out.println(Style.GREEN_BOLD_BRIGHT+"See You Soon..."+Style.RESET);
                    System.exit(0);

                }else {
                    System.out.println();
                    System.out.println(Style.RED+"Wrong Input Try Again!"+Style.RESET);
                    System.out.println();

                }

            }
        }catch(InputMismatchException ie) {
            System.out.println();
            System.out.println(Style.RED+"Wrong Input Try Again!"+Style.RESET);
            System.out.println();
            facultyOptions();

        }

    }

}
