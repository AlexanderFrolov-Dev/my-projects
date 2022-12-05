package ch27_images;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class SimpleImageLoad extends Frame {
    Image img;

    public SimpleImageLoad() {
        try {
            File imageFile = new File("Lilies.jpg");

            // загрузить изображение
            img = ImageIO.read(imageFile);
        } catch (IOException exc) {
            System.out.println("Cannot load image file. (Не удается загрузить файл изображения.)");
            System.exit(0);
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, getInsets().left, getInsets().top, null);
    }

    public static void main(String[] args) {
        SimpleImageLoad sil = new SimpleImageLoad();

        sil.setSize(new Dimension(400,365));
        sil.setTitle("SimpleImageLoad");
        sil.setVisible(true);
    }
}
