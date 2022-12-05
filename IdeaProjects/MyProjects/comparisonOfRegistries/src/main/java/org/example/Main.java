package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    private static String textOfFirstFile = "";
    private static String textOfSecondFile = "";
    private static File firstFile = new File("O:\\Отдел информационного обеспечения\\Falezhinskiy\\REG\\001  начало не работает.reg");
    private static File secondFile = new File("O:\\Отдел информационного обеспечения\\Falezhinskiy\\REG\\002  работает.reg");

    public static void main(String[] args) throws IOException {
        textOfFirstFile = readFromFile(firstFile);
//        textOfSecondFile = readFromFile(secondFile);

//        System.out.println(textOfFirstFile.length());
    }

    private static String readFromFile(File file) throws IOException {
        String fullText = "";
        try (FileInputStream fin = new FileInputStream(file)) {
            StringBuilder lineBuilder = new StringBuilder();

            int symbol;

            try {
                do {
                    symbol = fin.read();
                    if (symbol != -1) {
                        lineBuilder.append((char) symbol);
                        fullText = String.valueOf(lineBuilder);
                        System.out.println((char) symbol);
                    }
                } while (symbol != -1);
            } catch (IOException e) {
                System.out.println("Ошибка чтения из файла.");
            }
        }

        return fullText;
    }
}