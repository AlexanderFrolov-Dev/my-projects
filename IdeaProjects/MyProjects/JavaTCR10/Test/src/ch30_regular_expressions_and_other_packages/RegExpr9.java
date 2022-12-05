package ch30_regular_expressions_and_other_packages;

// Использовать метод split().
import java.util.regex.*;

class RegExpr9 {
    public static void main(String args[]) {

        // Составить шаблон для сопоставления со словами,
        // набранными строчными буквами.
        Pattern pat = Pattern.compile("[ ,.!]");

        String strs[] = pat.split("one two,alpha9 12!done.");

        for(int i=0; i < strs.length; i++)
            System.out.println("Следующая лексема: " + strs[i]);

    }
}