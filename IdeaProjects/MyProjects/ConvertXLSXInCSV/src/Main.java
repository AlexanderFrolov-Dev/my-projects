import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Задание: написать код, который выводит все числа от 0 до 1000,
 * кратные 3 и не кратные 5. Сумма цифр чисел должна быть меньше 10.
 */

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
//        ArrayList<Integer> numbersList = Calculation.getNumbersDivisibleBy3And5();
//        ArrayList<Integer> resultList = Calculation.getNumbersGivingTotalOf10(numbersList);
//
//        System.out.println(numbersList);
//
//        System.out.println(resultList);

        for (int i = 0; i < 1000; i++) {
            if (i % 3 == 0 && i % 5 != 0 && checkSum(i) < 10) {
//                System.out.println(i);
                integerArrayList.add(i);
            }
        }

//        int i = 1;
//        char ch = (char) i;
//        System.out.println(ch);

        System.out.println(Numbers.getNumbersList());
        System.out.println(integerArrayList);



//        Demo.task();
    }

    private static int checkSum(int i) {
        if (i < 10) {
            return i;
        } else {
            return i % 10 + checkSum(i / 10);
        }
    }
}

class Demo {
    public static void task() {
        IntStream.rangeClosed(0, 1000)
                .filter(i -> i % 3 == 0 && i % 5 != 0)
                .forEach(i -> {
                    String s = String.valueOf(i);
                    int sum = s.chars()
                            .map(C -> Character.digit(C, 10))
                            .sum();
                    if (sum < 10) {
                        System.out.println(i);
                    }
                });

    }
}
