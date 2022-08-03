// 209345180 Aharon Gross

/**
 * This class represents a point in the plane.
 * The class has a constructor, getters and various other methods.
 *
 * @author Aharon Gross
 */
public class Point {
    private double x;
    private double y;

    /**
     * This method is the constructor for the class.
     *
     * @param x - The x coordinate of the point.
     * @param y - The y coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * x getter method.
     *
     * @return x parameter of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * y getter method.
     *
     * @return y parameter of this point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * This method calculates the distance between 2 points.
     *
     * @param other - the point to check the distance from.
     * @return d - the distance between the points.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * This method check whether 2 points are equal.
     *
     * @param other - the point to compare with.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return (GeneralCalcs.isEqual(this.x, other.x) && GeneralCalcs.isEqual(this.y, other.y));
    }

    /**
     * Check if a point is inside a frame.
     * @param frame - The frame to check with.
     * @return true if the point is in the frame, false otherwise.
     */
    public boolean inFrame(Frame frame) {
        return (!GeneralCalcs.isBigger(this.x, frame.getMaxBorder().getX())
                && !GeneralCalcs.isBigger(this.y, frame.getMaxBorder().getY())
                && !GeneralCalcs.isBigger(frame.getMinBorder().getX(), this.x)
                && !GeneralCalcs.isBigger(frame.getMinBorder().getY(), this.y));
    }

    /**
     * If the point is in the frame, push it out of the frame.
     * @param frame - The frame to push out of.
     */
    public void pushOutOfFrame(Frame frame) {
        if (inFrame(frame)) {
            double distance = this.distance(frame.getMinBorder());
            Point p = frame.getMinBorder();
            if (GeneralCalcs.isBigger(distance, this.distance(frame.getMaxBorder()))) {
                distance = this.distance(frame.getMaxBorder());
                p = frame.getMaxBorder();
            }
            if (GeneralCalcs.isBigger(distance, this.distance(frame.bottomLeftPoint()))) {
                distance = this.distance(frame.bottomLeftPoint());
                p = frame.bottomLeftPoint();
            }
            if (GeneralCalcs.isBigger(distance, this.distance(frame.topRightPoint()))) {
                this.distance(frame.topRightPoint());
                p = frame.topRightPoint();
            }
            this.x = p.getX();
            this.y = p.getY();
        }
    }
}