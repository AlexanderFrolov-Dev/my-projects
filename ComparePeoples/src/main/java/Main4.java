import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Работает некорректно
public class Main4 {
    //    public static final Pattern SNILS_PATTERN = Pattern.compile("О\\d{3}-\\d{3}-\\d{3}\\s\\d{8}");
    public static final Pattern SNILS_PATTERN = Pattern.compile("\\d{3}-\\d{3}-\\d{3}\\s\\d{2}");
    public static final Pattern PEOPLES_RECORDS_PATTERN = Pattern.compile("О\\d{3}-\\d{3}-\\d{3}\\s\\d{8}.+[\\S\\s]+\\r\\nИС\\r\\n");
    public static final File DIRECTORY_WITH_FILES_FOR_COMPARISON_FILE = new File("C:\\Users\\Andrey Pakhomenkov\\Desktop\\ОГБД");
    public static final File RSO_FILE = new File("C:\\Users\\Andrey Pakhomenkov\\Desktop\\ОГБД\\01122076_сзн_РСО.txt");
    public static final File ESRN_FILE = new File("C:\\Users\\Andrey Pakhomenkov\\Desktop\\ОГБД\\07112076_сзн_ЭСРН.txt");
    private static Map<String, String> RsoMap = new HashMap<>();
    private static Map<String, String> EsrnMap = new HashMap<>();
    private static String textOfRsoFile;
    private static String textOfEsrnFile;

    public static void main(String[] args) {
        try (FileInputStream fisOfRSOFile = new FileInputStream(RSO_FILE)) {
            textOfRsoFile = IOUtils.toString(fisOfRSOFile, "cp866");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fisOfESRNFile = new FileInputStream(ESRN_FILE)) {
            textOfEsrnFile = IOUtils.toString(fisOfESRNFile, "cp866");
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> stringList = new ArrayList<>(getPeoplesRecords(textOfRsoFile));

        System.out.println(stringList.size());
        System.out.println(getPeoplesRecords(textOfEsrnFile).size());

        for (Map.Entry<String, String> entry : getMapPeoplesRecordsBySnils(stringList).entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
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

    private static String removeUnnecessarySpaces(String s) {
        Pattern multipleSpaces = Pattern.compile("\\s{2,}");
        Matcher matcher = multipleSpaces.matcher(s);

        if (matcher.find()) {
            s = s.replace(matcher.group(), " ");
        }

        return s;
    }
}
