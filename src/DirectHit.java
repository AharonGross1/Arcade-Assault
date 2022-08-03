// 209345180 Aharon Gross
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a level of the game.
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(new Velocity(0, -3));
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
        return ("DirectHit");
    }

    @Override
    public Sprite getBackground() {
        return new DirectHitBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        Block b = new Block(new Rectangle(new Point(380, 130), 40, 40));
        b.getCollisionRectangle().getFrame().setColor(Color.RED);
        list.add(b);
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public Color backgroundColor() {
        return Color.BLACK;
    }
}
