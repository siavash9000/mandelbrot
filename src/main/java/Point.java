public class Point {
    private final float x,y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null && this==null){
            return true;
        }
        if ((obj!=null && this==null)||(obj==null && this!=null)){
            return false;
        }
        if (!(obj instanceof Point)){
            return false;
        }
        Point that = (Point) obj;
        if (that.x == this.x && that.y == this.y) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString(){
        return String.format("(%4f,%4f)",x,y);
    }
}
