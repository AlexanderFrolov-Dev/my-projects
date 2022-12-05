import java.io.*;
import java.nio.charset.Charset;

import com.linuxense.javadbf.*;

public class JavaDBFReader {

    public static void main(String args[]) throws UnsupportedEncodingException {

        System.out.println(Charset.defaultCharset().displayName());

        String text = "текст";
        byte[] data = text.getBytes();
        String OEM = new String(data, "Windows-1251");
        System.out.println(OEM);

        try {

            // create a DBFReader object
            //
            InputStream inputStream = new FileInputStream("C:\\Users\\Andrey Pakhomenkov\\Desktop\\Vygruzka_DBF_po_raoynam_9385_216.DBF");
            // принять dbf-файл в качестве аргумента программы
            DBFReader reader = new DBFReader(inputStream);

            // получите количество полей, если вы хотите по некоторым причинам, например, следующим
            //
            int numberOfFields = reader.getFieldCount();

            // используйте это количество для извлечения всей информации о поле
            // если требуется
            for (int i = 0; i < numberOfFields; i++) {

                DBFField field = reader.getField(i);


//               byte[] arrayBytes = field.getName().getBytes();
//               String str = new String(arrayBytes, "windows-1251");
//                System.out.println(str);

                // сделайте что-нибудь с этим, если хотите
                // обратитесь к ссылке JavaDoc API для получения более подробной информации
                //
                System.out.println(field.getName());
            }

            // Теперь давайте начнем читать строки
            //
            Object[] rowObjects;

            while ((rowObjects = reader.nextRecord()) != null) {

                for (int i = 0; i < rowObjects.length; i++) {

                    System.out.println(rowObjects[i]);
                }
            }

            // К настоящему времени мы перебрали все строки

            inputStream.close();
        } catch (DBFException e) {

            System.out.println(e.getMessage());
        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
    }
}
