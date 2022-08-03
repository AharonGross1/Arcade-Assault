// 209345180 Aharon Gross

import biuoop.DrawSurface;

/**
 * This interface represents collidable objects in the game.
 */
public interface Collidable {
    /**
     * Get the "collision shape" of the object.
     *
     * @return the collidable's rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param hitter          - The ball that hit the collidable.
     * @param collisionPoint  - The point of collision.
     * @param currentVelocity - The velocity when the hit occurred.
     * @return the new velocity after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Draw the collidable on a given surface.
     *
     * @param surface - The surface to draw on.
     */
    void drawOn(DrawSurface surface);
}