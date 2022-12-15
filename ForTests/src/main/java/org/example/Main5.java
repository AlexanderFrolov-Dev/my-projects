package org.example;

import java.util.*;

public class Main5 {
    public static void main(String[] args) {
        int count = 0;
        List<Integer> lists = getReversedList(getList());
        long start = System.currentTimeMillis();
        for (Integer integer : lists) {
            count++;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        // 107 : 17
        // 133 : 17
    }

    public static List<Integer> getList() {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            integerList.add(i);
        }
        return integerList;
    }

    public static List<Integer> getReversedList(List<Integer> list) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            integerList.add(list.get(i));
        }
        return integerList;
    }
}
