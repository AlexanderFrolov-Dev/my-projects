package ch30_regular_expressions_and_other_packages;

// Применить метасимвол и квантор.
import java.util.regex.*;

class RegExpr5 {
    public static void main(String args[]) {
        Pattern pat = Pattern.compile("e.+d");
        Matcher mat = pat.matcher("extend cup end table");

        while(mat.find())
            System.out.println("Совпадение: " + mat.group());
    }
}
