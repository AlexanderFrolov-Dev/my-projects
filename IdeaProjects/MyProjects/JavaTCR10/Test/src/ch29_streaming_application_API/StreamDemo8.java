package ch29_streaming_application_API;

// Применить итератор в потоке данных.

import java.util.*;
import java.util.stream.*;

class StreamDemo8 {

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

        // Получить итератор для потока данных.
        Iterator<String> itr = myStream.iterator();

        // Перебрать элементы в потоке данных.
        while(itr.hasNext())
            System.out.println(itr.next());
    }
}
