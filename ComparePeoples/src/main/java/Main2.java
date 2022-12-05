import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2 {
    public static void main(String[] args) throws IOException {
        Path firstPath = Path.of("C:\\Users\\Andrey Pakhomenkov\\Desktop\\TestRSO.001_РСО.txt");
        Path secondPath = Path.of("C:\\Users\\Andrey Pakhomenkov\\Desktop\\TestESRN.001_ЭСРН.txt");

        FileInputStream fis = new FileInputStream(firstPath.toString());
//        String RSO = IOUtils.toString(fis, "cp866");
        String RSO = IOUtils.toString(fis, "UTF-8");

        FileInputStream fis2 = new FileInputStream(secondPath.toString());
//        String ESRN = IOUtils.toString(fis2, "cp866");
        String ESRN = IOUtils.toString(fis2, "UTF-8");

        String firstFileName = firstPath.getFileName().toString();
        String secondFileName = secondPath.getFileName().toString();

        Pattern index = Pattern.compile("\\.\\d{3}_[А-Я]+");
        Matcher matcherFirstFile = index.matcher(firstFileName);
        Matcher matcherSecondFile = index.matcher(secondFileName);

        String firstIndex = "";
        String secondIndex = "";
        String commonIndex = "";

        if (matcherFirstFile.find()) {
            firstIndex = matcherFirstFile.group();
        }

        if (matcherSecondFile.find()) {
            secondIndex = matcherSecondFile.group();
        }

        firstIndex = firstIndex.replace(".", "");
        secondIndex = secondIndex.replace(".", "");

        Pattern patternForCommonIndex = Pattern.compile("\\d{3}");
        Matcher matcherForCommonIndex = patternForCommonIndex.matcher(firstIndex);

        if (matcherForCommonIndex.find()) {
            commonIndex = matcherForCommonIndex.group();
        }

        System.out.println(firstIndex);
        System.out.println(secondIndex);
        System.out.println(commonIndex);

        System.out.println();
        System.out.println(RSO);
        System.out.println();

        String targetPath = "C:\\Users\\Andrey Pakhomenkov\\Desktop\\result\\" + commonIndex + ".txt";

//        Pattern pattern = Pattern.compile("О\\d{3}-\\d{3}-\\d{3}\\s\\d{8}[А-Я]+\\s+[А-Я]+\\s+[А-Я]+");
        Pattern pattern = Pattern.compile("О\\d{3}-\\d{3}-\\d{3}\\s\\d{8}");

        Matcher m = pattern.matcher(RSO);
        Matcher m2 = pattern.matcher(ESRN);

        List<String> RSOArrayList = new ArrayList<>();
        List<String> ESRNArrayList = new ArrayList<>();

        while (m.find()) {
            RSOArrayList.add(String.valueOf(m.group()));
        }

        while (m2.find()) {
            ESRNArrayList.add(String.valueOf(m2.group()));
        }


        System.out.println();
        System.out.println("RSO size: " + RSOArrayList.size());
        System.out.println("ESRN size: " + ESRNArrayList.size());
        System.out.println();

        System.out.println("RSO:");
        for (String s : RSOArrayList) {
            System.out.println(s);
        }

        System.out.println("===================================================================================");

        System.out.println("ESRN:");
        for (String s : ESRNArrayList) {
            System.out.println(s);
        }
        // Есть в РСО, но нет в ЭСРН
        List<String> RSONonMatches = new ArrayList<>();
        // Есть в ЭСРН, но нет в РСО
        List<String> ESRNNonMatches = new ArrayList<>();

        for (int i = 0; i < RSOArrayList.size(); i++) {
            String currentRow = RSOArrayList.get(i);
            if (ESRNArrayList.stream().noneMatch(currentRow::equals)) {
                RSONonMatches.add(currentRow);
            }
        }

        for (int i = 0; i < ESRNArrayList.size(); i++) {
            String currentRow = ESRNArrayList.get(i);
            if (RSOArrayList.stream().noneMatch(currentRow::equals)) {
                ESRNNonMatches.add(currentRow);
            }
        }

        System.out.println();
        System.out.println("/////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println();

        System.out.println("RSONonMatches size: " + RSONonMatches.size());
        System.out.println("ESRNNonMatches size: " + ESRNNonMatches.size());

        System.out.println();
        System.out.println("RSONonMatches:");
        for (String s : RSONonMatches) {
            System.out.println(s);
        }

        System.out.println("===================================================================================");

        System.out.println("ESRNNonMatches:");
        for (String s : ESRNNonMatches) {
            System.out.println(s);
        }

//        try {
//            FileWriter writer = new FileWriter(targetPath);
//            for (String s : RSOArrayList) {
//                writer.write(s + System.lineSeparator());
//            }
//            writer.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            if (!RSONonMatches.isEmpty() || !ESRNNonMatches.isEmpty()) {
                FileWriter writer = new FileWriter(targetPath);

                if (!RSONonMatches.isEmpty()) {
                    writer.write("Есть в РСО, но нет в ЭСРН:" + System.lineSeparator());
                }
                for (String s : RSONonMatches) {
                    writer.write(s + System.lineSeparator());
                }

                if (!ESRNNonMatches.isEmpty()) {
                    writer.write("Есть в ЭСРН, но нет в РСО:" + System.lineSeparator());
                }
                for (String s : ESRNNonMatches) {
                    writer.write(s + System.lineSeparator());
                }
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
