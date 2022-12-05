package ch30_regular_expressions_and_other_packages;

// Простой пример применения
// классов LocalDate и LocalTime.
import java.time.*;

class DateTimeDemo {
    public static void main(String args[]) {

        LocalDate curDate = LocalDate.now();
        System.out.println(curDate);

        LocalTime curTime = LocalTime.now();
        System.out.println(curTime);

//        LocalDateTime curDateTime = LocalDateTime.now();
//        System.out.println(curDateTime);
//
//        LocalDate curDate2 = curDateTime.toLocalDate();
//        System.out.println(curDate2);
//
//        LocalTime curTime2 = curDateTime.toLocalTime();
//        System.out.println(curTime2);
    }
}
