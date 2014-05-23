import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * This class demonstrates how to load an Image from an external file
 */
public class ViewerApp extends Component {
    private final int screenWidth = 900;
    private final int screenHeight = 600;
    BufferedImage img;
    private MandelBrotDecider decider = new MandelBrotDecider(screenWidth,screenHeight,20);

    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    public ViewerApp() {
        img = new BufferedImage(screenWidth, screenHeight,BufferedImage.TYPE_INT_RGB);
        double start = System.currentTimeMillis();
        boolean[][] screenPoints = decider.getMandelBrotPicture();
        System.out.println(String.format("%3f milliseconds", System.currentTimeMillis() - start));
        for(int i=0;i< screenWidth;i++) {
            for(int j=0;j< screenHeight;j++) {
            if(screenPoints[i][j]) {
                img.setRGB(i, j, Color.black.getRGB());
            }
            else {
                img.setRGB(i, j, Color.blue.getRGB());
            }
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