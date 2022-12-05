import java.util.ArrayList;

public class Numbers {
    static public ArrayList<Integer> getNumbersList() {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();
        ArrayList<String> totalStringArrayList = new ArrayList<>();
        ArrayList<Integer> totalIntegerArrayList = new ArrayList<>();

        for (int i = 0; i <= 1000; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                integerArrayList.add(i);
            }
        }

        for (Integer integer : integerArrayList) {
            String str = integer.toString();
            stringArrayList.add(str);
        }

        for (String str : stringArrayList) {
            int total = 0;
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int digit = Character.getNumericValue(chars[i]);
                total += digit;
            }
            if (total < 10) {
                totalStringArrayList.add(str);
            }
        }

        for (String str : totalStringArrayList) {
            totalIntegerArrayList.add(Integer.parseInt(str));
        }

        return totalIntegerArrayList;
    }
}
