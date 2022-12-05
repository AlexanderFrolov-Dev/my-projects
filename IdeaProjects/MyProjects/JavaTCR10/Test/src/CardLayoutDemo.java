import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CardLayoutDemo extends Frame {
    Checkbox windows10;
    Checkbox windows7;
    Checkbox windows8;
    Checkbox android;
    Checkbox solaris;
    Checkbox mac;

    Panel osCards;

    CardLayout cardLO;

    Button Win;
    Button Other;

    public CardLayoutDemo() {

        // использовать диспетчер поточной компоновки для
        // размещения компонентов в главном фрейме
        setLayout(new FlowLayout());

        Win = new Button("Windows");
        Other = new Button("Other");
        add(Win);
        add(Other);

        // установить панель osCards для применения
        // карточной компоновки
        cardLO = new CardLayout();
        osCards = new Panel();
        osCards.setLayout(cardLO);

        windows7 = new Checkbox("Windows 7", true);
        windows8 = new Checkbox("Windows 8");
        windows10 = new Checkbox("Windows 10");
        android = new Checkbox("Android");
        solaris = new Checkbox("Solaris");
        mac = new Checkbox("Mac OS");

        // ввести на панели флажки для выбора
        // разных версий ОС Windows
        Panel winPan = new Panel();
        winPan.add(windows7);
        winPan.add(windows8);
        winPan.add(windows10);

        // ввести на панели флажки для выбора других ОС
        Panel otherPan = new Panel();
        otherPan.add(android);
        otherPan.add(solaris);
        otherPan.add(mac);

        // ввести панели отдельных карт на панели колоды карт
        osCards.add(winPan, "Windows");
        osCards.add(otherPan, "Other");

        // ввести карты на панели главного фрейма
        add(osCards);

        // воспользоваться лямбда-выражениями для
        // обработки событий в экранных кнопках
        Win.addActionListener((ae) -> cardLO.show(osCards, "Windows"));
        Other.addActionListener((ae) -> cardLO.show(osCards, "Other"));

        // зарегистрировать приемники событий действия
        addMouseListener(new MouseAdapter() {
            // перебрать панели карт
            @Override
            public void mousePressed(MouseEvent e) {
                cardLO.next(osCards);
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        CardLayoutDemo cld = new CardLayoutDemo();

        cld.setSize(new Dimension(300,220));
        cld.setTitle("CardLayoutDemo");
        cld.setVisible(true);
    }
}
