// 209345180 Aharon Gross

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * This class represents a level of the game.
 */
public class FinalFour implements LevelInformation {
    public static final int SCREEN_WIDTH = 800;
    public static final int FRAME_BLOCK_SIZE = 25;
    public static final int GAME_BLOCK_WIDTH = 50;
    public static final int GAME_BLOCK_HEIGHT = 25;
    private Color[] colorArr;

    /**
     * Constructor for the class.
     */
    public FinalFour() {
        // Create the color array
        this.colorArr = new Color[7];
        this.colorArr[0] = Color.GRAY;
        this.colorArr[1] = Color.RED;
        this.colorArr[2] = Color.yellow;
        this.colorArr[3] = Color.green;
        this.colorArr[4] = Color.white;
        this.colorArr[5] = Color.pink;
        this.colorArr[6] = Color.cyan;
    }

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(new Velocity(1, -4));
        list.add(new Velocity(-1, -4));
        list.add(new Velocity(0, -4));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return ("Final Four");
    }

    @Override
    public Sprite getBackground() {
        return new FinalFourBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                Block b = new Block(new Rectangle(new Point(SCREEN_WIDTH - FRAME_BLOCK_SIZE
                        - (j + 1) * GAME_BLOCK_WIDTH,
                        120 + i * GAME_BLOCK_HEIGHT), GAME_BLOCK_WIDTH, GAME_BLOCK_HEIGHT));
                b.getCollisionRectangle().getFrame().setColor(this.colorArr[i]);
                list.add(b);
            }
        }
        return list;
    }


    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }

    @Override
    public Color backgroundColor() {
        return new Color(0, 128, 255);
    }
}