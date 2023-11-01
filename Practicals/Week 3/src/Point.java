public class Point {
    private float x_cor;
    private float y_cor;

    public Point(float x, float y) {
        this.x_cor = x;
        this.y_cor = y;
    }

    public Point() {
        this(0, 0);
    }

    public float getX() {
        return x_cor;
    }

    public float getY() {
        return y_cor;
    }

    public Point movePoint(float deltaX, float deltaY) {
        return new Point(this.x_cor + deltaX, this.y_cor + deltaY);
    }

    public Line createLine(Point end) {
        return new Line(this, end);
    }

    public Point flipPoint() {
        return new Point(-1*x_cor, -1*y_cor);
    }

    @Override
    public String toString() {
        return "(" + x_cor + ", " + y_cor + ")";
    }

    public static void main(String[] args) {
        Point exp = new Point();
        Point newPoint = exp.movePoint(2,3);
        System.out.println(newPoint.getX());
    }
}
