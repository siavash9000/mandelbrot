import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MandelBrotGenerator {
    private int screenWidth;
    private int screenHeight;

    private ScreenToWorldMapper mapper;
    private int maxIterationAllowed;
    private int numberOfCores = Runtime.getRuntime().availableProcessors();
    private ExecutorService executorService = Executors.newCachedThreadPool();

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
        int numberOfColumnsPerBlock = screenWidth/numberOfCores;
        ArrayList<boolean[][]> partialSolutions = new ArrayList<boolean[][]>(numberOfCores+1);
        for (int i=0;i<numberOfCores;i++){
            partialSolutions.add(getSubPicture(i*numberOfColumnsPerBlock,(i+1)*numberOfColumnsPerBlock));
        }
        boolean[][] solution = new boolean[screenWidth][screenHeight];
        for (int i=0;i<numberOfCores;i++) {
            System.arraycopy(partialSolutions.get(i), 0, solution, i*numberOfColumnsPerBlock, partialSolutions.get(i).length);
        }
        return solution;
    }

    private boolean[][] getSubPicture(int startingColumn, int endColumn) {
        boolean[][] screenPoints = new boolean[Math.abs(startingColumn-endColumn)][screenHeight];
        for(int i=startingColumn;i< endColumn;i++) {
            for(int j=0;j< screenHeight;j++) {
                screenPoints[i-startingColumn][j] = isPartOfMandelBrot(mapper.mapToWorld(new Point(i, j)));
            }
        }
        return screenPoints;
    }
}
