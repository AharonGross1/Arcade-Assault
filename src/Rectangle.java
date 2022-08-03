// 209345180 Aharon Gross

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a rectangle, a more abstract form of a frame.
 */
public class Rectangle {
    private Frame frame;

    /**
     * Class constructor.
     * @param upperLeft - the upper left corner of the rectangle .
     * @param width - The width of the rectangle.
     * @param height - The height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.frame = new Frame((int) upperLeft.getX(), (int) upperLeft.getY(), (int) (width + upperLeft.getX()),
                (int) (height + upperLeft.getY()), Color.WHITE);
    }

    /**
     * Getter for the rectangle's frame.
     * @return the rectangle's frame.
     */
    public Frame getFrame() {
        return this.frame;
    }

    /**
     * Getter for the rectangle's width.
     * @return the rectangle's width.
     */
    public double getWidth() {
        return this.frame.frameWidth();
    }

    /**
     * Getter for the rectangle's height.
     * @return the rectangle's height.
     */
    public double getHeight() {
        return this.frame.frameHeight();
    }

    /**
     * Getter for the upper-left point of the rectangle.
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.frame.getMinBorder();
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line - The line to check intersections with.
     * @return the intersection list.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<>();
        Line[] lArr = new Line[4];
        lArr[0] = this.frame.leftSideOfFrameLine();
        lArr[1] = this.frame.rightSideOfFrameLine();
        lArr[2] = this.frame.topOfFrameLine();
        lArr[3] = this.frame.bottomOfFrameLine();
        for (Line l : lArr) {
            boolean isUnique = true;
            // Lines are vertical
            if (l.isVerticalToYAxis() && line.isVerticalToYAxis()) {
                continue;
            }
            if (l.slope() == 0 && line.slope() == 0) {
                continue;
            }
            Point point = line.intersectionWith(l);
            if (point != null) {
                // Check duplicate points
                for (Point p : list) {
                    if (point.equals(p)) {
                        isUnique = false;
                    }
                }
                if (isUnique) {
                    // The point doesn't exist in the list yet
                    list.add(point);
                }
            }
        }
        return list;
    }
}