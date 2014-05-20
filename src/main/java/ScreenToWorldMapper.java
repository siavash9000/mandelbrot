
public class ScreenToWorldMapper {

    private final Point minScreen, maxScreen, minWorld, maxWorld;
    private float xScale, yScale;
    public ScreenToWorldMapper(Point minScreen, Point maxScreen, Point minWorld, Point maxWorld) {
        this.maxScreen = maxScreen;
        this.minScreen = minScreen;
        this.minWorld = minWorld;
        this.maxWorld = maxWorld;
        this.xScale = (maxWorld.getX() - minWorld.getX())/(maxScreen.getX()-minScreen.getX());
        this.yScale = (maxWorld.getY() - minWorld.getY())/(maxScreen.getY() - minScreen.getY());
    }

    public Point mapToWorld(Point screenPoint) {
        if (screenPoint == null)
            throw new NullPointerException("Point is null.");
        if (outsideOfScreen(screenPoint))
            throw new IllegalArgumentException(String.format("Given Point is outside of screen: %s",screenPoint.toString()));
        float worldX = minWorld.getX() + screenPoint.getX()*xScale;
        float worldY = minWorld.getY() + screenPoint.getY()*yScale;
        return new Point(worldX,worldY);
    }

    public boolean outsideOfScreen(Point point) {
        if (point.getX() < minScreen.getX() || point.getX() > maxScreen.getX())
            return true;
        if (point.getY() < minScreen.getY() || point.getY() > maxScreen.getY())
            return true;
        return false;
    }
}
