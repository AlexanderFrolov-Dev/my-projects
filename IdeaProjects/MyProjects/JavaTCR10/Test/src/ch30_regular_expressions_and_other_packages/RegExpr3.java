package ch30_regular_expressions_and_other_packages;

// Использовать метод find() для нахождения
// нескольких подпоследовательностей символов.
import java.util.regex.*;

class RegExpr3 {
    public static void main(String args[]) {
        Pattern pat = Pattern.compile("test");
        Matcher mat = pat.matcher("test 1 2 3 test");

        while(mat.find()) {
            System.out.println("Подпоследовательность test "
                    + "найдена по индексу " + mat.start());
        }
    }
}
