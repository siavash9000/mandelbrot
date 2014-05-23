import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class MandelBrotGeneratorTest {
    private final int screenWidth = 900;
    private final int screenHeight = 600;
    private MandelBrotGenerator decider = new MandelBrotGenerator(screenWidth,screenHeight,20);

    @Test
    public void inMandelbrot(){
        assertTrue(decider.isPartOfMandelBrot(new Point(0, 0)));
        assertTrue(decider.isPartOfMandelBrot(new Point(0.1, 0)));
        assertTrue(decider.isPartOfMandelBrot(new Point(0.2, -0.1)));
        assertTrue(decider.isPartOfMandelBrot(new Point(-0.1, 0.2)));
    }

    @Test
     public void notInMandelbrot(){
        assertFalse(decider.isPartOfMandelBrot(new Point(-2, -1)));
        assertFalse(decider.isPartOfMandelBrot(new Point(2, 1)));
        assertFalse(decider.isPartOfMandelBrot(new Point(-2, 1)));
        assertFalse(decider.isPartOfMandelBrot(new Point(-2, 1)));
    }
}
