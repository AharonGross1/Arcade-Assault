// 209345180 Aharon Gross

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * This class represents a ball.
 * The class has constructors, getters and various other methods.
 *
 * @author Aharon Gross
 */
public class Ball implements Sprite {
    private int radius;
    private Color color;
    private Point center;
    private Velocity velocity;
    private Frame frame;
    private GameEnvironment gameEnvironment;

    /**
     * This method is a constructor for the class.
     *
     * @param center - a point representing the center of the circle.
     * @param r      - the ball's radius.
     * @param color  - the ball's color.
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = Math.abs(r);
        this.color = color;
        // Default velocity
        this.speedBySize();
    }

    /**
     * This method is a constructor for the class.
     *
     * @param x     - x coordinate for the circle's center.
     * @param y     - y coordinate for the circle's center.
     * @param r     - the ball's radius.
     * @param color - the ball's color.
     */
    public Ball(int x, int y, int r, Color color) {
        this.center = new Point(x, y);
        this.radius = Math.abs(r);
        this.color = color;
        // Default velocity
        this.speedBySize();
    }

    /**
     * This method is a getter for the ball's center x coordinate.
     *
     * @return the ball's center x coordinate.
     */
    public int getX() {
        return ((int) this.center.getX());
    }

    /**
     * This method is a getter for the ball's center y coordinate.
     *
     * @return the ball's center y coordinate.
     */
    public int getY() {
        return ((int) this.center.getY());
    }

    /**
     * This method is a getter for the ball's radius.
     *
     * @return the ball's radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * This method is a getter for the ball's GameEnvironment.
     *
     * @return the ball's GameEnvironment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * This method is a setter for the ball's GameEnvironment.
     *
     * @param gameEnvironment - the new GameEnvironment;
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * This method is a getter for the ball's color.
     *
     * @return the ball's color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * This method is a setter for the ball's color.
     *
     * @param color - the ball's new color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * This method is a getter for the ball's velocity.
     *
     * @return the ball's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * This method is a setter for the ball's velocity with a given velocity.
     *
     * @param v - the velocity to set as the ball's velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * This method is a getter for the ball's frame.
     *
     * @return the ball's frame.
     */
    public Frame getFrame() {
        return this.frame;
    }

    /**
     * This method is a setter for the ball's top left screen border.
     *
     * @param frame - the frame the ball will be drawn on;
     */
    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    /**
     * This method is a setter for the ball's velocity with given dx, dy speed.
     *
     * @param dx - change in position on the 'x' axis.
     * @param dy - change in position on the `y` axis.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * This method draws the ball on a given DrawSurface.
     *
     * @param surface the DrawSurface to draw the ball on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.white);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * Removes the ball from a given game.
     * @param g - The game to remove the ball from.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * Implementing method in Sprite.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
        this.stuckInObstacle();
        this.moveOutOfCorner();
    }

    /**
     * This method checks if the ball is in a corner between 3 collidables and changes the velocity if it is.
     */
    public void moveOutOfCorner() {
        List<Collidable> list = new ArrayList<>();
        // Find all collidables the ball collided with
        for (Collidable c : gameEnvironment.getCollidableList()) {
            if (this.center.equals(c.getCollisionRectangle().getFrame().topRightPoint())
                    || this.center.equals(c.getCollisionRectangle().getFrame().getMaxBorder())
                    || this.center.equals(c.getCollisionRectangle().getFrame().getMinBorder())
                    || this.center.equals(c.getCollisionRectangle().getFrame().bottomLeftPoint())) {
                list.add(c);
            }
        }
        // Ball is in a corner
        if (list.size() > 2) {
            this.velocity = new Velocity(-this.velocity.getDx(), -this.velocity.getDy());
            this.velocity.applyToPoint(this.center);
        }
    }

    /**
     * If the ball enters a collidable, this method pops the ball out.
     */
    public void stuckInObstacle() {
        for (Collidable c : gameEnvironment.getCollidableList()) {
            this.center.pushOutOfFrame(c.getCollisionRectangle().getFrame());
            this.keepInBoundaries();
        }
    }

    /**
     * This method moves the ball one frame around the screen.
     */
    public void moveOneStep() {
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(this.trajectory());
        if (collisionInfo.collisionPoint() != null) {
            Point collisionPoint = collisionInfo.collisionPoint();
            this.setVelocity(collisionInfo.collisionObject().hit(this, collisionPoint, this.getVelocity()));
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }


    /**
     * This method makes sure the ball never exists its frame.
     */
    public void keepInBoundaries() {
        // Ball is outside x borders
        if (GeneralCalcs.isBigger((this.center.getX()), this.frame.getMaxBorder().getX() - 25)) {
            this.center = new Point(this.frame.getMaxBorder().getX() - 27, this.center.getY());
            this.velocity.setDx(-this.velocity.getDx());
        } else if (GeneralCalcs.isBigger(this.frame.getMinBorder().getX() + 25, this.center.getX())) {
            this.center = new Point(this.frame.getMinBorder().getX() + 27, this.center.getY());
            this.velocity.setDx(-this.velocity.getDx());
        }

        // Ball is outside y borders
        if (GeneralCalcs.isBigger(this.frame.getMinBorder().getY(), (this.center.getY() - 20))) {
            this.center = new Point(this.center.getX(), this.frame.getMinBorder().getY() + 60);
            this.velocity.setDy(-this.velocity.getDy());
        }
    }


    /**
     * Set the speed of the ball depending on it's size.
     */
    public void speedBySize() {
        if (this.getSize() >= 50) {
            this.setVelocity(1, 1);
        } else {
            double speed = Math.log(80 - this.getSize());
            this.setVelocity(speed, speed);
        }
    }

    /**
     * @return the ball's next step - the trajectory.
     */
    public Line trajectory() {
        return new Line(this.center, this.velocity.applyToPoint(this.center));
    }

    /**
     * Add the ball to a given game by adding it to the object lists of the interface it implements.
     *
     * @param g - The game to add to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}