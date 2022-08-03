// 209345180 Aharon Gross

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a block in the animation.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private List<HitListener> hitListeners;

    /**
     * Constructor for the class.
     *
     * @param rect - The block's rectangle.
     */
    public Block(Rectangle rect) {
        this.rect = rect;
        hitListeners = new ArrayList<>();
    }

    /**
     * Implement method in Collidable.
     *
     * @return this block's rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * This method draws the block on a given DrawSurface.
     *
     * @param surface - the DrawSurface to draw the ball on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.rect.getFrame().getColor());
        surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) (this.rect.getWidth()),
                (int) (this.rect.getHeight()));
        surface.setColor(Color.BLACK);
        this.rect.getFrame().drawOutline(surface, Color.BLACK);
    }

    /**
     * Implement method in Sprite.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Implement method in Collidable.
     *
     * @param hitter          - The ball that hit the block.
     * @param collisionPoint  - The point of collision.
     * @param currentVelocity - The velocity when the hit occurred.
     * @return the new velocity after the hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);

        // Check if a vertex was hit
        Velocity v = this.hitVertex(collisionPoint, currentVelocity);
        if (v != null) {
            this.notifyHit(hitter);
            return v;
        }

        // if there is a collision, set the new velocity depending on where the collision occurred
        if (collisionPoint != null) {
            // Collision on x-axis - change dy
            if (this.rect.getFrame().bottomOfFrameLine().pointOnLine(collisionPoint)
                    || this.rect.getFrame().topOfFrameLine().pointOnLine(collisionPoint)) {
                currentVelocity.setDy(-currentVelocity.getDy());
            }
            // Collision on y-axis - change dx
            if (this.rect.getFrame().rightSideOfFrameLine().pointOnLine(collisionPoint)
                    || this.rect.getFrame().leftSideOfFrameLine().pointOnLine(collisionPoint)) {
                currentVelocity.setDx(-currentVelocity.getDx());
            }

        }
        return currentVelocity;
    }

    /**
     * This method breaks down the options for a collision with a vertex of a block.
     * It then sets the velocity according to the collision vertex.
     *
     * @param collisionPoint  - The point of collision.
     * @param currentVelocity - The velocity when the hit occurred.
     * @return the new velocity after the hit.
     */
    public Velocity hitVertex(Point collisionPoint, Velocity currentVelocity) {
        // Collision with top left vertex
        if (this.rect.getFrame().getMinBorder().equals(collisionPoint)) {
            if (!GeneralCalcs.isBigger(currentVelocity.getDx(), 0)
                    && GeneralCalcs.isBigger(currentVelocity.getDy(), 0)) {
                currentVelocity.setDy(-currentVelocity.getDy());
            } else if (GeneralCalcs.isBigger(currentVelocity.getDx(), 0)
                    && !GeneralCalcs.isBigger(currentVelocity.getDy(), 0)) {
                currentVelocity.setDx(-currentVelocity.getDx());
            } else if (GeneralCalcs.isBigger(currentVelocity.getDx(), 0)
                    && GeneralCalcs.isBigger(currentVelocity.getDy(), 0)) {
                currentVelocity.setDy(-currentVelocity.getDy());
                currentVelocity.setDx(-currentVelocity.getDx());
            } // Collision with bottom right vertex
        } else if (this.rect.getFrame().getMaxBorder().equals(collisionPoint)) {
            if (!GeneralCalcs.isBigger(0, currentVelocity.getDx())
                    && GeneralCalcs.isBigger(0, currentVelocity.getDy())) {
                currentVelocity.setDy(-currentVelocity.getDy());
            } else if (GeneralCalcs.isBigger(0, currentVelocity.getDx())
                    && !GeneralCalcs.isBigger(0, currentVelocity.getDy())) {
                currentVelocity.setDx(-currentVelocity.getDx());
            } else if (GeneralCalcs.isBigger(0, currentVelocity.getDx())
                    && GeneralCalcs.isBigger(0, currentVelocity.getDy())) {
                currentVelocity.setDy(-currentVelocity.getDy());
                currentVelocity.setDx(-currentVelocity.getDx());
            } // Collision with bottom left vertex
        } else if (this.rect.getFrame().bottomLeftPoint().equals(collisionPoint)) {
            if (!GeneralCalcs.isBigger(currentVelocity.getDx(), 0)
                    && GeneralCalcs.isBigger(0, currentVelocity.getDy())) {
                currentVelocity.setDy(-currentVelocity.getDy());
            } else if (GeneralCalcs.isBigger(currentVelocity.getDx(),
                    0) && !GeneralCalcs.isBigger(0, currentVelocity.getDy())) {
                currentVelocity.setDx(-currentVelocity.getDx());
            } else if (GeneralCalcs.isBigger(currentVelocity.getDx(), 0)
                    && GeneralCalcs.isBigger(0, currentVelocity.getDy())) {
                currentVelocity.setDy(-currentVelocity.getDy());
                currentVelocity.setDx(-currentVelocity.getDx());
            } // Collision with top right vertex
        } else if (this.rect.getFrame().topRightPoint().equals(collisionPoint)) {
            if (!GeneralCalcs.isBigger(0, currentVelocity.getDx())
                    && GeneralCalcs.isBigger(currentVelocity.getDy(), 0)) {
                currentVelocity.setDy(-currentVelocity.getDy());
            } else if (GeneralCalcs.isBigger(0, currentVelocity.getDx())
                    && !GeneralCalcs.isBigger(currentVelocity.getDy(), 0)) {
                currentVelocity.setDx(-currentVelocity.getDx());
            } else if (GeneralCalcs.isBigger(0, currentVelocity.getDx())
                    && GeneralCalcs.isBigger(currentVelocity.getDy(), 0)) {
                currentVelocity.setDy(-currentVelocity.getDy());
                currentVelocity.setDx(-currentVelocity.getDx());
            }
        } else {
            return null;
        }
        return currentVelocity;
    }

    /**
     * Add the block to a given game by adding it to the object lists of the interface it implements.
     *
     * @param g - The game to add to.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * remove the block from a given game.
     *
     * @param gameLevel - The game to remove the block from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Implements method in HitNotifier.
     *
     * @param hl - The listener to add.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Implements method in HitNotifier.
     *
     * @param hl - The listener to remove.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies all the hit listeners that a hit occurred.
     *
     * @param hitter - The ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = this.hitListeners;
        // Notify all listeners about a hit event:
        if (listeners.size() > 0) {
            for (int i = 0; i < listeners.size(); i++) {
                listeners.get(i).hitEvent(this, hitter);
            }
        }
    }
}
