// 209345180 Aharon Gross

/**
 * a ScoreTrackingListener is in charge of updating the score according
 * to the number of blocks removed.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private GameLevel gameLevel;

    /**
     * Class constructor.
     *
     * @param scoreCounter - A new counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
        this.gameLevel = null;
    }

    /**
     * Class constructor.
     *
     * @param scoreCounter - A new counter.
     * @param gameLevel         - The game the counter is in.
     */
    public ScoreTrackingListener(Counter scoreCounter, GameLevel gameLevel) {
        this.currentScore = scoreCounter;
        this.gameLevel = gameLevel;
    }

    /**
     * Implements method in HitListener.
     * Updates the score whenever a block is hit.
     *
     * @param beingHit - The object that was hit.
     * @param hitter   - Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
        this.gameLevel.setScore(this.currentScore);
    }
}