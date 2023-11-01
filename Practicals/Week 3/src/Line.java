public class Line {
    private Point start;
    private Point end;
    private double distance;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.distance = this.lineLength(start, end);
    }

    public Line() {
        this(new Point(0,0), new Point(0,0));
    }

    public static double lineLength(Point start, Point end) {
        return Math.sqrt(Math.pow((end.getX() - start.getX()), 2) + Math.pow((end.getY() - start.getY()), 2));
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public void moveStart(float deltaX, float deltaY) {
        this.start = this.start.movePoint(deltaX, deltaY);
        this.distance = lineLength(start, end);
    }

    public void moveEnd(float deltaX, float deltaY) {
        this.end = this.end.movePoint(deltaX, deltaY);
        this.distance = lineLength(start, end);
    }

    public Point middle() {
        return new Point((start.getX() + end.getX())/2, (start.getY() + end.getY())/2);
    }

    public Line flipLine() {
        return new Line(start.flipPoint(), end.flipPoint());
    }

    @Override
    public String toString() {
        return "Line{" +
                "start=" + start +
                ", end=" + end +
                ", distance=" + distance +
                '}';
    }

    public static void main(String[] args) {
        Point s = new Point(4, 5);
        Point e = new Point(47, 43);

        Line lin = new Line(s, e);
        System.out.println("Line length = " + Line.lineLength(lin.getStart(), lin.getEnd()));
        System.out.println("Line position = " + lin);
        lin.moveStart(-3,-20);
        lin.moveEnd(-70,60);
        System.out.println("New Line length = " + Line.lineLength(lin.getStart(), lin.getEnd()));
        System.out.println("Line position = " + lin);

        System.out.println(lin.flipLine());
    }
}
