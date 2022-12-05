// FactorialCalculator

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int value = -1;
        int multiplier = 1;
        final String exclamationMark = "!";
        String multiplyBy = "";

        System.out.println("Введите число:");

        while (value != 0) {
            value = new Scanner(System.in).nextInt();
            for (int i = 1; i <= value; i++) {
                multiplier *= i;
                multiplyBy += multiplyBy.equals("") ? "" + i : " * " + i;
            }

            if (value != 0) {
                System.out.println(exclamationMark + value + " = " + multiplyBy + " = " + multiplier);
            }

            multiplyBy = "";
            multiplier = 1;
        }

        System.out.println("Программа закончила своё выполнение");
    }
}
