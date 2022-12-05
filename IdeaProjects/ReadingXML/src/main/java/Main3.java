import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main3 {
    public static void main(String[] args) throws IOException {
        ArrayList<File> fileList = new ArrayList<>();
        ArrayList<File> contentSmolenskZags = new ArrayList<>();
        int count = 0;

        searchFiles(new File("X:\\PENSION\\upravlen\\ЗАГС\\2022_god\\09\\"), fileList);
//        System.out.println(fileList.size());
        for (File file : fileList) {
            count++;
            System.out.println(count + " - " + file.getName());

            if (!searchSmolenskZagsFile(file).isEmpty()) {
                contentSmolenskZags.add(file);
            }
        }

        System.out.println(fileList.size());
        System.out.println(contentSmolenskZags.size());
    }

    private static void searchFiles(File rootFile, List<File> fileList) {
        if (rootFile.isDirectory()) {
            System.out.println("searching at: " + rootFile.getAbsolutePath());
            File[] directoryFiles = rootFile.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {

                    if (file.isDirectory()) {
                        searchFiles(file, fileList);
                    } else {
                        if (file.getName().toLowerCase().endsWith(".xml")) {
                            fileList.add(file);
                        }
                    }
                }
            }
        }
    }

    private static List<String> searchSmolenskZagsFile(File file) throws IOException {
        FileInputStream fis;

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BufferedInputStream bis = new BufferedInputStream(fis);
        String str = IOUtils.toString(bis, "UTF-8");

//        System.out.println(str);

//        System.out.println(str.startsWith("<FATALRequest"));

        Pattern firstLineOldType = Pattern.compile("<FATALRequest xmlns:FATALRequest.+>");

        Pattern blockDeath = Pattern.compile("<СведРегСмерт[\\S\\s]*?</СведРегСмерт>");
        Matcher matchInDeath = blockDeath.matcher(str);

        Pattern blockFatalDeath = Pattern.compile("<FATALRequest:СведРегСмерт[\\S\\s]*?</СведРегСмерт>");
        Matcher matchInFatalDeath = blockDeath.matcher(str);

        List<String> listOfBlocksDeath = new ArrayList<>();

        while (matchInDeath.find()) {
            listOfBlocksDeath.add(matchInDeath.group());
        }

//        System.out.println(listOfBlocksDeath.size());

        List<String> blocksWithZagsesSmolensk = new ArrayList<>();
        Pattern zagsSmolensk = Pattern.compile("<ОрганЗАГС.+Смоленск");
        Pattern fatalZagsSmolensk = Pattern.compile("<FATALRequest:ОрганЗАГС.+Смоленск");

        for (String block : listOfBlocksDeath) {
            Matcher matchInBlock = zagsSmolensk.matcher(block);
            if (matchInBlock.find()) {
                blocksWithZagsesSmolensk.add(block);
            }
        }

//        System.out.println(blocksWithZagsesSmolensk.size());
//
//        for (String s : blocksWithZagsesSmolensk) {
//            System.out.println(s);
//            System.out.println("=====================================================================================");
//        }

        return blocksWithZagsesSmolensk;
    }
}

