import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetListOfFatalFileNames {
    public static void main(String[] args) throws IOException {
        ArrayList<File> fileList = new ArrayList<>();
        ArrayList<String> fileNamesList = new ArrayList<>();
        int count = 0;

        searchFiles(new File("C:\\Users\\Andrey Pakhomenkov\\Desktop\\20220915fatal\\"), fileList);
        for (File file : fileList) {
            count++;
            System.out.println(count + " - " + file.getName());

            if (searchSmolenskZagsFile(file)) {
                fileNamesList.add(file.getName());
            }
        }

        System.out.println("fileNamesList size: " + fileNamesList.size());

        String targetPath = "C:\\Users\\Andrey Pakhomenkov\\Desktop\\Список файлов со Смоленскими ЗАГСами\\Список файлов.txt";

        try {
            FileWriter writer = new FileWriter(targetPath);
            int separatorCount = 0;
            for (int i = 0; i < fileNamesList.size(); i++) {
                separatorCount++;
                String s = fileNamesList.get(i);
                if (separatorCount < fileNamesList.size()) {
                    writer.write(s + System.lineSeparator());
                } else {
                    writer.write(s);
                }
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void searchFiles(File rootFile, List<File> fileList) {
        int count = 0;
        if (rootFile.isDirectory()) {
            System.out.println("searching at: " + rootFile.getAbsolutePath());
            File[] directoryFiles = rootFile.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {
                    if (file.isDirectory()) {
                        searchFiles(file, fileList);
                        System.out.println("Directory: " + file.getName());
                    } else {
                        if (file.getName().toLowerCase().endsWith(".xml")) {
                            count++;
                            fileList.add(file);
                            System.out.println("File" + count + ": " + file.getName());
                        }
                    }
                }
            }
        }
    }

    private static boolean searchSmolenskZagsFile(File file) throws IOException {
        FileInputStream fis;

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BufferedInputStream bis = new BufferedInputStream(fis);
        String str = IOUtils.toString(bis, "UTF-8");

        boolean find = false;

        Pattern fatalZagsSmolensk = Pattern.compile("<FATALRequest:ОрганЗАГС.+Смоленск");
        Matcher matcher = fatalZagsSmolensk.matcher(str);

        if (matcher.find()) {
            find = true;
        }

        return find;
    }
}
