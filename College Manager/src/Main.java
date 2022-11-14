import style.Style;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        System.out.println(Style.TEAL+"\n  College Management - Course Monitoring System   "+" "+Style.RESET);
        System.out.println();


        Scanner sc = new Scanner(System.in);

        while(true) {

            System.out.println(Style.RED_BRIGHT+"1. Admin Login");
            System.out.println("2. Faculty Login");
            System.out.println("3. Exit\n"+Style.RESET);

            String ch = sc.next();

            if(ch.equals("1")) {


            }else if(ch.equals("2")) {


            }else if(ch.equals("3")) {
                System.out.println();
                System.out.println(Style.GREEN_BOLD_BRIGHT+"Come Back Again."+Style.RESET);
                break;

            }else {
                System.out.println();
                System.out.println(Style.RED+"Invalid Input Please Try Again!"+Style.RESET);
                System.out.println();

            }

        }
        sc.close();

    }
}