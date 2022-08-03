// 209345180 Aharon Gross

import java.util.List;

/**
 * This class represents a line in the plane.
 * The class has constructors, getters and various other methods.
 *
 * @author Aharon Gross
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * This method is a constructor for the class.
     *
     * @param start - The beginning of the line.
     * @param end   - The end of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * This method is a constructor for the class.
     *
     * @param x1 - The x coordinate of the starting point of the line.
     * @param y1 - The y coordinate of the starting point of the line.
     * @param x2 - The x coordinate of the starting point of the line.
     * @param y2 - The y coordinate of the starting point of the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * This method is a getter for the start of a line.
     *
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * This method is a getter for the end of a line.
     *
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * This method finds the length of a line.
     *
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * This method finds the middle of a line.
     *
     * @return the point of the middle of the line.
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;

        return new Point(x, y);
    }


    /**
     * This method calculates the slope of a line.
     *
     * @return the slope of the line.
     */
    public double slope() {
        double incY = this.start.getY() - this.end.getY();
        double incX = this.start.getX() - this.end.getX();

        return (incY / incX);
    }

    /**
     * This method checks which x coordinate of the line is bigger,
     * the start's x coordinate or the end's.
     *
     * @return start/end point - whichever x coordinate is bigger.
     */
    public Point maxX() {
        if (GeneralCalcs.isBigger(this.start.getX(), this.end.getX())) {
            return this.start;
        }
        return this.end;
    }

    /**
     * This method checks which x coordinate of the line is smaller,
     * the start's x coordinate or the end's.
     *
     * @return start/end point - whichever x coordinate is smaller.
     */
    public Point minX() {
        if (GeneralCalcs.isBigger(this.start.getX(), this.end.getX())) {
            return this.end;
        }
        return this.start;
    }

    /**
     * This method checks which y coordinate of the line is bigger,
     * the start's y coordinate or the end's.
     *
     * @return start/end point - whichever y coordinate is bigger.
     */
    public Point maxY() {
        if (GeneralCalcs.isBigger(this.start.getY(), this.end.getY())) {
            return this.start;
        }
        return this.end;
    }

    /**
     * This method checks which y coordinate of the line is smaller,
     * the start's y coordinate or the end's.
     *
     * @return start/end point - whichever y coordinate is smaller.
     */
    public Point minY() {
        if (GeneralCalcs.isBigger(this.start.getY(), this.end.getY())) {
            return this.end;
        }
        return this.start;
    }

    /**
     * This method calculates the y-intercepts of the line.
     *
     * @return the lines y-intercept.
     */
    public double intercepts() {
        return (-(this.slope() * this.start.getX())) + this.start.getY();
    }

    /**
     * This method check whether 2 lines are parallel.
     *
     * @param other - the line to check parallelity with.
     * @return true if lines are parallel, false otherwise.
     */
    public boolean isParallel(Line other) {
        return (GeneralCalcs.isEqual(this.slope(), other.slope()));
    }

    /**
     * Check if the line is vertical to the y-axis.
     *
     * @return true if the line is vertical to the y-axis, false otherwise.
     */
    public boolean isVerticalToYAxis() {
        return GeneralCalcs.isEqual(this.start.getX(), this.end.getX());
    }

    /**
     * This method check whether 2 lines intersect.
     *
     * @param other - the line to check with.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        // Lines are parallel and intersect
        if (this.isParallel(other)) {
            return (GeneralCalcs.isEqual(this.intercepts(), other.intercepts())
                    && !GeneralCalcs.isBigger(other.minX().getX(), this.maxX().getX())
                    && !GeneralCalcs.isBigger(this.minX().getX(), other.maxX().getX())
                    && !GeneralCalcs.isBigger(other.minY().getY(), this.maxY().getY())
                    && !GeneralCalcs.isBigger(this.minY().getY(), other.maxY().getY()));
        }

        double interX, interY;
        // Line is vertical to Y-axis
        if (this.isVerticalToYAxis()) {
            interX = this.start.getX();
        } else {
            interX = (other.intercepts() - this.intercepts()) / (this.slope() - other.slope());
        }

        // x value of intersection isn't on this line
        if (GeneralCalcs.isBigger(interX, this.maxX().getX()) || GeneralCalcs.isBigger(this.minX().getX(), interX)) {
            return false;
        } else if (GeneralCalcs.isBigger(other.minY().getY(), this.maxY().getY())
                || GeneralCalcs.isBigger(this.minY().getY(),
                other.maxY().getY())) {
            return false;
        } else { // x value of intersection is on both lines
            return (!GeneralCalcs.isBigger(interX, other.maxX().getX())
                    && !GeneralCalcs.isBigger(other.minX().getX(), interX));
        }
    }

    /**
     * This method finds the intersection point of 2 lines.
     *
     * @param other - the line to find an intersection point with.
     * @return the intersection point, null if there is no intersection point.
     */
    public Point intersectionWith(Line other) {
        // No intersection point
        if (!this.isIntersecting(other) || !other.isIntersecting(this)) {
            return null;
        }

        // one line ends where the other begins
        if (this.isParallel(other)) {
            if (this.maxX().equals(other.minX())) {
                return this.maxX();
            } else if (other.maxX().equals(this.minX())) {
                return this.minX();
            }
            return null;
        }

        // Line is vertical to Y-axis
        if (this.isVerticalToYAxis()) {
            return new Point(this.end.getX(), (other.slope() * this.end.getX()) + other.intercepts());
        }
        if (other.isVerticalToYAxis()) {
            return new Point(other.end.getX(), (this.slope() * other.end.getX()) + this.intercepts());
        }

        // return intersection point
        double interX = (other.intercepts() - this.intercepts()) / (this.slope() - other.slope());
        double interY = this.slope() * interX + this.intercepts();
        return new Point(interX, interY);
    }

    /**
     * This method checks if 2 lines are equal.
     *
     * @param other - the line to check with.
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start)));
    }


    /**
     * Find the closest intersection with a given rectangle.
     *
     * @param rect - The rectangle to find the intersection with.
     * @return the closest intersection point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // No intersection
        if (GeneralCalcs.isBigger(this.minX().getX(), rect.getFrame().getMaxBorder().getX())
                || GeneralCalcs.isBigger(rect.getUpperLeft().getX(), this.maxX().getX())
                || GeneralCalcs.isBigger(this.minY().getY(), rect.getFrame().getMaxBorder().getY())
                || GeneralCalcs.isBigger(rect.getUpperLeft().getY(), this.maxY().getY())) {
            return null;
        }

        List<Point> list = rect.intersectionPoints(this);
        double distance = list.get(0).distance(this.start);
        int i = 0, index = 0;
        // Find the closest intersection point
        for (Point p : list) {
            if (GeneralCalcs.isBigger(distance, p.distance(this.start))) {
                distance = p.distance(this.start);
                index = i;
            }
            i++;
        }
        return list.get(index);
    }

    /**
     * Checks if a given point is on a given line.
     *
     * @param p - The point to check with.
     * @return true if the point is on the line, else return false.
     */
    public boolean pointOnLine(Point p) {
        return (!GeneralCalcs.isBigger(p.getX(), this.maxX().getX())
                && !GeneralCalcs.isBigger(this.minX().getX(), p.getX())
                && !GeneralCalcs.isBigger(p.getY(), this.maxY().getY())
                && !GeneralCalcs.isBigger(this.minY().getY(), p.getY()));
    }
}