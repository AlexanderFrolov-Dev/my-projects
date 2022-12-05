import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {
    JPanel contentPanel = new JPanel();
    GuessColour guessColour = new GuessColour();
    JMenuBar jMenuBar = new JMenuBar();

    JButton clubs = new JButton("Треф");
    JButton diamonds = new JButton("Бубны");
    JButton hearts = new JButton("Червы");
    JButton spades = new JButton("Пики");

    public MainFrame() throws IOException {
        setTitle("Угадай карту");
        setIconImage(ImageIO.read(new File("src/logo/brain-20424_640.jpg")));
        setSize(new Dimension(1200,1000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setContentPane(contentPanel);

        contentPanel.setLayout(new CardLayout());

        contentPanel.add(guessColour);

        setVisible(true);
    }
}
