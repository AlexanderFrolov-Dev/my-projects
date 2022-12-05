import java.util.ArrayList;

public class Calculation {
    static public ArrayList<Integer> getNumbersDivisibleBy3And5() {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i <= 1000; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                result.add(i);
            }
        }
        return result;
    }

    static public ArrayList<String> convertNumbersToString(ArrayList<Integer> integerArrayList) {
        ArrayList<String> result = new ArrayList<>();
        String str = "";

        for (Integer number : integerArrayList) {
            str = number.toString();
            result.add(str);
        }

        return result;
    }

    static public ArrayList<Integer> getResultList(ArrayList<String> stringArrayList) {
        ArrayList<Integer> result = new ArrayList<>();

        for (String str : stringArrayList) {
            result.add(checkSumDigitsOfNumberLess10(str));
        }
        return result;
    }

    static public int checkSumDigitsOfNumberLess10(String str) {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        int currentDigit;
        int total = 0;

        if(str.length() < 1) {
            return Integer.parseInt(str);
        } else {
            char[] ch = str.toCharArray();
            for (int i = 0; i < ch.length; i++) {
                currentDigit = Character.getNumericValue(ch[i]);
                total += currentDigit;
            }
            return total;
        }
    }
}
