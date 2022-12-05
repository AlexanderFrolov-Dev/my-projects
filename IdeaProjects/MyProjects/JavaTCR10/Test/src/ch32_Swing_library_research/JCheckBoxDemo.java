package ch32_Swing_library_research;

// Продемонстрировать применение компонента типа JCheckbox.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JCheckBoxDemo implements ItemListener {
    JLabel jlab;

    public JCheckBoxDemo() {

        // Установить фрейм средствами класса JFrame.
        JFrame jfrm = new JFrame("JCheckBoxDemo");
        jfrm.setLayout(new FlowLayout());
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setSize(250, 100);

        // Ввести флажки на панели содержимого.
        JCheckBox cb = new JCheckBox("C");
        cb.addItemListener(this);
        jfrm.add(cb);

        cb = new JCheckBox("C++");
        cb.addItemListener(this);
        jfrm.add(cb);

        cb = new JCheckBox("Java");
        cb.addItemListener(this);
        jfrm.add(cb);

        cb = new JCheckBox("Perl");
        cb.addItemListener(this);
        jfrm.add(cb);

        // Создать метку и ввести ее на панели содержимого.
        jlab = new JLabel("Select languages");
        jfrm.add(jlab);

        // Отобразить фрейм.
        jfrm.setVisible(true);
    }

    // Обработать события от выбираемых элементов,
    // наступающие при установке и сбросе флажков.
    public void itemStateChanged(ItemEvent ie) {
        JCheckBox cb = (JCheckBox)ie.getItem();

        if(cb.isSelected())
            jlab.setText(cb.getText() + " is selected");
        else
            jlab.setText(cb.getText() + " is cleared");
    }

    public static void main(String[] args) {

        // Создать фрейм в потоке диспетчеризации событий.
        SwingUtilities.invokeLater(() -> new JCheckBoxDemo());
    }
}
