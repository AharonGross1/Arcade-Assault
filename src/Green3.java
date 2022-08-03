// 209345180 Aharon Gross
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * This class represents a level of the game.
 */
public class Green3 implements LevelInformation {
    public static final int SCREEN_WIDTH = 800;
    public static final int FRAME_BLOCK_SIZE = 25;
    public static final int GAME_BLOCK_WIDTH = 50;
    public static final int GAME_BLOCK_HEIGHT = 25;
    private Color[] colorArr;

    /**
     * Constructor for the class.
     */
    public Green3() {
        // Create the color array
        this.colorArr = new Color[5];
        this.colorArr[0] = Color.GRAY;
        this.colorArr[1] = Color.RED;
        this.colorArr[2] = Color.YELLOW;
        this.colorArr[3] = Color.BLUE;
        this.colorArr[4] = Color.white;
    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(new Velocity(4, -4));
        list.add(new Velocity(-4, -4));
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
        return ("Green 3");
    }

    @Override
    public Sprite getBackground() {
        return new Green3Background();
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10 - i; j++) {
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
        return 40;
    }

    @Override
    public Color backgroundColor() {
        return new Color(0, 110, 0);
    }
}
