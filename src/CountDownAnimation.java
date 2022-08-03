// 209345180 Aharon Gross

import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * A class to display an animation of a countdown on top of the game.
 */
public class CountDownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Color backgroundColor;

    /**
     * Constructor for the class.
     *
     * @param numOfSeconds    - The time the animation should play.
     * @param countFrom       - the number to countdown from.
     * @param gameScreen      - The sprites to draw on the screen.
     * @param backgroundColor - The background color.
     */
    public CountDownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, Color backgroundColor) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.backgroundColor = backgroundColor;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        Frame frame = new Frame(0, 0, d.getWidth(), d.getHeight(), this.backgroundColor);
        frame.animationLoop(d);
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.GREEN);
        if (this.countFrom == 3) {
            d.drawText((d.getWidth() / 2) - 20, d.getHeight() - 200, String.valueOf(this.countFrom), 50);
            this.countFrom--;
            return;
        }
        if (this.countFrom <= 0) {
            d.drawText((d.getWidth() / 2) - 40, d.getHeight() - 200, "GO", 50);
            sleeper.sleepFor((long) (this.numOfSeconds * 1000 / 3));
        } else {
            d.drawText((d.getWidth() / 2) - 20, d.getHeight() - 200, String.valueOf(this.countFrom), 50);
            sleeper.sleepFor((long) (this.numOfSeconds * 1000 / 3));
        }
        this.countFrom--;
    }

    @Override
    public boolean shouldStop() {
        return this.countFrom < -1;
    }
}
