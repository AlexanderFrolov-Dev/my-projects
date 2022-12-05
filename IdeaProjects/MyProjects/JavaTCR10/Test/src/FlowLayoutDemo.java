import java.awt.*;
import java.awt.event.*;

public class FlowLayoutDemo extends Frame implements ItemListener {
    String msg = "";
    Checkbox windows, android, solaris, mac;

    public FlowLayoutDemo() {

        // использовать диспетчер поточной компоновки
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // создать флажки windows
        windows = new Checkbox("Windows", true);
        android = new Checkbox("Android");
        solaris = new Checkbox("Solaris");
        mac = new Checkbox("Mac OS");

        // ввести флажки в фрейм
        add(windows);
        add(android);
        add(solaris);
        add(mac);

        // ввести приемники событий в отдельных элементах
        windows.addItemListener(this);

        android.addItemListener(this);
        solaris.addItemListener(this);
        mac.addItemListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void itemStateChanged(ItemEvent ie) {
        repaint();
    }

    // отобразить текущее состояние флажков
    public void paint(Graphics g) {
        msg = "Current state: ";
        g.drawString(msg, 20, 120);
        msg = "Windows: " + windows.getState();
        g.drawString(msg, 20, 140);
        msg = "Android: " + android.getState();
        g.drawString(msg, 20, 160);
        msg = "Solaris: " + solaris.getState();
        g.drawString(msg, 20, 180);
        msg = "Маc OS: " + mac.getState();
        g.drawString(msg, 20, 200);
    }

    public static void main(String[] args) {
        FlowLayoutDemo appwin = new FlowLayoutDemo();

        appwin.setSize(new Dimension(240, 220));
        appwin.setTitle("CheckboxDemo");
        appwin.setVisible(true);
    }
}
