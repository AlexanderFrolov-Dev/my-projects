package ch32_Swing_library_research;

// Продемонстрировать применение компонента
// типа JTextField.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JTextFieldDemo {

    public JTextFieldDemo() {

        // Установить фрейм средствами класса JFrame.
        JFrame jfrm = new JFrame("JTextFieldDemo");
        jfrm.setLayout(new FlowLayout());
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setSize(260, 120);

        // Ввести текстовое поле на панели содержимого.
        JTextField jtf = new JTextField(15);
        jfrm.add(jtf);

        // Ввести метку.
        JLabel jlab = new JLabel();
        jfrm.add(jlab);

        // Обработать события действия.
        jtf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // Отобразить текст, когда пользователь
                // нажимает клавишу <Enter>.
                jlab.setText(jtf.getText());
            }
        });

        // Отобразить фрейм.
        jfrm.setVisible(true);
    }

    public static void main(String[] args) {

        // Создать фрейм в потоке диспетчеризации событий.
        SwingUtilities.invokeLater(JTextFieldDemo::new);
    }
}
