package ch30_regular_expressions_and_other_packages;

// Применить класс символов.
import java.util.regex.*;

class RegExpr7 {
    public static void main(String args[]) {
        // Составить шаблон для сопоставления со словами,
        // набранными строчными буквами.
        Pattern pat = Pattern.compile("[a-z]+");
        Matcher mat = pat.matcher("this is a test.");

        while(mat.find())
            System.out.println("Совпадение: " + mat.group());
    }
}
