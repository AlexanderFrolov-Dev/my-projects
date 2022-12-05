import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyMain3 {
    public static void main(String[] args) {
        int count = 0;
        String wrongExample = "gfsgfgntyu -= +";
        String digits = "6556459432";
        String commas = ",,,,,,,,,";
        String spaces = "     ";
        String digitsAndCommas = "5441,,,4551,6487,4848,,163320";
        String digitsAndCommasAndSpaces = " 54 41, ,,4551,6487,4848 ,,16332 0 ";

        Pattern pattern = Pattern.compile("\\d+|,+| ");
        Pattern validate = Pattern.compile("\\s*(\\d+,+\\s*)+\\s*");
        Matcher matcher1 = pattern.matcher(wrongExample);
        Matcher matcher2 = pattern.matcher(digits);
        Matcher matcher3 = pattern.matcher(commas);
        Matcher matcher4 = pattern.matcher(spaces);
        Matcher matcher5 = pattern.matcher(digitsAndCommas);
        Matcher matcher6 = pattern.matcher(digitsAndCommasAndSpaces);

        while (matcher5.find()) {
            System.out.println(count + " - " + matcher5.group());
            count++;
        }
        System.out.println(count);
    }
}
