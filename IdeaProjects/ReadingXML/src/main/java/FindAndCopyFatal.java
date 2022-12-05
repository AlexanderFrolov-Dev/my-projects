import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindAndCopyFatal {
    private static final String SOURCE_ROOT_FOLDER_PATH = "C:\\Users\\Andrey Pakhomenkov\\Desktop\\20221017\\";
    private static final String TARGET_FOLDER_PATH = "C:\\Users\\Andrey Pakhomenkov\\Desktop\\20221017-2\\";

    public static void main(String[] args) throws IOException {
        ArrayList<File> fileList = new ArrayList<>();
        int fileNumber = 0;

        searchFiles(new File(SOURCE_ROOT_FOLDER_PATH), fileList);
        for (File file : fileList) {
            fileNumber++;
//            System.out.println(fileNumber + " - " + file.getName());
            if (!searchSmolenskZagsFile(getStringFromFile(file)).isEmpty()) {
                Files.copy(file.toPath(), new File(TARGET_FOLDER_PATH.concat(file.getName())).toPath());
            }
        }
    }

    private static String getStringFromFile(File file) throws IOException {
        FileInputStream fis;

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BufferedInputStream bis = new BufferedInputStream(fis);

        return IOUtils.toString(bis, "UTF-8");
    }

    private static void searchFiles(File rootFile, List<File> fileList) {
        int xmlFileNumber = 0;
        if (rootFile.isDirectory()) {
//            System.out.println("searching at: ".concat(rootFile.getAbsolutePath()));
            File[] directoryFiles = rootFile.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {
                    if (file.isDirectory()) {
                        searchFiles(file, fileList);
                    } else {
                        if (file.getName().toLowerCase().endsWith(".xml")) {
                            xmlFileNumber++;
                            fileList.add(file);
//                            System.out.println("File" + xmlFileNumber + ": " + file.getName());
                        }
                    }
                }
            }
        }
    }

    private static List<String> searchSmolenskZagsFile(String str) {
        List<String> listOfBlocksDeathNew = new ArrayList<>();
        List<String> blocksWithZagsesSmolenskFatalNew = new ArrayList<>();
        Pattern blockFatalNew = Pattern.compile("<FATALRequest:СведРегСмерт[\\S\\s]*?</FATALRequest:СведРегСмерт>");
        Matcher matchInFatalNew = blockFatalNew.matcher(str);

        while (matchInFatalNew.find()) {
            listOfBlocksDeathNew.add(matchInFatalNew.group());
        }

        for (String block : listOfBlocksDeathNew) {
            final Pattern fatalZagsSmolenskNew = Pattern.compile("<FATALRequest:ОрганЗАГС.+Смоленск");
            Matcher matchInBlock = fatalZagsSmolenskNew.matcher(block);
            if (matchInBlock.find()) {
                blocksWithZagsesSmolenskFatalNew.add(block);
            }
        }

        return blocksWithZagsesSmolenskFatalNew;
    }
}


