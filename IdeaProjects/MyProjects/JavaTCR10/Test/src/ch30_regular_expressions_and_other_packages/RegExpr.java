package ch30_regular_expressions_and_other_packages;

// Пример простого сопоставления с шаблоном.
import java.util.regex.*;

class RegExpr {
    public static void main(String args[]) {
        Pattern pat;
        Matcher mat;
        boolean found;

        pat = Pattern.compile("Java");
        mat = pat.matcher("Java");

        found = mat.matches(); // Проверить на совпадение.

        System.out.println("Проверка совпадения Java с Java.");
        if(found) System.out.println("Совпадает");
        else System.out.println("Не совпадает");

        System.out.println();

        System.out.println("Проверка совпадения Java с Java 9.");
        mat = pat.matcher("Java 9"); // Создать новый
                                // сопоставитель с шаблоном.

        found = mat.matches(); // Проверить на совпадение.

        if(found) System.out.println("Совпадает");
        else System.out.println("Не совпадает");
    }
}

