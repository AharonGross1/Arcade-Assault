// 209345180 Aharon Gross

/**
 * a BallRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructor for the class.
     *
     * @param gameLevel           - The game the listener is in.
     * @param remainingBalls - the number of balls that are in the game.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.game = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Implement method in HitListener.
     * Removes the ball from the game.
     *
     * @param beingHit - The object that was hit.
     * @param hitter   - Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.decrease(1);
        this.game.setRemainingBalls(this.remainingBalls);
        hitter.removeFromGame(game);
    }
}