// 209345180 Aharon Gross

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * A class to represent the pause and endgame screen.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor sensor;
    private String key;
    //private Animation animation;
    private Boolean stop;
    private int score;
    private Boolean alreadyPressed;

    /**
     * Constructor for the class.
     *
     * @param sensor - The gui's keyboard.
     * @param key    - The string to print.
     * @param score  - The current score.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, int score) {
        this.sensor = sensor;
        this.key = key;
        this.stop = false;
        this.score = score;
        this.alreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.sensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.alreadyPressed = false;
        }
        d.drawText(d.getWidth() / 2 - 15 * key.length() / 2, d.getHeight() / 6, this.key, 30);
        d.drawText(d.getWidth() / 2 - 160, d.getHeight() / 2, "press space to continue", 30);
        d.drawText(10, d.getHeight() - 20, "Score: " + score, 20);
        if (this.sensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            if (!this.alreadyPressed) {
                this.stop = true;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

