import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AppFrame extends JFrame {
    MainPanel mainPane = new MainPanel();

    public AppFrame() {
        setTitle("Сравнение таблиц");
        setSize(new Dimension(1000,1000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(mainPane);

        setLocationRelativeTo(null);  // Установить открытие окна по центру экрана.
        setVisible(true);
    }
}

