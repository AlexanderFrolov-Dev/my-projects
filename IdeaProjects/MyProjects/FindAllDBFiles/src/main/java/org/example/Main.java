package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static LocalDate currentDate = LocalDate.now();
    private static String folderName = currentDate.getYear() + "_" + currentDate.getMonth().getValue();
    private static String folderPath = "C:\\Users\\Andrey Pakhomenkov\\Desktop\\" + folderName;
    private static ArrayList<File> fileList = new ArrayList<>();
    private static File file = new File(folderPath);
    private static int countCopy = 0;

    public static void main(String[] args) throws IOException {
        if (file.exists()) {
            System.out.println("Файл уже существует");
        } else {
            Files.createDirectory(Path.of(folderPath));
        }
        searchFiles(new File("O:\\Отдел информационного обеспечения\\Манухов\\Файлы регистра\\"), fileList);
    }

    private static void searchFiles(File rootFile, List<File> fileList) throws IOException {
        if (rootFile.isDirectory()) {
            System.out.println("Поиск в папке: " + rootFile.getAbsolutePath());
            File[] directoryFiles = rootFile.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {
                    if (file.isDirectory()) {
                        searchFiles(file, fileList);
                    } else {
                        if (file.getName().toLowerCase().endsWith(".fdb")) {
                            fileList.add(file);
                            if (!file.exists()) {
                                Files.copy(file.toPath(), Path.of(folderPath + "\\" + file.getName()));
                            } else {
                                System.out.println(file.getName());
                                countCopy++;
                                String[] splitStr = file.getName().split("\\.");
                                String copy = splitStr[0] + "-copy" + countCopy + "." + splitStr[1];
                                Files.copy(file.toPath(), Path.of(folderPath + "\\" + copy));
                            }
                        }
                    }
                }
            }
        }
    }
}