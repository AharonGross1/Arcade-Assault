// 209345180 Aharon Gross
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a level of the game.
 */
public class WideEasy implements LevelInformation {

    public static final int SCREEN_WIDTH = 800;
    public static final int FRAME_BLOCK_SIZE = 25;
    public static final int GAME_BLOCK_WIDTH = 50;
    public static final int GAME_BLOCK_HEIGHT = 25;

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Velocity(i - 6, -6));
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return new String("Wide Easy");
    }

    @Override
    public Sprite getBackground() {
        return new WideEasyBackground();
    }

    @Override
    public List<Block> blocks() {
        Color[] colors = new Color[15];
        colors[0] = Color.red;
        colors[1] = Color.red;
        colors[2] = Color.orange;
        colors[3] = Color.orange;
        colors[4] = Color.yellow;
        colors[5] = Color.yellow;
        colors[6] = Color.green;
        colors[7] = Color.green;
        colors[8] = Color.green;
        colors[9] = Color.blue;
        colors[10] = Color.blue;
        colors[11] = Color.pink;
        colors[12] = Color.pink;
        colors[13] = Color.cyan;
        colors[14] = Color.cyan;
        List<Block> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Block b =
                    new Block(new Rectangle(new Point(SCREEN_WIDTH - FRAME_BLOCK_SIZE - (i + 1) * GAME_BLOCK_WIDTH,
                    250), GAME_BLOCK_WIDTH, GAME_BLOCK_HEIGHT));
            b.getCollisionRectangle().getFrame().setColor(colors[i]);
            list.add(b);
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    @Override
    public Color backgroundColor() {
        return Color.white;
    }
}
