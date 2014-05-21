public class MandelBrotDecider {

    private int maxIterationAllowed = 20;
    public boolean isPartOfMandelBrot(Point worldPoint) {
        double a = worldPoint.getX();
        double b = worldPoint.getY();
        double lastx = 0, lasty = 0, nextx, nexty;
        for (int i=0;i<maxIterationAllowed;i++) {
            if ((Math.pow(lastx,2) + Math.pow(lasty,2)) > 4)
                return false;
            nextx = Math.pow(lastx,2) - Math.pow(lasty,2) + a;
            nexty = 2*lastx*lasty + b;
            lastx = nextx;
            lasty = nexty;
        }
        return true;
    }
}
