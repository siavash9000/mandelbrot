import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class MandelBrotDeciderTest {

    private int maxiterationAllowed = 0;
    private MandelBrotDecider decider = new MandelBrotDecider(maxiterationAllowed);

    @Test(expected = IllegalArgumentException.class)
    public void exceptionOnNegativeIteration() {
        new MandelBrotDecider(-1);
    }

    @Test
    public void originIsInMandelbrot(){
        assertTrue(decider.isPartOfMandelBrot(0, 0));
    }

    @Test
    public void notInMandelbrotOne(){
        assertFalse(decider.isPartOfMandelBrot(-2, 0));
    }
}
