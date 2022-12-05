package ch30_regular_expressions_and_other_packages;

// Применить метод replaceAll().
import java.util.regex.*;

class RegExpr8 {
    public static void main(String args[]) {
        String str = "Jon Jonathan Frank Ken Todd";

        Pattern pat = Pattern.compile("Jon.*? ");
        Matcher mat = pat.matcher(str);

        System.out.println("Исходная последовательность: " + str);

        str = mat.replaceAll("Eric ");

        System.out.println("Измененная последовательность: " + str);

    }
}