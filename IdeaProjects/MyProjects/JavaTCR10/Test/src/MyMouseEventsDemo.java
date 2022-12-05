import java.awt.*;
import java.awt.event.*;

public class MyMouseEventsDemo extends Frame implements MouseListener, MouseMotionListener {

    private String msg = "";
    private int mouseX;
    private int mouseY;

    public MyMouseEventsDemo() {
        addMouseListener(this);
        addMouseMotionListener(this);
        addWindowListener(new MyOwnWindowAdapter());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        msg = msg + " -- клик получен";
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        msg = "Кнопка мыши нажата";
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        msg = "Кнопка мыши отпущена";
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseX = 200;
        mouseY = 200;
        msg = "Курсор мыши наведен на компонент";
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseX = 150;
        mouseY = 150;
        msg = "Курсор мыши отведен от компонента";
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        msg = "*" + " Перетаскивание объекта мышью " + mouseX + ", " + mouseY;
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        msg = "Перемещение курсора мыши" + e.getX() + ", " + e.getY();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(msg, mouseX, mouseY);
    }

    public static void main(String[] args) {
        MyMouseEventsDemo mmed = new MyMouseEventsDemo();

        mmed.setSize(new Dimension(500, 500));
        mmed.setTitle("Мое первое окно для работы с мышью");
        mmed.setVisible(true);
    }
}

class MyOwnWindowAdapter extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}