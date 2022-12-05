import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
//        ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\Notepad++\\notepad++.exe", "C:\\Users\\Andrey Pakhomenkov\\Desktop\\Text.txt");

//        ProcessBuilder processBuilder = new ProcessBuilder(
//                "C:\\Program Files (x86)\\Microsoft Office\\Office12\\EXCEL.EXE",
//                "C:\\Users\\Andrey Pakhomenkov\\Desktop\\Книга1.xlsx"
//        );
//        processBuilder.start();

//        String url = "172.16.16.225";
//        String user = "test";
//        String passwd = "123456";

//        Connection con = DriverManager.getConnection(url, user, passwd);

        ProcessBuilder processBuilder = new ProcessBuilder(
                "C:\\Program Files (x86)\\Microsoft Office\\Office12\\EXCEL.EXE",
                "C:\\Users\\Andrey Pakhomenkov\\Desktop\\Книга1.xlsx"
        );
        processBuilder.start();

//        HSSFWorkbook workbook;

//        File file = new File("C:\\Users\\Andrey Pakhomenkov\\Desktop\\Книга1.xlsx");
//
//        InputStreamReader in = new InputStreamReader(new FileInputStream(file), "cp1251");
//        BufferedReader reader = new BufferedReader(in);
//        String s;
//        StringBuilder sb = new StringBuilder();
//        while((s = reader.readLine()) != null){
//            sb.append(s + "\n");
//        }
//        System.out.println(sb);
//        reader.close();

//        try(FileReader reader = new FileReader("C:\\Users\\Andrey Pakhomenkov\\Desktop\\Книга1.xlsx"))
//        {
//            char[] buf = new char[256000];
//            int c;
//            while((c = reader.read(buf))>0){
//
//                if(c < 256000){
//                    buf = Arrays.copyOf(buf, c);
//                }
//                System.out.print(buf);
//            }
//        }
//        catch(IOException ex){
//
//            System.out.println(ex.getMessage());
//        }
    }

    private Connection getNewConnection() throws SQLException {
        String url = "jdbc:h2:mem:test";
        String user = "sa";
        String passwd = "sa";
        return DriverManager.getConnection(url, user, passwd);
    }
}