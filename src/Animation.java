// 209345180 Aharon Gross

import biuoop.DrawSurface;

/**
 * An interface of an animation.
 */
public interface Animation {
    /**
     * The logic of one frame - drawing and moving objects.
     *
     * @param d - The drawSurface to carry this out on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Returns true or false, depending on whether the animation should still run or not.
     *
     * @return true if the animation should stop, false otherwise.
     */
    boolean shouldStop();
}