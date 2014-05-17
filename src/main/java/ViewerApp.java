import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import javax.imageio.*;
import javax.swing.*;

/**
 * This class demonstrates how to load an Image from an external file
 */
public class ViewerApp extends Component {

    BufferedImage img;

    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    public ViewerApp() {
        try {
            URL stream = getClass().getResource("tiger.jpg");
            img = ImageIO.read(stream);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public Dimension getPreferredSize() {
        if (img == null) {
            return new Dimension(100,100);
        } else {
            return new Dimension(img.getWidth(null), img.getHeight(null));
        }
    }

    public static void main(String[] args) {

        JFrame f = new JFrame("Load Image Sample");

        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        f.add(new ViewerApp());
        f.pack();
        f.setVisible(true);
    }
}