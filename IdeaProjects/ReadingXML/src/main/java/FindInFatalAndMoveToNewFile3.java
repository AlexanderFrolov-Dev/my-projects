import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindInFatalAndMoveToNewFile3 {
    private static final String SOURCE_ROOT_FOLDER_PATH = "C:\\Users\\Andrey Pakhomenkov\\Desktop\\20221114_1\\";
    private static final String TARGET_FOLDER_PATH = "C:\\Users\\Andrey Pakhomenkov\\Desktop\\20221114_1_3\\";
    private static final Pattern FIRST_LINE_NEW_FORMAT_FATAL = Pattern.compile("<FATALRequest:FATALRequest.+>");
    private static final Pattern FIRST_LINE_OLD_FORMAT_FATAL = Pattern.compile("<FATALRequest.+>");
    private static String firstLineNewFormat;
    private static String firstLineOldFormat;
    private static final String LAST_LINE_NEW_FORMAT_FATAL = "</FATALRequest:FATALRequest>";
    private static final String LAST_LINE_OLD_FORMAT_FATAL = "</FATALRequest>";
    private static final ArrayList<String> filesNameList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ArrayList<File> fileList = new ArrayList<>();
        int fileNumber = 0;

        searchFiles(new File(SOURCE_ROOT_FOLDER_PATH), fileList);
        for (File file : fileList) {
            fileNumber++;
//            System.out.println(fileNumber + " - " + file.getName());
            if (!searchSmolenskZagsFileWithNewFormat(getStringFromFile(file)).isEmpty()) {
                createFileWithNewFormat(searchSmolenskZagsFileWithNewFormat(getStringFromFile(file)), file);
            }
            if (!searchSmolenskZagsFileWithOldFormat(getStringFromFile(file)).isEmpty()) {
                createFileWithOldFormat(searchSmolenskZagsFileWithOldFormat(getStringFromFile(file)), file);
            }
        }

        for (String s : filesNameList) {
            System.out.println(s);
        }
    }

    private static void createFileWithNewFormat(List<String> blocksOfSeparateRecord, File sourceFile) {
        String targetPath = TARGET_FOLDER_PATH.concat(sourceFile.getName());
        int blocksCount = 0;

        try {
            FileWriter writer = new FileWriter(targetPath);
            if (!firstLineNewFormat.isEmpty()) {
                writer.write(firstLineNewFormat.concat(System.lineSeparator()));
            }
            for (String s : blocksOfSeparateRecord) {
                writer.write("\s\s".concat(s).concat(System.lineSeparator()));
            }
            writer.write(LAST_LINE_NEW_FORMAT_FATAL);
            filesNameList.add(sourceFile.getName());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createFileWithOldFormat(List<String> blocksOfSeparateRecord, File sourceFile) {
        String targetPath = TARGET_FOLDER_PATH.concat(sourceFile.getName());
        int blocksCount = 0;

        try {
            FileWriter writer = new FileWriter(targetPath);
            if (!firstLineOldFormat.isEmpty()) {
                writer.write(firstLineOldFormat.concat(System.lineSeparator()));
            }
            for (String s : blocksOfSeparateRecord) {
                writer.write("\s\s".concat(s).concat(System.lineSeparator()));
            }
            writer.write(LAST_LINE_OLD_FORMAT_FATAL);
            filesNameList.add(sourceFile.getName());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getFirstLineWithNewFormat(String str) {
        Matcher matcher = FIRST_LINE_NEW_FORMAT_FATAL.matcher(str);

        if (matcher.find()) {
            firstLineNewFormat = matcher.group();
        }
    }

    private static void getFirstLineWithOldFormat(String str) {
        Matcher matcher = FIRST_LINE_OLD_FORMAT_FATAL.matcher(str);

        if (matcher.find()) {
            firstLineOldFormat = matcher.group();
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

    private static List<String> searchSmolenskZagsFileWithNewFormat(String str) {
        List<String> listOfBlocksDeathNew = new ArrayList<>();
        List<String> blocksWithZagsesSmolenskFatalNew = new ArrayList<>();
        Pattern blockFatalNew = Pattern.compile("<FATALRequest:СведРегСмерт[\\S\\s]*?</FATALRequest:СведРегСмерт>");
        Matcher matchInFatalNew = blockFatalNew.matcher(str);

        getFirstLineWithNewFormat(str);

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

    private static List<String> searchSmolenskZagsFileWithOldFormat(String str) {
        List<String> listOfBlocksDeathOld = new ArrayList<>();
        List<String> blocksWithZagsesSmolenskFatalOld = new ArrayList<>();
        Pattern blockFatalOld = Pattern.compile("<СведРегСмерт[\\S\\s]*?</СведРегСмерт>");
        Matcher matchInFatalOld = blockFatalOld.matcher(str);

        getFirstLineWithOldFormat(str);

        while (matchInFatalOld.find()) {
            listOfBlocksDeathOld.add(matchInFatalOld.group());
        }

        for (String block : listOfBlocksDeathOld) {
            final Pattern fatalZagsSmolenskOld = Pattern.compile("<ОрганЗАГС.+Смоленск");
            Matcher matchInBlock = fatalZagsSmolenskOld.matcher(block);
            if (matchInBlock.find()) {
                blocksWithZagsesSmolenskFatalOld.add(block);
            }
        }

        return blocksWithZagsesSmolenskFatalOld;
    }
}




