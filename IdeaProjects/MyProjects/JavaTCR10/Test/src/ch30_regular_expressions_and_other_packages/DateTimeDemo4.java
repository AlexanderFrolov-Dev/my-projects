package ch30_regular_expressions_and_other_packages;

// Пример синтаксического анализа даты и времени.
import java.time.*;
import java.time.format.*;

class DateTimeDemo4 {
    public static void main(String args[]) {

        // Получить объект типа LocalDateTime,
        // выполнив синтаксический анализ символьной
        // строки даты и времени.
        LocalDateTime curDateTime =
                LocalDateTime.parse("мая 25, 2022 2:12 PM",
                        DateTimeFormatter.ofPattern("MMMM d',' yyyy h':'mm a"));

        // А теперь отобразить проанализированные дату и время.
        System.out.println(curDateTime.format(
                DateTimeFormatter.ofPattern("MMMM d',' yyyy h':'mm a")));
    }
}