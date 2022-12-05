import javax.swing.*;
import java.awt.*;

public class PatternJPanelFirstLevel extends JPanel {
    private final JLabel addNewSavingMoney = new JLabel("Добавить новую цель для экономии");
    private final JLabel addNewAccumulationMoney = new JLabel("Добавить новую цель для накоплений");
    private final JLabel selectSavingMoneyLabel = new JLabel("Выбрать цель для экономии");
    private final JLabel selectAccumulationLabel = new JLabel("Выбрать цель для накоплений");

    private final JButton addSavingMoney = new JButton("Добавить");
    private final JButton addAccumulation = new JButton("Добавить");
    private final JButton selectSavingMoney = new JButton("Выбрать");
    private final JButton selectAccumulation = new JButton("Выбрать");

    public PatternJPanelFirstLevel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addNewSavingMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
        addNewAccumulationMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectSavingMoneyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectAccumulationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addSavingMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
        addNewSavingMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectSavingMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectAccumulation.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public JLabel getAddNewSavingMoney() {return addNewSavingMoney;}

    public JLabel getAddNewAccumulationMoney() {
        return addNewAccumulationMoney;
    }

    public JLabel getSelectSavingMoneyLabel() {
        return selectSavingMoneyLabel;
    }

    public JLabel getSelectAccumulationLabel() {
        return selectAccumulationLabel;
    }

    public JButton getAddSavingMoney() {
        return addSavingMoney;
    }

    public JButton getAddAccumulation() {
        return addAccumulation;
    }

    public JButton getSelectSavingMoney() {
        return selectSavingMoney;
    }

    public JButton getSelectAccumulation() {
        return selectAccumulation;
    }
}
