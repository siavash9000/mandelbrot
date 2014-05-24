import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.concurrent.*;

public class MandelBrotGenerator {
    private int screenWidth;
    private int screenHeight;

    private ScreenToWorldMapper mapper;
    private int maxIterationAllowed;
    private int numberOfCores;
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public MandelBrotGenerator(int screenWidth, int screenHeight, int maxIterationAllowed, int numberOfCores) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.mapper = new ScreenToWorldMapper(new Point(0,0),new Point(screenWidth,screenHeight),
                new Point(-2.5,-1.5), new Point(1.5,1.5));
        this.maxIterationAllowed = maxIterationAllowed;
        this.numberOfCores = numberOfCores;
    }

    public boolean[][] getMandelBrotPicture() throws ExecutionException, InterruptedException {
        int numberOfColumnsPerBlock = screenWidth/numberOfCores;
        ArrayList<Future<boolean[][]>> partialSolutions = new ArrayList<Future<boolean[][]>>(numberOfCores+1);
        for (int i=0;i<numberOfCores;i++){
            partialSolutions.add(executorService.submit(new MandelBrotSolver((i*numberOfColumnsPerBlock),
                    (i+1)*numberOfColumnsPerBlock,screenHeight,maxIterationAllowed,mapper)));
        }
        boolean[][] solution = new boolean[screenWidth][screenHeight];
        for (int i=0;i<numberOfCores;i++) {
            System.arraycopy(partialSolutions.get(i).get(), 0, solution, i*numberOfColumnsPerBlock, partialSolutions.get(i).get().length);
        }
        return solution;
    }
}

class MandelBrotSolver implements Callable<boolean[][]> {

    private int startingColumn;
    private int endColumn;
    private int screenHeight;
    private int maxIterationAllowed;
    private ScreenToWorldMapper mapper;

    public MandelBrotSolver(int startingColumn, int endColumn, int screenHeight, int maxIterationAllowed, ScreenToWorldMapper mapper) {
        this.startingColumn = startingColumn;
        this.endColumn = endColumn;
        this.screenHeight = screenHeight;
        this.maxIterationAllowed = maxIterationAllowed;
        this.mapper = mapper;
    }

    @Override
    public boolean[][] call() throws Exception {
        boolean[][] screenPoints = new boolean[Math.abs(startingColumn-endColumn)][screenHeight];
        for(int i=startingColumn;i< endColumn;i++) {
            for(int j=0;j< screenHeight;j++) {
                screenPoints[i-startingColumn][j] = isPartOfMandelBrot(mapper.mapToWorld(new Point(i, j)));
            }
        }
        return screenPoints;
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
}
