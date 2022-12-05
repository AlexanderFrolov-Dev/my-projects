package org.example;

import java.util.ArrayList;

public class Main {

    static ArrayList<String> array = new ArrayList<>();

    public static void main(String[] args) {
        for (String s : setIndexes(1000)) {
            System.out.println(s);
        }
    }

    // Корректно работает только с размером ячейки массива не больше чем в две буквы.
    private static ArrayList<String> setIndexes(int cellsCount) {
        final int NUMBER_OF_LETTERS_IN_ALPHABET = 26;  // Переменная количества букв в английском алфавите.
        final int INDEX_OF_A = 65;  // Переменная индекса символа 'A'(латинская буква) в ascii.
        char letter;  // Переменная для основной буквы.
        char additionalLetter;  // Переменная для дополнительной буквы.
        int count = array.size();  // Переменная, указывающая размер списка (для сокращения размера кода).

        // Пока размер массива меньше заданного в параметре числа, проверяем условия и рекурсивно вызываем этот же метод
        // из самого себя. Изначально массив создается пустым за пределами метода с размером 0.
        if (array.size() < cellsCount) {
            if (count < NUMBER_OF_LETTERS_IN_ALPHABET) {  // Если размер массива меньше кол-ва букв в алфавите, то
                letter = (char) (count + INDEX_OF_A);     // индекс буквы - размер массива + 65.
                array.add(String.valueOf(letter));  // Добавляем букву в массив.
            } else {
                // Иначе индекс буквы - остаток от деления размера массива на кол-во букв в алфавите (26) + 65. Индекс
                // дополнительной буквы - целое число от деления размера массива на кол-во букв в алфавите + 65.
                letter = (char) ((count % NUMBER_OF_LETTERS_IN_ALPHABET) + INDEX_OF_A);
                additionalLetter = (char) (((count / NUMBER_OF_LETTERS_IN_ALPHABET) - 1) + INDEX_OF_A);
                array.add(String.valueOf(additionalLetter) + letter);  // Добавляем буквы в массив.
            }
            setIndexes(cellsCount);  // Рекурсивный вызов метода самим же собой.
        }
        return array;
    }
}