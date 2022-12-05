import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main5 {
    private static final Pattern FIRST_LINE_OLD_TYPE_FATAL = Pattern.compile("<FATALRequest xmlns:FATALRequest.+>");
    private static final Pattern FIRST_LINE_NEW_TYPE_FATAL = Pattern.compile("<FATALRequest:FATALRequest xmlns.+>");
    private static final Pattern FIRST_LINE_OLD_TYPE_PER_NAME = Pattern.compile("<PERNAMERequest xmlns:PERNAMERequest.+>");
    private static final Pattern FIRST_LINE_OLD_TYPE_ROGD = Pattern.compile("<ns2:ROGDRequest xmlns:ns2.+>");
    private static final String LAST_LINE_OLD_TYPE_FATAL = "</FATALRequest>";
    private static final String LAST_LINE_NEW_TYPE_FATAL = "</FATALRequest:FATALRequest>";
    private static final String LAST_LINE_OLD_TYPE_PER_NAME = "</PERNAMERequest>";
    private static final String LAST_LINE_OLD_TYPE_ROGD = "</ns2:ROGDRequest>";

    private final static Pattern ZAGS_SMOLENSK_OLD = Pattern.compile("<ОрганЗАГС.+Смоленск");
    private final static Pattern FATAL_ZAGS_SMOLENSK_NEW = Pattern.compile("<FATALRequest:ОрганЗАГС.+Смоленск");

    public static void main(String[] args) throws IOException {

    }

    private static String getStringFromFile() {
        return null;
    }

    private static List<String> searchSmolenskZagsFile(File file) throws IOException {
        StringBuilder resultFile;
        FileInputStream fis;
        List<String> listOfBlocksDeathOld = new ArrayList<>();
        List<String> listOfBlocksDeathNew = new ArrayList<>();
        List<String> blocksWithZagsesSmolenskFatalOld = new ArrayList<>();
        List<String> blocksWithZagsesSmolenskFatalNew = new ArrayList<>();
        boolean newFatal;

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BufferedInputStream bis = new BufferedInputStream(fis);
        String str = IOUtils.toString(bis, "UTF-8");

        if (getFileType(file).equals(FileType.FATAL)) {

            Pattern blockFataOld = Pattern.compile("<СведРегСмерт[\\S\\s]*?</СведРегСмерт>");
            Matcher matchInFatalOld = blockFataOld.matcher(str);

            Pattern blockFatalNew = Pattern.compile("<FATALRequest:СведРегСмерт[\\S\\s]*?</FATALRequest:СведРегСмерт>");
            Matcher matchInFatalNew = blockFatalNew.matcher(str);

            if (matchInFatalOld.find()) {
                newFatal = false;
                while (matchInFatalOld.find()) {
                    listOfBlocksDeathOld.add(matchInFatalOld.group());
                }
            }

            if (matchInFatalNew.find()) {
                newFatal = true;
                while (matchInFatalNew.find()) {
                    listOfBlocksDeathNew.add(matchInFatalNew.group());
                }
            }

            for (String block : listOfBlocksDeathOld) {
                Matcher matchInBlock = ZAGS_SMOLENSK_OLD.matcher(block);
                if (matchInBlock.find()) {
                    blocksWithZagsesSmolenskFatalOld.add(block);
                }
            }

            for (String block : listOfBlocksDeathNew) {
                Matcher matchInBlock = FATAL_ZAGS_SMOLENSK_NEW.matcher(block);
                if (matchInBlock.find()) {
                    blocksWithZagsesSmolenskFatalNew.add(block);
                }
            }
        }


//        System.out.println(str);

//        System.out.println(str.startsWith("<FATALRequest"));


//        System.out.println(listOfBlocksDeathOld.size());


//        System.out.println(blocksWithZagsesSmolenskFatalOld.size());
//
//        for (String s : blocksWithZagsesSmolenskFatalOld) {
//            System.out.println(s);
//            System.out.println("=====================================================================================");
//        }

        return blocksWithZagsesSmolenskFatalOld;
    }

    private static FileType getFileType(File file) {
        if (file.getName().startsWith("FATALRequest")) {
            return FileType.FATAL;
        } else if (file.getName().startsWith("PERNAMERequest")) {
            return FileType.PERNAME;
        } else {
            return FileType.ROGD;
        }
    }
}

