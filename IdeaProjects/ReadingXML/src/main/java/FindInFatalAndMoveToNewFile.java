import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindInFatalAndMoveToNewFile {
    private static final String SOURCE_ROOT_FOLDER_PATH = "C:\\Users\\Andrey Pakhomenkov\\Desktop\\Список файлов со Смоленскими ЗАГСами\\20220906\\";
    private static final String TARGET_FOLDER_PATH = "C:\\Users\\Andrey Pakhomenkov\\Desktop\\Список файлов со Смоленскими ЗАГСами\\Список файлов\\";
    private static final Pattern FIRST_LINE_NEW_TYPE_FATAL = Pattern.compile("<FATALRequest:FATALRequest xmlns.+>");
    private static String firstLine;
    private static final String LAST_LINE_NEW_TYPE_FATAL = "</FATALRequest:FATALRequest>";
    private static ArrayList<String> filesNameList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ArrayList<File> fileList = new ArrayList<>();
        int fileNumber = 0;

        searchFiles(new File(SOURCE_ROOT_FOLDER_PATH), fileList);
        for (File file : fileList) {
            fileNumber++;
//            System.out.println(fileNumber + " - " + file.getName());
            if (!searchSmolenskZagsFile(getStringFromFile(file)).isEmpty()) {
                createFile(searchSmolenskZagsFile(getStringFromFile(file)), file);
            }
        }

        for (String s : filesNameList) {
            System.out.println(s);
        }
    }

    private static void createFile(List<String> blocksOfSeparateRecord, File sourceFile) {
        String targetPath = TARGET_FOLDER_PATH.concat("Smolensk_").concat(sourceFile.getName());
        int blocksCount = 0;

        try {
            FileWriter writer = new FileWriter(targetPath);
            if (!firstLine.isEmpty()) {
                writer.write(firstLine.concat(System.lineSeparator()).concat("\s\s"));
            }
            for (String s : blocksOfSeparateRecord) {
                writer.write(s.concat(System.lineSeparator()));
            }
            writer.write(LAST_LINE_NEW_TYPE_FATAL);
            filesNameList.add(sourceFile.getName());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getFirstLine(String str) {
        Matcher matcher = FIRST_LINE_NEW_TYPE_FATAL.matcher(str);

        if (matcher.find()) {
            firstLine = matcher.group();
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

        getFirstLine(str);

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


