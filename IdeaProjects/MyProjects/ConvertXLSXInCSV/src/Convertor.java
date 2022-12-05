public class Convertor {
    XSSFWorkbook input = new XSSFWorkbook(new File("input.xlsx"));
    CSVPrinter output = new CSVPrinter(new FileWriter("output.csv"), CSVFormat.DEFAULT);

    String tsv = new XSSFExcelExtractor(input).getText();
    BufferedReader reader = new BufferedReader(new StringReader(tsv));
reader.lines().map(line -> line.split("\t").forEach(output::printRecord);
}
