import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

public class MainFrame extends JFrame {
    private Map<String, Double> listSaving = new TreeMap<>();
    private Map<String, Double> listAccumulation = new TreeMap<>();

    private JPanel firstPanel = new JPanel();

    private final JLabel addNewSavingMoney = new JLabel("Добавить новую цель для экономии");
    private final JLabel addNewAccumulationMoney = new JLabel("Добавить новую цель для накоплений");
    private final JLabel selectSavingMoneyLabel = new JLabel("Выбрать цель для экономии");
    private final JLabel selectAccumulationLabel = new JLabel("Выбрать цель для накоплений");

    private final JButton addSavingMoney = new JButton("Добавить");
    private final JButton addAccumulation = new JButton("Добавить");
    private final JButton selectSavingMoney = new JButton("Выбрать");
    private final JButton selectAccumulation = new JButton("Выбрать");

    public MainFrame() {
        fillPanel();
        addNewSavingMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
        addNewAccumulationMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectSavingMoneyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectAccumulationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addSavingMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
        addAccumulation.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectSavingMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectAccumulation.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(firstPanel);
    }

    private boolean listSavingIsEmpty() {
        return listSaving.isEmpty();
    }

    private boolean listAccumulationIsEmpty() {
        return listAccumulation.isEmpty();
    }

    private void fillPanel() {
        if (listSavingIsEmpty() && listAccumulationIsEmpty()) {
            firstPanel.add(addNewSavingMoney);
            firstPanel.add(addSavingMoney);
            firstPanel.add(addNewAccumulationMoney);
            firstPanel.add(addAccumulation);
        }else if (!listSavingIsEmpty() && !listAccumulationIsEmpty()) {
            firstPanel.add(selectSavingMoneyLabel);
            firstPanel.add(selectSavingMoney);
            firstPanel.add(selectAccumulationLabel);
            firstPanel.add(selectAccumulation);
        }else if (listSavingIsEmpty() && !listAccumulationIsEmpty()) {
            firstPanel.add(addNewSavingMoney);
            firstPanel.add(addSavingMoney);
            firstPanel.add(selectAccumulationLabel);
            firstPanel.add(selectAccumulation);
        }else if (!listSavingIsEmpty() && listAccumulationIsEmpty()) {
            firstPanel.add(selectSavingMoneyLabel);
            firstPanel.add(selectSavingMoney);
            firstPanel.add(addNewAccumulationMoney);
            firstPanel.add(addAccumulation);
        }
    }
}
