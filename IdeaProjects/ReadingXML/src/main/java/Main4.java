import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main4 {
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
        // Результаты выборки fatal из всей папки 2022_god: 46395
        //1879

//        File emptyFile = new File("X:\\PENSION\\upravlen\\ЗАГС\\2022_god\\08\\20220822\\ROGDRequest_20220822_001454_2cf1207e-5fcb-43af-811b-67ce03651e25.xml");
//        File notEmptyFile = new File("X:\\PENSION\\upravlen\\ЗАГС\\2022_god\\08\\20220822\\ROGDRequest_20220820_002047_7279f0e7-1bae-4fd8-9849-d6b31010896d.xml");
//        System.out.println(emptyFile.length() + " байт");
//        System.out.println(notEmptyFile.length() + " байт");

        //==============================================================================================================
        // Поиск первых строк в файлах FATALRequest старого формата
        File oldTypeFileFatal = new File("C:\\Users\\Andrey Pakhomenkov\\Desktop\\FATALRequest_20211230_000043_ad8220f6-c65c-43db-8c9e-dfe4979839e0.xml");

        FileInputStream fisOldFatal;
        try {
            fisOldFatal = new FileInputStream(oldTypeFileFatal);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BufferedInputStream bisOldFatal = new BufferedInputStream(fisOldFatal);
        String strOldFatal = IOUtils.toString(bisOldFatal, "UTF-8");

        Pattern firstLineOldTypeFatal = Pattern.compile("<FATALRequest xmlns:FATALRequest.+>");
        Matcher matchFirstLineOldTypeFatal = firstLineOldTypeFatal.matcher(strOldFatal);

        if (matchFirstLineOldTypeFatal.find()) {
            System.out.println("FirstLineOldTypeFatal: " + matchFirstLineOldTypeFatal.group());
        }

        String lastLineOldTypeFatal = "</FATALRequest>";
        System.out.println("LastLineOldTypeFatal: " + lastLineOldTypeFatal);
        System.out.println("----------------------------------------------");

        //==============================================================================================================
        // Поиск первых строк в файлах PERNAMERequest старого формата

        File oldTypeFilePerName = new File("X:\\PENSION\\upravlen\\ЗАГС\\2022_god\\01\\10\\PERNAMERequest_20220104_000025_85cfa139-733d-438a-a739-c10fb5dc1e30.xml");

        FileInputStream fisOldPerName;
        try {
            fisOldPerName = new FileInputStream(oldTypeFilePerName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BufferedInputStream bisOldPerName = new BufferedInputStream(fisOldPerName);
        String strOldPerName = IOUtils.toString(bisOldPerName, "UTF-8");

        Pattern firstLineOldTypePerName = Pattern.compile("<PERNAMERequest xmlns:PERNAMERequest.+>");
        Matcher matchFirstLineOldTypePerName = firstLineOldTypePerName.matcher(strOldPerName);

        if (matchFirstLineOldTypePerName.find()) {
            System.out.println("FirstLineOldTypePerName: " + matchFirstLineOldTypePerName.group());
        }

        String lastLineOldTypePerName = "</PERNAMERequest>";
        System.out.println("LastLineOldTypePerName: " + lastLineOldTypePerName);
        System.out.println("----------------------------------------------");

        //==============================================================================================================
        // Поиск первых строк в файлах ROGDRequest старого формата

        File oldTypeFileRogd = new File("X:\\PENSION\\upravlen\\ЗАГС\\2022_god\\01\\10\\ROGDRequest_20211230_000043_5af88233-8fd2-4669-8545-aa49a6648a9e.xml");

        FileInputStream fisOldRogd;
        try {
            fisOldRogd = new FileInputStream(oldTypeFileRogd);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BufferedInputStream bisOldRogd = new BufferedInputStream(fisOldRogd);
        String strOldRogd = IOUtils.toString(bisOldRogd, "UTF-8");

        Pattern firstLineOldTypeRogd = Pattern.compile("<ns2:ROGDRequest xmlns:ns2.+>");
        Matcher matchFirstLineOldTypeRogd = firstLineOldTypeRogd.matcher(strOldRogd);

        if (matchFirstLineOldTypeRogd.find()) {
            System.out.println("FirstLineOldTypeRogd: " + matchFirstLineOldTypeRogd.group());
        }

        String lastLineOldTypeRogd = "</ns2:ROGDRequest>";
        System.out.println("LastLineOldTypeRogd: " + lastLineOldTypeRogd);

        System.out.println();

        //==============================================================================================================
        // Поиск первых строк в файлах FATALRequest нового формата
        File newTypeFileFatal = new File("X:\\PENSION\\upravlen\\ЗАГС\\2022_god\\09\\20220906\\FATALRequest_20220905_171442_1e64a546-17ad-4c90-8d84-25da8fd42e82.xml");

        FileInputStream fisNewFatal;
        try {
            fisNewFatal = new FileInputStream(newTypeFileFatal);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BufferedInputStream bisNewFatal = new BufferedInputStream(fisNewFatal);
        String strNewFatal = IOUtils.toString(bisNewFatal, "UTF-8");

        Pattern firstLineNewTypeFatal = Pattern.compile("<FATALRequest:FATALRequest xmlns.+>");
        Matcher matchFirstLineNewTypeFatal = firstLineNewTypeFatal.matcher(strNewFatal);

        if (matchFirstLineNewTypeFatal.find()) {
            System.out.println("FirstLineNewType: " + matchFirstLineNewTypeFatal.group());
        }

        String lastLineNewTypeFatal = "</FATALRequest:FATALRequest>";
        System.out.println("LastLineNewTypeFatal: " + lastLineNewTypeFatal);
        System.out.println("----------------------------------------------");
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

            Pattern blockFatalNew = Pattern.compile("<FATALRequest:СведРегСмерт[\\S\\s]*?</СведРегСмерт>");
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

