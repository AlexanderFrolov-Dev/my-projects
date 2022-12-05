import javax.swing.*;
import java.awt.*;

public class MainActivity extends JFrame {

    JLabel addNewSavingMoney = new JLabel("Добавить новую цель для экономии");
    JLabel addNewAccumulationMoney = new JLabel("Добавить новую цель для накоплений");
    JLabel selectSavingMoneyLabel = new JLabel("Выбрать цель для экономии");
    JLabel selectAccumulationLabel = new JLabel("Выбрать цель для накоплений");

    JButton addSavingMoney = new JButton("Добавить");
    JButton addAccumulation = new JButton("Добавить");
    JButton selectSavingMoney = new JButton("Выбрать");
    JButton selectAccumulation = new JButton("Выбрать");

    public boolean existSavingMoneyFile() {
        return true;
    }

    public boolean existAccumulationFile() {
        return true;
    }

    public MainActivity() {
        MainPanel mainPanel = new MainPanel();

        mainPanel.setLayout(new CardLayout());
    }
}
