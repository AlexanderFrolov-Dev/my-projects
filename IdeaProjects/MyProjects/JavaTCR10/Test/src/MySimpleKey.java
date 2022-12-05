//import java.awt.*;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//
//public class MySimpleKeyWithKeyAdapter extends Frame implements KeyListener {
//    String msg = "";
//    String keyState = "";
//
//    public MySimpleKeyWithKeyAdapter() {
//        addKeyListener(this);
//        addWindowListener(new MyWindowAdapter2());
//    }
//
//    @Override
//    public void keyTyped(KeyEvent e) {
//        msg += e.getKeyChar();
//        repaint();
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        keyState = "Кнопка нажата";
//
//        int key = e.getKeyCode();
//
//        switch (key) {
//            case KeyEvent.VK_F1:
//                msg += "<F1>";
//                break;
//            case KeyEvent.VK_F2:
//                msg += "<F2>";
//                break;
//            case KeyEvent.VK_F3:
//                msg += "<F3>";
//                break;
//            case KeyEvent.VK_PAGE_DOWN:
//                msg += "<PgDn>";
//                break;
//            case KeyEvent.VK_PAGE_UP:
//                msg += "<PgUp>";
//                break;
//            case KeyEvent.VK_LEFT:
//                msg += "<Left>";
//                break;
//            case KeyEvent.VK_RIGHT:
//                msg += "<Right>";
//                break;
//        }
//
//        repaint();
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//        keyState = "Кнопка отпущена";
//        repaint();
//    }
//
//    @Override
//    public void paint(Graphics g) {
//        g.drawString(msg, 20, 100);
//        g.drawString(keyState,20,50);
//    }
//
//    public static void main(String[] args) {
//        MySimpleKeyWithKeyAdapter msk = new MySimpleKeyWithKeyAdapter();
//
//        msk.setSize(new Dimension(500, 350));
//        msk.setTitle("Мое первое окно для работы с клавиатурой");
//        msk.setVisible(true);
//    }
//}
//
//class MyWindowAdapter2 extends WindowAdapter {
//    @Override
//    public void windowClosing(WindowEvent e) {
//        System.exit(0);
//    }
//}