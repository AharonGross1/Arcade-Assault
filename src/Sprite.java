// 209345180 Aharon Gross

import biuoop.DrawSurface;

/**
 * A sprite interface.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d - The draw surface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed so that they can take their next step.
     */
    void timePassed();
}