import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Andrey Pakhomenkov\\Desktop\\FATALRequest_20211230_000043_ad8220f6-c65c-43db-8c9e-dfe4979839e0.xml");

        FileInputStream fis;

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BufferedInputStream bis = new BufferedInputStream(fis);

//        while (bis.read() != -1) {
//            byteOfSymbol = bis.read();
//            System.out.println((char) byteOfSymbol);
//        }

        String str = IOUtils.toString(bis, "UTF-8");

        System.out.println(str);

        System.out.println(str.startsWith("<FATALRequest"));

        Pattern blockDeath = Pattern.compile("(<СведРегСмерт)(.+\r*\n*.+)*(</СведРегСмерт>)");
        Matcher matchInDeath = blockDeath.matcher(str);

        List<String> listOfBlocksDeath = new ArrayList<>();

        while (matchInDeath.find()) {
            listOfBlocksDeath.add(matchInDeath.group());
        }

        System.out.println(listOfBlocksDeath.size());
    }
}
