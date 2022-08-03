// 209345180 Aharon Gross

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructor for the class.
     *
     * @param gameLevel     - The game the listener is in.
     * @param removedBlocks - the number of blocks that are in the game.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Implement method in HitListener.
     * Removes the block from the game.
     *
     * @param beingHit - The object that was hit.
     * @param hitter   - Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBlocks.decrease(1);
        this.gameLevel.setRemainingBlocks(this.remainingBlocks);
        beingHit.removeFromGame(gameLevel);
    }
}