import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class GuessColour extends JPanel {
    JPanel leftPanel = new JPanel();
    JPanel centralPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel topPanel = new JPanel();
    ButtonGroup numberOfAttempts = new ButtonGroup();
    JRadioButton tenAttempts = new JRadioButton("10");
    JRadioButton twentyAttempts = new JRadioButton("20");
    JRadioButton thirtyAttempts = new JRadioButton("30");
    JRadioButton fortyAttempts = new JRadioButton("40");
    JRadioButton fiftyAttempts = new JRadioButton("50");
    ImageIcon hearts = new ImageIcon("src/images_for_buttons/heart-icon_34407.png");
    ImageIcon spades = new ImageIcon("src/images_for_buttons/spades_icon-icons.com_70867.png");
    JButton red = new JButton("Красная", hearts);
    JButton black = new JButton("Чёрная", spades);
    JButton start = new JButton("Старт");
    JLabel cardBackImage = new JLabel(new ImageIcon("src/card_back/card-game-48980_640.png"));
    JLabel timerLabel = new JLabel("X");
    Timer timer = new Timer();

    public GuessColour() {
        setLayout(new BorderLayout());

        red.setAlignmentX(Component.CENTER_ALIGNMENT);
        black.setAlignmentX(Component.CENTER_ALIGNMENT);
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardBackImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        red.setMaximumSize(new Dimension(150,50));
        black.setMaximumSize(new Dimension(150,50));
        start.setMaximumSize(new Dimension(150,50));
        timerLabel.setMinimumSize(new Dimension(150,50));

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

        leftPanel.add(Box.createRigidArea(new Dimension(250,150)));
        leftPanel.add(start);
        leftPanel.add(Box.createRigidArea(new Dimension(250,100)));
        leftPanel.add(red);
        leftPanel.add(Box.createRigidArea(new Dimension(250,10)));
        leftPanel.add(black);

        centralPanel.add(Box.createRigidArea(new Dimension(500, 25)));
        centralPanel.add(cardBackImage);

        rightPanel.add(Box.createRigidArea(new Dimension(50, 400)));
        timerLabel.setBackground(Color.ORANGE);
        rightPanel.add(timerLabel);

        numberOfAttempts.add(tenAttempts);
        numberOfAttempts.add(twentyAttempts);
        numberOfAttempts.add(thirtyAttempts);
        numberOfAttempts.add(fortyAttempts);
        numberOfAttempts.add(fiftyAttempts);

        topPanel.add(tenAttempts);
        topPanel.add(twentyAttempts);
        topPanel.add(thirtyAttempts);
        topPanel.add(fortyAttempts);
        topPanel.add(fiftyAttempts);

        centralPanel.setBackground(Color.PINK);
        topPanel.setBackground(Color.CYAN);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.scheduleAtFixedRate(new TimerTask() {
                    int i = 10;
                    @Override
                    public void run() {
                        timerLabel.setText(String.valueOf(i));
                        i--;

                        if (i < 0) {
                            timer.cancel();
                            timerLabel.setText("O");
                        }
                    }
                }, 0, 1000);

            }
        });

//        numberOfAttempts.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                timerLabel.setText(e.getActionCommand());
//            }
//        });

        add(leftPanel, BorderLayout.WEST);
        add(centralPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);
    }
}

// if() game start, number enabled

//        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
//
//        red.setAlignmentX(Component.CENTER_ALIGNMENT);
//        black.setAlignmentX(Component.CENTER_ALIGNMENT);
//        cardBackImage.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        red.setMaximumSize(new Dimension(100,30));
//        black.setMaximumSize(new Dimension(100,30));
//
//        red.setBackground(Color.red);
//        black.setBackground(Color.gray);
//
//        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
//
//        leftPanel.add(red);
//        leftPanel.add(Box.createRigidArea(new Dimension(250,10)));
//        leftPanel.add(black);
//
//        centralPanel.add(cardBackImage);
//
//        add(leftPanel);
//        add(centralPanel);


//        start.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                timer.scheduleAtFixedRate(new TimerTask() {
//                    int i = 20;
//                    @Override
//                    public void run() {
//                        timerLabel.setText(String.valueOf(i));
//                        start.setText(STOP);
//                        i--;
//
//                        if (i < 0) {
//                            timer.cancel();
//                            start.setText(START);
//                        }
//                    }
//                }, 0, 1000);
//            }
//        });