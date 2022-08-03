// 209345180 Aharon Gross

/**
 * An interface that represents objects that are notified when a hit event occurs.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit - The object that was hit.
     * @param hitter   - Ball that's doing the hitting.
     */

    void hitEvent(Block beingHit, Ball hitter);
}