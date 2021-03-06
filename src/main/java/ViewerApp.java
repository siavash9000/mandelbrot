import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.swing.*;

public class ViewerApp extends Component {
    private final int screenWidth = 1500;
    private final int screenHeight = 1000;
    BufferedImage img;
    private MandelBrotGenerator decider = new MandelBrotGenerator(screenWidth,screenHeight,1000,10, Executors.newCachedThreadPool());

    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    public ViewerApp() {
        img = new BufferedImage(screenWidth, screenHeight,BufferedImage.TYPE_INT_RGB);
        double start = System.currentTimeMillis();
        boolean[][] screenPoints = new boolean[0][];
        try {
            screenPoints = decider.getMandelBrotPicture();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("%3f milliseconds", System.currentTimeMillis() - start));
        for(int i=0;i< screenWidth;i++) {
            for(int j=0;j< screenHeight;j++) {
                if(screenPoints[i][j]) {
                    img.setRGB(i, j, Color.black.getRGB());
                }
                else {
                    img.setRGB(i, j, Color.white.getRGB());
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