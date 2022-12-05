package org.example;

// Урок 2.6
public class Main {
    public static void main(String[] args) {
        int coffeeAmount = 1000;
        int milkAmount = 3210;
        int skimmedMilkAmount = 1000;

        boolean isBlocked = true;

        int cappucinoMilkRequired = 60;
        int cappucinoCoffeeRequired = 15;

        boolean milkIsEnough = skimmedMilkAmount >= cappucinoMilkRequired ||
                milkAmount >= cappucinoMilkRequired;

        boolean coffeeIsEnough = coffeeAmount >= cappucinoCoffeeRequired;

        boolean hasErrors = false;  // Начальное значение переменной при инициализации hasErrors = false.

        if (isBlocked) {
            System.out.println("Кофе-машина заблокирована");
            hasErrors = true;  // Переменной присваивается новое значение. Теперь hasErrors = true.
        }

        if (!coffeeIsEnough) {
            System.out.println("Кофе недостаточно");
            hasErrors = true;  // Переменной присваивается новое значение. Теперь hasErrors = true.
        }

        if (!milkIsEnough) {
            System.out.println("Молока недостаточно");
            hasErrors = true;  // Переменной присваивается новое значение. Теперь hasErrors = true.
        }

        if (!hasErrors) {  // Здесь сравнивается с hasErrors. А hasErrors = true.
            System.out.println("Готовим кофе");
        }

        System.out.println(!hasErrors);
        System.out.println("end");
    }
}