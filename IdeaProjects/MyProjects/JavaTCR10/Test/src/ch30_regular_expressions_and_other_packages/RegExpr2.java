package ch30_regular_expressions_and_other_packages;

// Использовать метод find() для нахождения
// подпоследовательности символов.
import java.util.regex.*;

class RegExpr2 {
    public static void main(String args[]) {
        Pattern pat = Pattern.compile("Java");
        Matcher mat = pat.matcher("Java 9");

        System.out.println("Поиск Java в Java 9.");

        if(mat.find()) System.out.println("Подпоследовательность найдена.");
        else System.out.println("Совпадения отсутствуют.");
    }
}
