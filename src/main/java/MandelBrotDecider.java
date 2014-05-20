public class MandelBrotDecider {

    public MandelBrotDecider(int maxIterationAllowed){
        if(maxIterationAllowed<0){
            throw new IllegalArgumentException("maxIterationAllowed must be greater than 0");
        }
    }

    public boolean isPartOfMandelBrot(float x, float y) {
        if (x==-2){
            return false;
        }
        return true;
    }
}
