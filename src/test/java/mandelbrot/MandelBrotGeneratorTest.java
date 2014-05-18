package mandelbrot;

import junit.framework.Assert;
import org.junit.Test;

public class MandelBrotGeneratorTest {

    private MandelBrotGenerator generator = new MandelBrotGenerator();
    @Test()
    public void getImage(){
        Assert.assertNull(generator.getImage());
    }
}
