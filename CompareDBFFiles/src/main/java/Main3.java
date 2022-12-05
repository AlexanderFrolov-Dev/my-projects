import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Main3 {
    public static void main(String[] args) throws IOException {
        System.out.println(decodeText(new File("C:\\Users\\Andrey Pakhomenkov\\Desktop\\Vygruzka_DBF_po_raoynam_9385_216.DBF"), "windows-1251"));
    }

    private static String decodeText(File file, String encoding) throws IOException {
//        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes()),
//                                Charset.forName(encoding))).readLine();
        FileInputStream fin = new FileInputStream(file);
        byte[] b = fin.readAllBytes();
        String str = new String(b, Charset.forName(encoding));
        return str;
    }
}
