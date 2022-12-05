package ch25_introduction_to_library_AWT_working_with_windows_and_graphics_and_text;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GraphicsDemo extends Frame {

    public GraphicsDemo() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g = (Graphics2D) g;

        g.drawLine(20,40,100,90);
        g.drawLine(20,90,100,40);
        g.drawLine(40,45,200,80);

        g.drawRect(20,150,60,50);
        g.fillRect(110,150,60,50);
        g.drawRoundRect(200,150,60,50,15,15);
        g.fillRoundRect(290,150,60,50,30,40);

        g.drawOval(20,250,50,50);
        g.fillOval(100,250,75,50);
        g.drawOval(200,260,100,40);

        g.drawArc(20,350,70,70,0,180);
        g.fillArc(70,350,70,70,0,75);

        int[] xpoins = {20,200,20,200,20};
        int[] ypoins = {450,450,650,650,450};
        int num = 5;

        g.drawPolygon(xpoins, ypoins, num);

        g.draw3DRect(300,500, 50, 100, true);
    }

    public static void main(String[] args) {
        GraphicsDemo graphicsDemo = new GraphicsDemo();

        graphicsDemo.setSize(new Dimension(370,700));
        graphicsDemo.setTitle("GraphicsDemo");
        graphicsDemo.setVisible(true);
    }
}
