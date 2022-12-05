import java.awt.*;
import java.awt.event.*;

public class MySimpleKeyWithKeyAdapter extends Frame {
    String msg = "";
    String keyState = "";

    public MySimpleKeyWithKeyAdapter() {
        addKeyListener(new MyKeyAdapter(this));
        addWindowListener(new MyWindowAdapter2());
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(msg, 20, 100);
        g.drawString(keyState,20,50);
    }

    public static void main(String[] args) {
        MySimpleKeyWithKeyAdapter msk = new MySimpleKeyWithKeyAdapter();

        msk.setSize(new Dimension(500, 350));
        msk.setTitle("Мое первое окно для работы с клавиатурой");
        msk.setVisible(true);
    }
}

class MyKeyAdapter extends KeyAdapter {
    MySimpleKeyWithKeyAdapter mskwka;

    public MyKeyAdapter(MySimpleKeyWithKeyAdapter mskwka) {
        this.mskwka = mskwka;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        mskwka.keyState = "Кнопка нажата";

        int key = e.getKeyCode();

        if ((e.getKeyChar() >= 'A') && (e.getKeyChar() <= 'z')) {
            mskwka.msg += e.getKeyChar();
        } else {
            switch (key) {
                case KeyEvent.VK_F1:
                    mskwka.msg += "<F1>";
                    break;
                case KeyEvent.VK_F2:
                    mskwka.msg += "<F2>";
                    break;
                case KeyEvent.VK_F3:
                    mskwka.msg += "<F3>";
                    break;
                case KeyEvent.VK_PAGE_DOWN:
                    mskwka.msg += "<PgDn>";
                    break;
                case KeyEvent.VK_PAGE_UP:
                    mskwka.msg += "<PgUp>";
                    break;
                case KeyEvent.VK_LEFT:
                    mskwka.msg += "<Left>";
                    break;
                case KeyEvent.VK_RIGHT:
                    mskwka.msg += "<Right>";
                    break;
            }
        }



        mskwka.repaint();
    }
}

class MyWindowAdapter2 extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
