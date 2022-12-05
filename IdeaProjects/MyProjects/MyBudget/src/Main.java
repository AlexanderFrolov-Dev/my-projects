import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        JFrame mainFrame = new MainFrame();

        mainFrame.setTitle("My budget");
        mainFrame.setSize(new Dimension(500,800));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setVisible(true);
    }
}

//    JFrame mainFrame = new JFrame();
//
//        mainFrame.setTitle("My budget");
//        mainFrame.setSize(new Dimension(500,800));
//        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JPanel cardPanel = new JPanel();
//        cardPanel.setLayout(new CardLayout());
//
//        JLabel addNewSavingMoney = new JLabel("Добавить новую цель для экономии");
//        JLabel addNewAccumulationMoney = new JLabel("Добавить новую цель для накоплений");
//        JLabel selectSavingMoneyLabel = new JLabel("Выбрать цель для экономии");
//        JLabel selectAccumulationLabel = new JLabel("Выбрать цель для накоплений");
//
//        JButton addSavingMoney = new JButton("Добавить");
//        JButton addAccumulation = new JButton("Добавить");
//        JButton selectSavingMoney = new JButton("Выбрать");
//        JButton selectAccumulation = new JButton("Выбрать");
//
//        addNewSavingMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
//        addNewAccumulationMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
//        selectSavingMoneyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        selectAccumulationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        addSavingMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
//        addNewSavingMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
//        selectSavingMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
//        selectAccumulation.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        JPanel jPanelNN = new JPanel();
//        jPanelNN.setLayout(new BoxLayout(jPanelNN, BoxLayout.Y_AXIS));
//
//        jPanelNN.add(addNewSavingMoney);
//        jPanelNN.add(addSavingMoney);
//        jPanelNN.add(addNewAccumulationMoney);
//        jPanelNN.add(addAccumulation);
//        cardPanel.add(jPanelNN);
//
//        JPanel jPanelYY = new JPanel();
//        jPanelYY.setLayout(new BoxLayout(jPanelYY,BoxLayout.Y_AXIS));
//
//        jPanelYY.add(selectSavingMoneyLabel);
//        jPanelYY.add(selectSavingMoney);
//        jPanelYY.add(selectAccumulationLabel);
//        jPanelYY.add(selectAccumulation);
//        cardPanel.add(jPanelYY);
//
//        JPanel jPanelYN = new JPanel();
//        jPanelYN.setLayout(new BoxLayout(jPanelYN, BoxLayout.Y_AXIS));
//
//        jPanelYN.add(selectSavingMoneyLabel);
//        jPanelYN.add(selectSavingMoney);
//        jPanelYN.add(addNewAccumulationMoney);
//        jPanelYN.add(addAccumulation);
//
//        JPanel jPanelNY = new JPanel();
//        jPanelNY.setLayout(new BoxLayout(jPanelNY, BoxLayout.Y_AXIS));
//
//        jPanelNY.add(addNewSavingMoney);
//        jPanelNY.add(addSavingMoney);
//        jPanelNY.add(selectAccumulationLabel);
//        jPanelNY.add(selectAccumulation);
//
//        mainFrame.add(cardPanel);
//
//        mainFrame.setVisible(true);