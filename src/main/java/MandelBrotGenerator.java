public class MandelBrotGenerator {
    private int screenWidth;
    private int screenHeight;

    private ScreenToWorldMapper mapper;
    private int maxIterationAllowed;

    public MandelBrotGenerator(int screenWidth, int screenHeight, int maxIterationAllowed) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.mapper = new ScreenToWorldMapper(new Point(0,0),new Point(screenWidth,screenHeight),
                new Point(-2.5,-1.5), new Point(1.5,1.5));
        this.maxIterationAllowed = maxIterationAllowed;
    }

    public boolean isPartOfMandelBrot(Point worldPoint) {
        double a = worldPoint.getX();
        double b = worldPoint.getY();
        double lastx = 0, lasty = 0, nextx, nexty;
        for (int i=0;i<maxIterationAllowed;i++) {
            double squaredX = lastx * lastx;
            double squaredY = lasty * lasty;
            if ((squaredX + squaredY) > 4)
                return false;
            nextx = squaredX - squaredY + a;
            nexty = 2*lastx*lasty + b;
            lastx = nextx;
            lasty = nexty;
        }
        return true;
    }

    public boolean[][] getMandelBrotPicture() {
        boolean[][] screenPoints = new boolean[screenWidth][screenHeight];
        for(int i=0;i< screenWidth;i++) {
            for(int j=0;j< screenHeight;j++) {
                    screenPoints[i][j] = isPartOfMandelBrot(mapper.mapToWorld(new Point(i, j)));
            }
        }
        return screenPoints;
    }
}
