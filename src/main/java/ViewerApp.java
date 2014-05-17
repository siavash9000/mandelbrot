import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
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
        img = new BufferedImage(800,400,BufferedImage.TYPE_INT_RGB);
        for(int i=0;i<800;i++) {
            for(int j=0;j<400;j++) {
                Color randomColor = new Color((int)(Math.random() * 0x1000000));
                img.setRGB(i, j, randomColor.getRGB());
            }
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(img.getWidth(null), img.getHeight(null));
    }

    public static void main(String[] args) {

        JFrame f = new JFrame("MandelBrot");

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