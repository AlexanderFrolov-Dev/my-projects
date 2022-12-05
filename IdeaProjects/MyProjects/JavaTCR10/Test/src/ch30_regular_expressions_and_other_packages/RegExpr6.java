package ch30_regular_expressions_and_other_packages;

// Применить квантор ?
import java.util.regex.*;

class RegExpr6 {
    public static void main(String args[]) {
        // Составить шаблон для нестрогого совпадения.
        Pattern pat = Pattern.compile("e.+?d");
//        Pattern pat = Pattern.compile("e.+?d{0,6}");
        Matcher mat = pat.matcher("extend cup end table");
//        Matcher mat = pat.matcher("extend cup end expend table");

        while(mat.find())
            System.out.println("Совпадение: " + mat.group());
    }
}
