// 209345180 Aharon Gross

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * This class is the logic behind running a level.
 */
public class GameLevel implements Animation {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int FRAME_BLOCK_SIZE = 20;
    public static final int PADDLE_HEIGHT = 20;
    private Counter score;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private Boolean lastLevel;

    /**
     * Constructor for the class.
     *
     * @param levelInformation - The level to run.
     * @param ks               - The gui's keyboard.
     * @param animationRunner  - The animation runner to run the level.
     * @param gui              - The gui.
     * @param score            - The current game's score.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor ks,
                     AnimationRunner animationRunner, GUI gui, Counter score) {
        this.gui = gui;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.score = score;
        this.runner = animationRunner;
        this.keyboard = ks;
        this.levelInformation = levelInformation;
        this.lastLevel = false;
        this.sprites.addSprite(this.levelInformation.getBackground());
    }

    /**
     * getter for the game's screen width.
     *
     * @return the screen's width.
     */
    public int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    /**
     * getter for the game's gui.
     *
     * @return the screen's gui.
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * getter for the game's screen height.
     *
     * @return the screen's height.
     */
    public int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    /**
     * getter for the game's paddle width.
     *
     * @return the paddle's width.
     */
    public int getPaddleWidth() {
        return this.levelInformation.paddleWidth();
    }

    /**
     * getter for the game's paddle height.
     *
     * @return the paddle's height.
     */
    public int getPaddleHeight() {
        return PADDLE_HEIGHT;
    }

    /**
     * Getter for the game's frame block's size.
     *
     * @return the frame block's size.
     */
    public int getFrameBlockSize() {
        return FRAME_BLOCK_SIZE;
    }


    /**
     * Getter for the game's remaining blocks counter.
     *
     * @return the game's remaining blocks counter.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * Setter for the game's RemainingBlocks counter.
     *
     * @param counter - The new counter.
     */
    public void setRemainingBlocks(Counter counter) {
        this.remainingBlocks = counter;
    }


    /**
     * Setter for the game's LastLevel param.
     *
     * @param lastLevel - is this the last level.
     */
    public void setLastLevel(Boolean lastLevel) {
        this.lastLevel = lastLevel;
    }

    /**
     * Getter for the game's remaining balls counter.
     *
     * @return the game's remaining balls counter.
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    /**
     * Setter for the game's RemainingBalls counter.
     *
     * @param counter - the new counter.
     */
    public void setRemainingBalls(Counter counter) {
        this.remainingBalls = counter;
    }

    /**
     * Getter for the game's score counter.
     *
     * @return the game's score counter.
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * Setter for the game's score counter.
     *
     * @param score - The new counter.
     */
    public void setScore(Counter score) {
        this.score = score;
    }

    /**
     * Add a collidable to the game's game environment.
     *
     * @param c - The collidable to add to the game.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add a sprite to the game's sprites.
     *
     * @param s - The sprite to add to the game.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Removes a collidable from the game environment.
     *
     * @param c - The collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Removes a sprite from the game environment.
     *
     * @param s - The sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Create an array of blocks to surround the screen with.
     *
     * @return an array of blocks.
     */
    public Block[] createFrameBlocks() {
        Block[] blocks = new Block[5];
        // Create the blocks
        blocks[0] = new Block(new Rectangle(new Point(0, 20),
                SCREEN_WIDTH - FRAME_BLOCK_SIZE, FRAME_BLOCK_SIZE));
        blocks[1] = new Block(new Rectangle(new Point(0, FRAME_BLOCK_SIZE),
                FRAME_BLOCK_SIZE + 5, SCREEN_HEIGHT - FRAME_BLOCK_SIZE));
        blocks[2] = new Block(new Rectangle(new Point(0, SCREEN_HEIGHT - 1), SCREEN_WIDTH, FRAME_BLOCK_SIZE));
        blocks[3] = new Block(new Rectangle(new Point(SCREEN_WIDTH - FRAME_BLOCK_SIZE - 5, 20),
                FRAME_BLOCK_SIZE + 5, SCREEN_HEIGHT - 21));
        blocks[4] = new Block(new Rectangle(new Point(0, 0), SCREEN_WIDTH, 25));


        return blocks;
    }

    /**
     * Create blocks to surround the screen with.
     *
     * @param gameEnvironment - The game environment to add the blocks to.
     * @param blocks          - The blocks to surround the screen with.
     */
    public void initializeFrameBlocks(GameEnvironment gameEnvironment, Block[] blocks) {
        // Add the frame blocks to the game
        for (Block b : blocks) {
            b.getCollisionRectangle().getFrame().setColor(Color.GRAY);
            gameEnvironment.addCollidable(b);
            b.addToGame(this);
        }

        blocks[blocks.length - 1].getCollisionRectangle().getFrame().setColor(Color.LIGHT_GRAY);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score, this);
        Frame frame = new Frame(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this.levelInformation.backgroundColor());
        Paddle paddle = new Paddle(new Point((int) (SCREEN_WIDTH / 2 - (this.levelInformation.paddleWidth() / 2)), 570),
                this.levelInformation.paddleWidth(), PADDLE_HEIGHT, this);
        paddle.setPaddleSpeed(this.levelInformation.paddleSpeed());
        paddle.addToGame(this);
        // Create the balls
        createBalls(this.levelInformation.numberOfBalls(), frame);
        // Create the frame blocks
        Block[] frameBlocks = this.createFrameBlocks();
        this.initializeFrameBlocks(this.environment, frameBlocks);
        frameBlocks[2].addHitListener(ballRemover);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this, this.levelInformation.levelName());
        scoreIndicator.addToGame(this);
        for (Block b : this.levelInformation.blocks()) {
            b.addHitListener(scoreTrackingListener);
            b.addHitListener(blockRemover);
            b.addToGame(this);
        }
        this.remainingBlocks.increase(this.levelInformation.numberOfBlocksToRemove());
        this.remainingBalls.increase(this.levelInformation.numberOfBalls());
    }

    /**
     * A method for creating a given number of balls.
     *
     * @param amount - The number of balls to create.
     * @param frame  - The frame of the balls.
     */
    public void createBalls(int amount, Frame frame) {
        int i = 0;

        while (amount > 0) {
            Ball ball = new Ball(SCREEN_WIDTH / 2, 565, 5, Color.LIGHT_GRAY);
            ball.setFrame(frame);
            ball.addToGame(this);
            ball.setGameEnvironment(this.environment);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            amount--;
            i++;
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.remainingBlocks.getValue() < 1) {
            this.running = false;
        }

        Frame frame = new Frame(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this.levelInformation.backgroundColor());
        frame.animationLoop(d);
        this.sprites.drawAllOn(d);
        d.setColor(Color.blue);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "Pause", this.score.getValue()));
            this.runner.run(new CountDownAnimation(2, 3, this.sprites, this.levelInformation.backgroundColor()));
        }
        if (this.remainingBalls.getValue() < 1) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "Game Over!", this.score.getValue()));
            if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                this.runner.setPlay(false);
            }
        }
        if (this.lastLevel && this.remainingBlocks.getValue() == 0) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "You Win!", this.score.getValue() + 100));
            if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                this.runner.setPlay(false);
            }
        }
    }


    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * The method used to run the level.
     */
    public void run() {
        // countdown before turn starts.
        this.runner.run(new CountDownAnimation(2, 3, this.sprites, this.levelInformation.backgroundColor()));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

}