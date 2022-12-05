package ch30_regular_expressions_and_other_packages;

// Создание специального формата даты и времени.
import java.time.*;
import java.time.format.*;

class DateTimeDemo3 {
    public static void main(String args[]) {

        LocalDateTime curDateTime = LocalDateTime.now();
        System.out.println(curDateTime.format(
                DateTimeFormatter.ofPattern("MMMM d',' yyyy h':'mm a")));
    }
}