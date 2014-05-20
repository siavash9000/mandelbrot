import com.sun.javaws.exceptions.InvalidArgumentException;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class ScreenToWorldMapperTest {

    private final Point minScreen = new Point(0,0), maxScreen = new Point(400,800),
                        minWorld = new Point(-2,-1), maxWorld = new Point(1,1);
    private ScreenToWorldMapper mapper = new ScreenToWorldMapper(minScreen,maxScreen,minWorld,maxWorld);

    @Test(expected = IllegalArgumentException.class)
    public void outsideOfScreenPoint(){
        Point outsider = new Point(-1,0);
        mapper.mapToWorld(outsider);
    }

    @Test(expected = NullPointerException.class)
    public void inputIsNull(){
        mapper.mapToWorld(null);
    }

    @Test
    public void originMapsToOrigin(){
        Assert.assertEquals(minWorld, mapper.mapToWorld(minScreen));
    }

    @Test
    public void rightUpperCornerMapsToRightUpperCorner() {
        Assert.assertEquals(maxWorld, mapper.mapToWorld(maxScreen));
    }

    @Test
    public void leftUpperCornerMapsToLeftUpperCorner() {
        Point leftUpperCornerScreen = new Point(minScreen.getX(),maxScreen.getY());
        Point leftUpperCornerWorld  = new Point(minWorld.getX(),maxWorld.getY());
        Assert.assertEquals(leftUpperCornerWorld, mapper.mapToWorld(leftUpperCornerScreen));
    }

    @Test
    public void rightLowerCornerMapsToRightLowerCorner() {
        Point rightLowerCornerScreen = new Point(maxScreen.getX(),minScreen.getY());
        Point rightLowerCornerWorld  = new Point(maxWorld.getX(),minWorld.getY());
        Assert.assertEquals(rightLowerCornerWorld, mapper.mapToWorld(rightLowerCornerScreen));
    }
}
