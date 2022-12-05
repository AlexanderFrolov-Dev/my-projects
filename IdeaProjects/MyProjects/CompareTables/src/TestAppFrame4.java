import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TestAppFrame4 extends JFrame {

    public TestAppFrame4() throws HeadlessException {
        setTitle("Сравнение таблиц");
        setSize(new Dimension(1000,1000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        MyTableModel myTableModel;
        try {
            myTableModel = new MyTableModel("C:\\Users\\timon\\OneDrive\\Рабочий стол\\Test_Files\\First_File_CSV.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JTable table = new JTable(myTableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        add(panel);

        setVisible(true);
    }
}
