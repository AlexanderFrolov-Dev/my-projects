package org.example;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main4 {
    public static void main(String[] args) throws IOException {

        // Классический способ чтения файла построчно, но нужно указать кодировку, чтобы распознавались "ё", "ь" и т. п.
        List<String> stringList = Files.readAllLines(Paths.get("C:\\Users\\Andrey Pakhomenkov\\Desktop\\ЕДВ 3-7.csv"), Charset.forName("cp1251"));
        System.out.println("=========================================================================================");
        for (String s : stringList) {
            System.out.println(s);
        }
        System.out.println("=========================================================================================");

        // Чтение файла построчно с помощью подключения библиотеки.
        List<String[]> personData = new ArrayList<>();
        List<String> fioAndBd = new ArrayList<>();
        Map<String, String> fioBd = new TreeMap<>();
        FileInputStream fis = new FileInputStream("C:\\Users\\Andrey Pakhomenkov\\Desktop\\ЕДВ 3-7.csv");

        String text = IOUtils.toString(fis, "cp1251");
        List<String> rows = Arrays.asList(text.split("\\n"));

        for (String s : rows) {
            List<String> elements = Arrays.asList(s.split(";"));

            for (int i = 0; i < elements.size(); i++) {
                if (i == 4) {
                    personData.add(elements.get(i).split(" "));
                }
            }
        }

        for (String[] array : personData) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; i++) {
                if (i <= 3) {
                    sb.append(array[i]).append(" ");
                }
            }
            fioAndBd.add(sb.toString().replace("(", "").replace(")", "").trim());
            sb.delete(0, sb.length());
        }

        fioAndBd.forEach(System.out::println);

        for (String s : fioAndBd) {
            int lastSpaceIndex = s.lastIndexOf(" ");
            fioBd.put(s.substring(0, lastSpaceIndex + 1).trim(), s.substring(lastSpaceIndex + 1));
        }

        for (Map.Entry<String, String> entry : fioBd.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
