import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("C:\\Users\\Andrey Pakhomenkov\\Desktop\\ESRN.txt");
        String ESRN = IOUtils.toString(fis, "UTF-8");

        FileInputStream fis2 = new FileInputStream("C:\\Users\\Andrey Pakhomenkov\\Desktop\\RSO.txt");
        String RSO = IOUtils.toString(fis2, "UTF-8");

        Pattern pattern = Pattern.compile("О\\d{3}-\\d{3}-\\d{3}\\s\\d{8}[А-Я]+\\s+[А-Я]+\\s+[А-Я]+");

        Matcher m = pattern.matcher(ESRN);
        Matcher m2 = pattern.matcher(RSO);

        List<String> ESRNArrayList = new ArrayList<>();
        List<String> RSOArrayList = new ArrayList<>();

        while (m.find()) {
            ESRNArrayList.add(String.valueOf(m.group()));
        }

        while (m2.find()) {
            RSOArrayList.add(String.valueOf(m2.group()));
        }

//        for (String s : ESRNArrayList) {
//            System.out.println(s);
//        }
//
//        System.out.println("===================================================================================");
//
//        for (String s : RSOArrayList) {
//            System.out.println(s);
//        }

        List<String> ESRNNonMatches = new ArrayList<>();
        List<String> RSONonMatches = new ArrayList<>();

        for (int i = 0; i < ESRNArrayList.size(); i++) {
            String currentRow = ESRNArrayList.get(i);
            if (RSOArrayList.stream().noneMatch(currentRow::equals)) {
                ESRNNonMatches.add(currentRow);
            }
        }

        for (int i = 0; i < RSOArrayList.size(); i++) {
            String currentRow = RSOArrayList.get(i);
            if (ESRNArrayList.stream().noneMatch(currentRow::equals)) {
                RSONonMatches.add(currentRow);
            }
        }

        System.out.println(ESRNNonMatches.size());
        System.out.println(RSONonMatches.size());

        for (String s : ESRNNonMatches) {
            System.out.println(s);
        }

        System.out.println("===================================================================================");

        for (String s : RSONonMatches) {
            System.out.println(s);
        }
    }
}
