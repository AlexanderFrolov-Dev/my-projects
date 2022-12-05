import com.linuxense.javadbf.DBFReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
//    public static void main(String[] args) {
//        /**
//         * Прочитайте запись DBF
//         * @param path
//         * @return
//         * @throws IOException
//         */
//
//        try {
////            DbfWriterAndReadUtil.createDbf("b.dbf", fieldList, "GBK");
////            DbfWriterAndReadUtil.writeDbf("b.dbf", rowList, "GBK");
//            String[] fieldName = DbfWriterAndReadUtil.getFieldName("b.dbf", "GBK");
//            for (int i = 0; i < fieldName.length; i++) {
//                System.out.println(fieldName[i]);
//            }
//
//            List<Map<String, String>> getRowList = DbfWriterAndReadUtil.readDbf("b.dbf", "GBK");
//            for (Map<String, String> entity : getRowList) {
//                System.out.println(entity);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static List<Map<String, String>> readDbf(String path, String charsetName) throws IOException {
        List<Map<String, String>> rowList = new ArrayList<>();
//		InputStream fis = new FileInputStream(path);
        DBFReader dbfReader = new DBFReader(new FileInputStream(path), Charset.forName(charsetName));
        Object[] rowValues;
        while ((rowValues = dbfReader.nextRecord()) != null) {
            Map<String, String> rowMap = new HashMap<String, String>();
            for (int i = 0; i < rowValues.length; i++) {
                rowMap.put(dbfReader.getField(i).getName(), String.valueOf(rowValues[i]).trim());
            }
//			System.out.println(rowMap);
            rowList.add(rowMap);
        }
        dbfReader.close();
//		fis.close();
        return rowList;
    }
}
