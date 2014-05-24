import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class MandelBrotGeneratorTest {
    private final int screenWidth = 900;
    private final int screenHeight = 600;
    ScreenToWorldMapper mapper = new ScreenToWorldMapper(new Point(0,0),new Point(screenWidth,screenHeight),
    new Point(-2.5,-1.5), new Point(1.5,1.5));
    private MandelBrotSolver decider = new MandelBrotSolver(0,screenWidth,screenHeight,20, mapper);

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
