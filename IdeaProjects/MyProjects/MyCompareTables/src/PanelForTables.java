import javax.swing.*;
import java.awt.*;

public class PanelForTables extends JPanel {
    JScrollPane scrollForFirstTable;
    JScrollPane scrollForSecondTable;
    JTable firstTable;
    JTable secondTable;
    String s;

    public PanelForTables() {
        setMaximumSize(new Dimension(1000, 585));
        s = MainPanel.firstPath.getText();

        firstTable = createTable();
        scrollForFirstTable = new JScrollPane(firstTable);
        secondTable = createTable();
        scrollForSecondTable = new JScrollPane(secondTable);
        add(scrollForFirstTable);
        add(scrollForSecondTable);

        setBackground(Color.RED);
    }

    private JTable createTable() {
        // new JTable(getCountOfLines() + 1, getCountOfColumns() + 1);
        // Чтобы можно было вывести ячейки с индексами цифр слева и индексами букв вверху.
        // Крайняя верхняя ячейка слева при этом будет пустая.
        String[] heading = new String[5];
        String[][] table = new String[10][5];
        int addOne = 1;

        for (int i = 0; i < heading.length; i++) {
            heading[i] = String.valueOf((char) ('A' + i));
        }
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = (addOne + i) + " " + ((char) ('A' + j));
            }
        }

        return new JTable(table, heading);
    }
}

