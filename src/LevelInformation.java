// 209345180 Aharon Gross

import java.awt.Color;
import java.util.List;

/**
 * An interface to represent a level in the game.
 */
public interface LevelInformation {
    /**
     * The number of balls in the current level.
     *
     * @return - the number of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return a list with the ball's velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * The paddle's speed.
     *
     * @return the paddle's speed.
     */
    int paddleSpeed();

    /**
     * The paddle's width.
     *
     * @return the paddle's width.
     */
    int paddleWidth();

    /**
     * The level's name.
     *
     * @return a string with the level's name.
     */
    String levelName();

    /**
     * The level's special background.
     *
     * @return a sprite of the background.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return a list with the level's blocks.
     */

    List<Block> blocks();

    /**
     * The number of blocks in the level.
     *
     * @return the number of blocks in the level.
     */
    int numberOfBlocksToRemove();

    /**
     * Getter for the level's background color.
     *
     * @return the level's background color.
     */
    Color backgroundColor();
}