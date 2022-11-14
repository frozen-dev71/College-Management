package administrator;

import java.util.Scanner;

import dao.AdminDao;
import dao.AdminDaoImpl;
import exception.AdminException;
import style.Style;

public class AdminLogin {

    public static void LoginAdmin(){

        @SuppressWarnings("resource")
        Scanner sc= new Scanner(System.in);
        int i=4;
        for(; i>=0; i--) {

            System.out.print(Style.CYAN+"Enter Username: "+Style.RESET);
            String uname = sc.next();

            System.out.print(Style.CYAN+"Enter Password: "+Style.RESET);
            String pass = sc.next();

            System.out.println();

            AdminDao dao = new AdminDaoImpl();

            try {
                boolean flag = dao.LoginAdmin(uname, pass);
                if(flag) {
                    AdminAccess.adminOption();
                    return;
                }

            }catch (AdminException e) {
                System.out.println();
                System.out.println(Style.RED_BACKGROUND+e.getMessage()+Style.RESET);
                System.out.println();
                System.out.println(Style.YELLOW+i+" Attempts Left.."+Style.RESET);
                System.out.println();
                while(true) {
                    System.out.print(Style.GREEN+"Want to try again?(y/n) : "+Style.RESET);
                    String choice = sc.next();

                    if(choice.equalsIgnoreCase("y")) {
                        break;
                    }else if(choice.equalsIgnoreCase("n")){
                        return;
                    }else {
                        System.out.println();
                        System.out.println(Style.RED+"Wrong Input...!"+Style.RESET);
                        System.out.println();
                    }
                }
            }
        }
        System.out.println();
        System.out.println(Style.YELLOW+"Try Again After Some Time.."+Style.RESET);
        System.out.println();
    }

}
