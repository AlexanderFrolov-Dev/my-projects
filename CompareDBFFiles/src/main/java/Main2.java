import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main2 {
    private static String fullText;
    public static void main(String[] args) throws IOException {
        String s = readFromFile(new File("C:\\Users\\Andrey Pakhomenkov\\Desktop\\Vygruzka_DBF_po_raoynam_9385_216.DBF"));
        byte[] b = s.getBytes();
        String str = new String(b, "windows-1251");
        System.out.println(str);
    }

    private static String readFromFile(File file) throws IOException {
        try (FileInputStream fin = new FileInputStream(file)) {
            StringBuilder lineBuilder = new StringBuilder();
            fullText = "";
            int symbol;

            try {
                do {
                    symbol = fin.read();
                    if (symbol != -1) {
//                        char c = (char) symbol;
//                        String s = String.valueOf(c);
//                        byte[] b = s.getBytes();
//                        String str = new String(b, "windows-1251");
                        lineBuilder.append((char) symbol);
                        fullText = String.valueOf(lineBuilder);
                    }
                } while (symbol != -1);
            } catch (IOException e) {
                System.out.println("Ошибка чтения из файла.");
            }
        }

        return fullText;
    }
}
