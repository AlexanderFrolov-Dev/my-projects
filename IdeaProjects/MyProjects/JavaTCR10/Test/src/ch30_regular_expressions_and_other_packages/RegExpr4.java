package ch30_regular_expressions_and_other_packages;

// Применить квантор.
import java.util.regex.*;

class RegExpr4 {
    public static void main(String args[]) {
        Pattern pat = Pattern.compile("W+");
        Matcher mat = pat.matcher("W WW WWW");

        while(mat.find())
            System.out.println("Совпадение: " + mat.group());
    }
}
