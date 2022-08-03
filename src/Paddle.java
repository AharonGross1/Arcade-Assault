// 209345180 Aharon Gross

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * This class represents a paddle, which is also a Sprite and a Collidable.
 */
public class Paddle implements Sprite, Collidable {
    private int paddleSpeed;
    private Rectangle rect;
    private GameLevel gameLevel;

    /**
     * Constructor for the class.
     *
     * @param p         - The paddle's top left point.
     * @param width     - The paddle's width.
     * @param height    - The paddle's height.
     * @param gameLevel - The game the paddle is in.
     */
    public Paddle(Point p, int width, int height, GameLevel gameLevel) {
        this.rect = new Rectangle(p, width, height);
        this.gameLevel = gameLevel;
        this.paddleSpeed = 10;
    }

    /**
     * Setter for the paddle's speed.
     *
     * @param paddleSpeed - The new speed.
     */
    public void setPaddleSpeed(int paddleSpeed) {
        this.paddleSpeed = paddleSpeed;
    }

    /**
     * Implement method in Collidable.
     *
     * @return The paddle's rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Move the paddle to the left.
     */
    public void moveLeft() {
        // Paddle reached the left side of the screen
        if (GeneralCalcs.isBigger(this.gameLevel.getFrameBlockSize(), this.rect.getFrame().getMinBorder().getX())) {
            return;
        }
        this.rect = new Rectangle(new Point(this.rect.getUpperLeft().getX() - paddleSpeed,
                this.rect.getUpperLeft().getY()), this.rect.getWidth(), this.rect.getHeight());

    }

    /**
     * Move the paddle to the right.
     */
    public void moveRight() {
        // Paddle reached the right side of the screen
        if (GeneralCalcs.isBigger(this.rect.getFrame().getMaxBorder().getX(),
                this.gameLevel.getScreenWidth() - this.gameLevel.getFrameBlockSize())) {
            return;
        }
        this.rect = new Rectangle(new Point(this.rect.getUpperLeft().getX() + paddleSpeed,
                this.rect.getUpperLeft().getY()), this.rect.getWidth(), this.rect.getHeight());
    }

    /**
     * Implement method in Collidable and Sprite.
     *
     * @param d - The draw surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.orange);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) (this.rect.getWidth()), (int) (this.rect.getHeight()));
        d.setColor(Color.BLACK);
        this.rect.getFrame().drawOutline(d, Color.BLACK);
    }

    /**
     * Implement method in Sprite.
     */
    @Override
    public void timePassed() {
        biuoop.KeyboardSensor keyboard = this.gameLevel.getGui().getKeyboardSensor();
        // Move the paddle according to user input
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            moveRight();
        }

        //  Keep paddle in the game frame
        if (GeneralCalcs.isBigger(this.gameLevel.getFrameBlockSize() + 5, this.rect.getFrame().getMinBorder().getX())) {
            this.rect = new Rectangle(new Point(this.gameLevel.getFrameBlockSize() + 5,
                    this.rect.getUpperLeft().getY()), this.rect.getWidth(), this.rect.getHeight());
        }

        if (GeneralCalcs.isBigger(this.rect.getUpperLeft().getX() + this.rect.getWidth(),
                gameLevel.getScreenWidth() - this.gameLevel.getFrameBlockSize() - 5)) {
            this.rect = new Rectangle(new Point(this.gameLevel.getScreenWidth() - this.gameLevel.getPaddleWidth()
                    - this.gameLevel.getFrameBlockSize() - 5,
                    this.rect.getUpperLeft().getY()), this.rect.getWidth(), this.rect.getHeight());
        }
    }

    /**
     * Change the velocity of the object that hit the paddle.
     *
     * @param collisionPoint  - The point of collision.
     * @param currentVelocity - The velocity when the hit occurred.
     * @return the new velocity after the collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint != null) {
            // Split the paddle into 5 regions
            int fifthOfPaddle = this.gameLevel.getPaddleWidth() / 5;
            Rectangle[] regions = new Rectangle[5];
            regions[0] = new Rectangle(this.rect.getUpperLeft(), fifthOfPaddle, this.rect.getHeight());
            regions[1] = new Rectangle(regions[0].getFrame().topRightPoint(), fifthOfPaddle, this.rect.getHeight());
            regions[2] = new Rectangle(regions[1].getFrame().topRightPoint(), fifthOfPaddle, this.rect.getHeight());
            regions[3] = new Rectangle(regions[2].getFrame().topRightPoint(), fifthOfPaddle, this.rect.getHeight());
            regions[4] = new Rectangle(regions[3].getFrame().topRightPoint(), fifthOfPaddle, this.rect.getHeight());

            // Change the velocity according to the region the object hit
            for (int i = 0; i <= 4; i++) {
                if (regions[i].getFrame().topOfFrameLine().pointOnLine(collisionPoint)) {
                    if (i == 2) {
                        currentVelocity.setDy(-currentVelocity.getDy());
                    } else {
                        currentVelocity = Velocity.fromAngleAndSpeed(getAngle(i), currentVelocity.getSpeed());
                    }
                    return currentVelocity;
                }
                if (regions[i].getFrame().rightSideOfFrameLine().pointOnLine(collisionPoint)
                        || regions[i].getFrame().leftSideOfFrameLine().pointOnLine(collisionPoint)) {
                    if (!regions[i].getFrame().bottomLeftPoint().equals(collisionPoint)
                            && !regions[i].getFrame().getMinBorder().equals(collisionPoint)) {
                        currentVelocity.setDx(-currentVelocity.getDx());
                    }
                }
            }
        }
        return currentVelocity;
    }

    /**
     * This method returns an angle depending on the paddle region.
     *
     * @param region - The paddle region.
     * @return an angle (int)
     */
    public int getAngle(int region) {
        if (region == 0) {
            return 300;
        } else if (region == 1) {
            return 330;
        } else if (region == 2) {
            return 360;
        } else if (region == 3) {
            return 30;
        } else if (region == 4) {
            return 60;
        }
        return 0;
    }

    /**
     * Add the paddle to a given game by adding it to the object lists of the interface it implements.
     *
     * @param g - The game to add to.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}