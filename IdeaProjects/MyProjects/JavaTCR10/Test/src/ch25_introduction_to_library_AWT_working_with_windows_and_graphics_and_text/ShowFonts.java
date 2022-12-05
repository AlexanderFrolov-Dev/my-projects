package ch25_introduction_to_library_AWT_working_with_windows_and_graphics_and_text;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ShowFonts extends Frame {
    String msg = "First five fonts: ";
    GraphicsEnvironment graphicsEnvironment;

    public ShowFonts() throws HeadlessException {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontList = graphicsEnvironment.getAvailableFontFamilyNames();

        for (int i = 0; (i < 5) && (i < fontList.length); i++) {
            msg += fontList[i] + " ";
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(msg, 10,60);
    }

    public static void main(String[] args) {
        ShowFonts showFonts = new ShowFonts();

        showFonts.setSize(new Dimension(500,100));
        showFonts.setTitle("ShowFonts");
        showFonts.setVisible(true);
    }
}
