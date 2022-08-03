// 209345180 Aharon Gross

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * This class represents a frame that can be drawn on the screen.
 * The class has constructors, getters and various other methods.
 *
 * @author Aharon Gross
 */
public class Frame {
    private Point minBorder;
    private Point maxBorder;
    private Color color;

    /**
     * This method is a constructor for the class.
     * The builder makes sure the parameters are set correctly.
     *
     * @param minBorder - A point representing the minimum border of the frame.
     * @param maxBorder - A point representing the maximum border of the frame.
     * @param color     - Thr frame's color.
     */
    public Frame(Point minBorder, Point maxBorder, Color color) {
        this.minBorder = new Point(Math.min(minBorder.getX(), maxBorder.getX()),
                Math.min(minBorder.getY(), maxBorder.getY()));
        this.maxBorder = new Point(Math.max(minBorder.getX(), maxBorder.getX()),
                Math.max(minBorder.getY(), maxBorder.getY()));
        this.color = color; // Set default color
    }

    /**
     * This method is a constructor for the class.
     * The builder makes sure the parameters are set correctly.
     *
     * @param minX  - X coordinate of the minimum border of the frame.
     * @param minY  - Y coordinate of the minimum border of the frame.
     * @param maxX  - X coordinate of the maximum border of the frame.
     * @param maxY  - Y coordinate of the maximum border of the frame.
     * @param color - Thr frame's color.
     */
    public Frame(int minX, int minY, int maxX, int maxY, Color color) {
        this.minBorder = new Point(Math.min(minX, maxX), Math.min(minY, maxY));
        this.maxBorder = new Point(Math.max(minX, maxX), Math.max(minY, maxY));
        this.color = color; // Set default color
    }

    /**
     * This method is a getter for the frame's minimum border point.
     *
     * @return the frame's minimum border point.
     */
    public Point getMinBorder() {
        return minBorder;
    }

    /**
     * This method is a setter for the frame's minimum border point.
     *
     * @param minBorder - the new minimum border point.
     */
    public void setMinBorder(Point minBorder) {
        this.minBorder = minBorder;
    }

    /**
     * This method is a getter for the frame's maximum border point.
     *
     * @return the frame's maximum border point.
     */
    public Point getMaxBorder() {
        return maxBorder;
    }

    /**
     * This method is a setter for the frame's maximum border point.
     *
     * @param maxBorder - the new maximum border point.
     */
    public void setMaxBorder(Point maxBorder) {
        this.maxBorder = maxBorder;
    }

    /**
     * This method is a getter for the frame's color.
     *
     * @return the frame's color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * This method is a setter for the frame's color.
     *
     * @param color - the new color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * This method calculates the width of the frame.
     *
     * @return the total height of the frame.
     */
    public int frameWidth() {
        return (int) (maxBorder.getX() - minBorder.getX());
    }

    /**
     * This method calculates the height of the frame.
     *
     * @return the total height of the frame.
     */
    public int frameHeight() {
        return (int) (maxBorder.getY() - minBorder.getY());
    }

    /**
     * Getter for the frame's top right point.
     *
     * @return the frame's top right point.
     */
    public Point topRightPoint() {
        return new Point(this.getMaxBorder().getX(), this.getMinBorder().getY());
    }

    /**
     * Getter for the frame's bottom left point.
     *
     * @return the frame's bottom left point.
     */
    public Point bottomLeftPoint() {
        return new Point(this.getMinBorder().getX(), this.getMaxBorder().getY());
    }

    /**
     * This method draws the frame on a given surface.
     *
     * @param d - The surface to draw the frame on.
     */
    public void animationLoop(DrawSurface d) {
        d.setColor(this.getColor());
        d.drawRectangle((int) (this.getMinBorder().getX()), (int) (this.getMinBorder().getY()),
                this.frameWidth(), this.frameHeight());
        d.fillRectangle((int) (this.getMinBorder().getX()), (int) (this.getMinBorder().getY()),
                this.frameWidth(), this.frameHeight());
    }

    /**
     * Getter for the frame's top line.
     *
     * @return the frame's top line.
     */
    public Line topOfFrameLine() {
        return new Line(this.getMinBorder(), this.topRightPoint());
    }

    /**
     * Getter for the frame's bottom line.
     *
     * @return the frame's bottom line.
     */
    public Line bottomOfFrameLine() {
        return new Line(this.getMaxBorder(), this.bottomLeftPoint());
    }

    /**
     * Getter for the frame's right side line.
     *
     * @return the frame's right side line.
     */
    public Line rightSideOfFrameLine() {
        return new Line(this.topRightPoint(), this.getMaxBorder());
    }

    /**
     * Getter for the frame's left side line.
     *
     * @return the frame's left side line.
     */
    public Line leftSideOfFrameLine() {
        return new Line(this.bottomLeftPoint(), this.getMinBorder());
    }

    /**
     * Draw the outlines of a frame on a given draw surface with a given color.
     *
     * @param d     - The surface to draw on.
     * @param color - The color of the outline.
     */
    public void drawOutline(DrawSurface d, Color color) {
        // Array of the frame's outline lines
        Line[] lArr = new Line[4];
        lArr[0] = this.leftSideOfFrameLine();
        lArr[1] = this.topOfFrameLine();
        lArr[2] = this.rightSideOfFrameLine();
        lArr[3] = this.bottomOfFrameLine();

        // Draw each line
        for (Line l : lArr) {
            d.setColor(color);
            d.drawLine((int) l.start().getX(), (int) l.start().getY(), (int) l.end().getX(), (int) l.end().getY());
        }
    }

}
