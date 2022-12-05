package ch29_streaming_application_API;

// Применить итератор-разделитель в потоке данных.

import java.util.*;
import java.util.stream.*;

class StreamDemo9 {

    public static void main(String[] args) {

        // Создать список символьных строк.
        ArrayList<String> myList = new ArrayList<>( );
        myList.add("Alpha");
        myList.add("Beta");
        myList.add("Gamma");
        myList.add("Delta");
        myList.add("Phi");
        myList.add("Omega");

        // Получить поток данных для списочного массива.
        Stream<String> myStream = myList.stream();

        // Получить итератор-разделитель.
        Spliterator<String> splitItr = myStream.spliterator();

        // Перебрать элементы в потоке данных.
        while(splitItr.tryAdvance((n) -> System.out.println(n)));
    }
}
