// 209345180 Aharon Gross

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The class in charge of running the animation.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    private boolean play;

    /**
     * Constructor for the class.
     *
     * @param gui - the gui.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
        this.play = true;
    }

    /**
     * Setter for the game's play param.
     *
     * @param play - true or false.
     */
    public void setPlay(Boolean play) {
        this.play = play;
    }

    /**
     * In charge of running the game.
     *
     * @param animation - An animation to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            // End of game.
            if (!play) {
                gui.close();
                return;
            }
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}