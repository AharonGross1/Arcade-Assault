// 209345180 Aharon Gross

/**
 * This class represents a velocity.
 * The class has constructors, getters and various other methods.
 *
 * @author Aharon Gross
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * A constructor for the class.
     *
     * @param dx - change in position on the 'x' axis.
     * @param dy - change in position on the `y` axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This method creates a velocity given an angle and speed.
     *
     * @param angle - the angle of the movement.
     * @param speed - the speed of the movement;
     * @return the velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -(speed * Math.cos(Math.toRadians(angle)));
        return new Velocity(dx, dy);
    }

    /**
     * This method is a getter for the velocity's dx.
     *
     * @return the velocity's dx.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * This method is a setter for the velocity's dx.
     *
     * @param dx - the new dx.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * This method is a getter for the velocity's dx.
     *
     * @return the velocity's dx.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * This method is a setter for the velocity's dy.
     *
     * @param dy - the new dy.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Getter for the velocity's speed.
     *
     * @return the velocity's speed.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
    }

    /**
     * This method Takes a point with position (x,y) and returns a new point
     * with position (x+dx, y+dy).
     *
     * @param p - the point that needs a change in position.
     * @return the new point.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}