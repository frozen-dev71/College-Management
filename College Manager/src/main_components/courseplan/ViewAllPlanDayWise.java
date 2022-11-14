package main_components.courseplan;

import java.util.List;

import dao.CoursePlanDao;
import dao.CoursePlanDaoImpl;
import exception.CoursePlanException;
import style.Style;
import model.CoursePlan;

public class ViewAllPlanDayWise {

    public static void viewAllPlan() {

        CoursePlanDao dao = new CoursePlanDaoImpl();

        try {
            List<CoursePlan> plans = dao.viewAllCoursePlanDateWise();

            System.out.println();
            System.out.println(Style.TEAL+"|--------------------------------------------------------------------------|");
            System.out.printf("%8s %10s %6s %20s %10s %10s", " PLAN ID |", "BATCH ID |", "DAY NO |", "TOPIC |", "PLAN DATE |", "STATUS |");
            System.out.println();
            System.out.println("|--------------------------------------------------------------------------|");

            plans.forEach(e -> {
                System.out.printf("%5s %13s %6s %20s %14s %8s",  e.getPlanId(), e.getBatchId(), e.getDaynumber(), e.getTopic(), e.getDate(), e.isStatus());
                System.out.println();
            });
            System.out.println("|--------------------------------------------------------------------------|");
            System.out.println();

        } catch (CoursePlanException e) {
            System.out.println();
            System.out.println(Style.RED_BACKGROUND+ e.getMessage()+Style.RESET);
            System.out.println();

        }

    }

}
