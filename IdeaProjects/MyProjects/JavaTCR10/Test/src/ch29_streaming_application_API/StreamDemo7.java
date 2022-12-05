package ch29_streaming_application_API;

// Использовать метод collect() для создания
// списка типа List и множества типа Set из потока данных.

import java.util.*;
import java.util.stream.*;

class NamePhone2Email2 {
    String name;
    String phonenum;
    String email;

    NamePhone2Email2(String n, String p, String e) {
        name = n;
        phonenum = p;
        email = e;
    }
}

class NamePhone2 {
    String name;
    String phonenum;

    NamePhone2(String n, String p) {
        name = n;
        phonenum = p;
    }
}

class StreamDemo7 {

    public static void main(String[] args) {

        // Список имен, номеров телефонов и
        // адресов электронной почты.
        ArrayList<NamePhone2Email2> myList = new ArrayList<>( );

        myList.add(new NamePhone2Email2("Larry", "555-5555",
                "Larry@HerbSchildt.com"));
        myList.add(new NamePhone2Email2("James", "555-4444",
                "James@HerbSchildt.com"));
        myList.add(new NamePhone2Email2("Mary", "555-3333",
                "Mary@HerbSchildt.com"));

        // Отобразить только имена и номера телефонов
        // на новый поток данных.
        Stream<NamePhone2> nameAndPhone = myList.stream().map(
                (a) -> new NamePhone2(a.name,a.phonenum)
        );

        // Вызвать метод collect(), чтобы составить
        // список типа List из имен и номеров телефонов.
        List<NamePhone2> npList = nameAndPhone.collect(Collectors.toList());

        System.out.println("Имена и номера телефонов в списке типа List:");
        for(NamePhone2 e : npList)
            System.out.println(e.name + ": " + e.phonenum);

        // Получить другое отображение имен и номеров телефонов.
        nameAndPhone = myList.stream().map(
                (a) -> new NamePhone2(a.name,a.phonenum)
        );

        // А теперь создать множество типа Set,
        // вызвав метод collect().
        Set<NamePhone2> npSet = nameAndPhone.collect(Collectors.toSet());

        System.out.println("\nИмена и номера телефонов "
                + "в множестве типа Set: ");
        for(NamePhone2 e : npSet)
            System.out.println(e.name + ": " + e.phonenum);
    }
}
