package ch26_using_controls_and_layout_controllers_and_menus_from_AWT_library;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CBGroup extends Frame implements ItemListener {
    String msg = "";
    Checkbox windows;
    Checkbox android;
    Checkbox solaris;
    Checkbox mac;
    CheckboxGroup checkboxGroup;

    public CBGroup() {
        setLayout(new FlowLayout());

        checkboxGroup = new CheckboxGroup();

        windows = new Checkbox("Windows", checkboxGroup, true);
        android = new Checkbox("Android", checkboxGroup, false);
        solaris = new Checkbox("Solaris", checkboxGroup, false);
        mac = new Checkbox("Mac", checkboxGroup, false);

        add(windows);
        add(android);
        add(solaris);
        add(mac);

        windows.addItemListener(this);
        android.addItemListener(this);
        solaris.addItemListener(this);
        mac.addItemListener(this);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        msg = "Current selection: ";
        msg += checkboxGroup.getSelectedCheckbox().getLabel();
        g.drawString(msg, 20,120);
    }

    public static void main(String[] args) {
        CBGroup cbGroup = new CBGroup();

        cbGroup.setSize(new Dimension(240, 180));
        cbGroup.setTitle("CBGroup");
        cbGroup.setVisible(true);
    }
}
