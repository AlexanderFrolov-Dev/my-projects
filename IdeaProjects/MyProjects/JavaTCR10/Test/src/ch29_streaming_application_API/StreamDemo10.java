package ch29_streaming_application_API;

// Продемонстрировать применение метода trySplit().

import java.util.*;
import java.util.stream.*;

class StreamDemo10 {

    public static void main(String[] args) {

        // Создать список символьных строк.
        ArrayList<String> myList = new ArrayList<>( );
        myList.add("Alpha");
        myList.add("Beta");
        myList.add("Gamma");
        myList.add("Delta");
        myList.add("Phi");
        myList.add("Omega");

        // Получить итератор-разделитель.
        Stream<String> myStream = myList.stream();

        // А теперь разделить первый итератор.
        Spliterator<String> splitItr = myStream.spliterator();

        // Использовать сначала итератор splitItr2,
        // если нельзя разделить итератор splitItr.
        Spliterator<String> splitItr2 = splitItr.trySplit();

        // If splitItr could be split, use splitItr2 first.
        if(splitItr2 != null) {
            System.out.println("Результат, выводимый итератором splitItr2: ");
            splitItr2.forEachRemaining((n) -> System.out.println(n));
        }

        // А теперь воспользоваться итератором splitItr.
        System.out.println("\nРезультат, выводимый итератором: ");
        splitItr.forEachRemaining((n) -> System.out.println(n));
    }
}

