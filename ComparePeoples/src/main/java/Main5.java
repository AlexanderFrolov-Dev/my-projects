import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Работает некорректно
public class Main5 {
    //    public static final Pattern SNILS_PATTERN = Pattern.compile("О\\d{3}-\\d{3}-\\d{3}\\s\\d{8}");
    public static final Pattern SNILS_PATTERN = Pattern.compile("\\d{3}-\\d{3}-\\d{3}\\s\\d{2}");
    public static final Pattern PEOPLES_RECORDS_PATTERN = Pattern.compile("О\\d{3}-\\d{3}-\\d{3}\\s\\d{8}.+[\\S\\s]+\\r\\nИС\\r\\n");
    public static final File DIRECTORY_WITH_FILES_FOR_COMPARISON_FILE = new File("C:\\Users\\Andrey Pakhomenkov\\Desktop\\ОГБД");
    public static final File RSO_FILE = new File("C:\\Users\\Andrey Pakhomenkov\\Desktop\\ОГБД\\01122076_сзн_РСО.txt");
    public static final File ESRN_FILE = new File("C:\\Users\\Andrey Pakhomenkov\\Desktop\\ОГБД\\07112076_сзн_ЭСРН.txt");
    private static Map<String, String> rsoMap = new HashMap<>();
    private static Map<String, String> esrnMap = new HashMap<>();
    private static List<String> rsoPeoplesRecordsList = new ArrayList<>();
    private static List<String> esrnPeoplesRecordsList = new ArrayList<>();
    private static String textOfRsoFile;
    private static String textOfEsrnFile;
    private static List<String> rsoNonMatches = new ArrayList<>();
    private static List<String> esrnNonMatches = new ArrayList<>();

    public static void main(String[] args) {

        textOfRsoFile = getStringCp866FromFile(RSO_FILE);
        textOfEsrnFile = getStringCp866FromFile(ESRN_FILE);

        rsoPeoplesRecordsList = getPeoplesRecords(textOfRsoFile);
        esrnPeoplesRecordsList = getPeoplesRecords(textOfEsrnFile);

        rsoMap = getMapPeoplesRecordsBySnils(rsoPeoplesRecordsList);
        esrnMap = getMapPeoplesRecordsBySnils(esrnPeoplesRecordsList);

        Set<String> rsoMapKeys = rsoMap.keySet();
        List<String> rsoMapKeysList = rsoMapKeys.stream().toList();
        Set<String> esrnMapKeys = esrnMap.keySet();
        List<String> esrnMapKeysList = esrnMapKeys.stream().toList();

        for (String s : rsoMapKeys) {
            if (esrnMapKeys.stream().noneMatch(s::equals)) {
                rsoNonMatches.add(s);
            }
        }

        for (String s : esrnMapKeys) {
            if (rsoMapKeys.stream().noneMatch(s::equals)) {
                esrnNonMatches.add(s);
            }
        }



        System.out.println("rsoPeoplesRecordsList size: " + rsoPeoplesRecordsList.size());
        System.out.println("getPeoplesRecords(textOfEsrnFile) size: " + getPeoplesRecords(textOfRsoFile).size());
        System.out.println("rsoMap size: " + rsoMap.size());
        System.out.println("esrnMap size: " + esrnMap.size());
        System.out.println("rsoNonMatches size: " + rsoNonMatches.size());
        System.out.println("esrnNonMatches size: " + esrnNonMatches.size());

        for (String s : esrnNonMatches) {
            System.out.println(s);
        }

        try {
            FileWriter fileWriterForRso = new FileWriter("C:\\Users\\Andrey Pakhomenkov\\Desktop\\Расхождения ОГБД\\Есть в РСО, но нет в ЭСРН.txt");

            for (String s : rsoNonMatches) {
                fileWriterForRso.write(s + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            FileWriter fileWriterForEsrn = new FileWriter("C:\\Users\\Andrey Pakhomenkov\\Desktop\\Расхождения ОГБД\\Есть в ЭСРН, но нет в РСО.txt");

            for (String s : esrnNonMatches) {
                fileWriterForEsrn.write(s + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        for (Map.Entry<String, String> entry : getMapPeoplesRecordsBySnils(rsoPeoplesRecordsList).entrySet()) {
//            System.out.println(entry.getKey() + " - " + entry.getValue());
//        }
    }

    private static Map<String, String> getMapPeoplesRecordsBySnils(List<String> peoplesRecords) {
        Map<String, String> peoplesRecordsBySnils = new HashMap<>();

        for (String s : peoplesRecords) {
            Matcher matcher = SNILS_PATTERN.matcher(s);
            if (matcher.find()) {
                peoplesRecordsBySnils.put(matcher.group(), s);
            }
        }

        return peoplesRecordsBySnils;
    }

    private static List<String> getPeoplesRecords(String text) {
        return List.of(text.split(String.valueOf(Pattern.compile("\\r\\nИС\\r\\n"))));
    }

    private static String getStringCp866FromFile(File file) {
        String stringCp866 = "";
        try (FileInputStream fis = new FileInputStream(file)) {
            stringCp866 = IOUtils.toString(fis, "cp866");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringCp866;
    }
}

//        try (FileInputStream fisOfRSOFile = new FileInputStream(RSO_FILE)) {
//            textOfRsoFile = IOUtils.toString(fisOfRSOFile, "cp866");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try (FileInputStream fisOfESRNFile = new FileInputStream(ESRN_FILE)) {
//            textOfEsrnFile = IOUtils.toString(fisOfESRNFile, "cp866");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
