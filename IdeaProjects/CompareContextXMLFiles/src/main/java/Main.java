import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    String august25D = "C:\\Users\\Andrey Pakhomenkov\\Desktop\\Новая папка\\20220830\\PFR-1.0-Y-2022-M-09-D25.XML";
    String september25D = "C:\\Users\\Andrey Pakhomenkov\\Desktop\\Новая папка\\20220916\\PFR-1.0-Y-2022-M-09-D25-1.XML";
    static File august25DFile = new File("C:\\Users\\Andrey Pakhomenkov\\Desktop\\Новая папка\\20220830\\PFR-1.0-Y-2022-M-09-D25.XML");
    static File september25DFile = new File("C:\\Users\\Andrey Pakhomenkov\\Desktop\\Новая папка\\20220916\\PFR-1.0-Y-2022-M-09-D25-1.XML");

    public static void main(String[] args) throws IOException {
        List<String> listBlocksAugust;
        List<String> listBlocksSeptember;
        List<String> listSNILSAugust = new ArrayList<>();
        List<String> listSNILSSeptember = new ArrayList<>();
        String august = getStringFromFile(august25DFile);
        String september = getStringFromFile(september25DFile);
        List<String> listMatchesSNILS = new ArrayList<>();
        List<String> listNonMatchesSNILS = new ArrayList<>();

        listBlocksAugust = getListOfPersonBlocks(august);
        listBlocksSeptember = getListOfPersonBlocks(september);

        System.out.println("listBlocksAugust size: " + listBlocksAugust.size());
        System.out.println("listBlocksSeptember size: " + listBlocksSeptember.size());

        for (String s : listBlocksAugust) {
            getSNILSList(s, listSNILSAugust);
        }

        for (String s : listBlocksSeptember) {
            getSNILSList(s, listSNILSSeptember);
        }

        System.out.println("listSNILSAugust size: " + listSNILSAugust.size());
        System.out.println("listSNILSSeptember size: " + listSNILSSeptember.size());

        for (int i = 0; i < listSNILSAugust.size(); i++) {
            String currentRow = listSNILSAugust.get(i);
            if (listSNILSSeptember.stream().anyMatch(currentRow::equals)) {
                listMatchesSNILS.add(currentRow);
            }
        }

//        for (String s : listMatchesSNILS) {
//            System.out.println(s);
//        }

        System.out.println("=========================================================================================");

        System.out.println("listMatchesSNILS size: " + listMatchesSNILS.size());

        for (int i = 0; i < listSNILSSeptember.size(); i++) {
            String currentRow = listSNILSSeptember.get(i);
            if (listMatchesSNILS.stream().noneMatch(currentRow::equals)) {
                listNonMatchesSNILS.add(currentRow);
            }
        }

//        for (String s : listBlocksSeptember) {
//            System.out.println(s);
//        }

        System.out.println("listNonMatchesSNILS size: " + listNonMatchesSNILS.size());

        FileWriter writer = new FileWriter("C:\\Users\\Andrey Pakhomenkov\\Desktop\\Matches_SNILS.txt");

        for (String s : listNonMatchesSNILS) {
            writer.write(s.concat(System.lineSeparator()));
        }

        writer.close();

//        System.out.println(august);

//        System.out.println(august.equals(september));
    }

    public static String getStringFromFile(File file) throws IOException {
        FileInputStream fis;

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BufferedInputStream bis = new BufferedInputStream(fis);

        return IOUtils.toString(bis, "cp1251");
    }

    public static List<String> getListOfPersonBlocks(String s) {
        List<String> blocksList = new ArrayList<>();
        Pattern pattern = Pattern.compile("<ДанныеПоПолучателю>[\\S\\s]*?</ДанныеПоПолучателю>");
        Matcher matcher = pattern.matcher(s);

        while (matcher.find()) {
            blocksList.add(matcher.group());
        }

        return blocksList;
    }

    public static void getSNILSList(String s, List<String> list) {
        Pattern pattern = Pattern.compile("<СтраховойНомер>\\d{11}</СтраховойНомер>");
        Matcher matcher = pattern.matcher(s);

        while (matcher.find()) {
            list.add(matcher.group().replaceAll("\\D", ""));
        }
    }
}
