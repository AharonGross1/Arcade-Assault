// 209345180 Aharon Gross

import biuoop.DrawSurface;

/**
 * A class that represents the game's score.
 */
public class ScoreIndicator implements Sprite {
    private GameLevel g;
    private String str;

    /**
     * Constructor for the class.
     *
     * @param g   - the gameLevel to draw on
     * @param str - The string to display.
     */
    public ScoreIndicator(GameLevel g, String str) {
        this.g = g;
        this.str = str;
    }

    /**
     * Implements method in Sprites interface.
     *
     * @param d - The draw surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (g.getRemainingBlocks().getValue() == 0) {
            g.getScore().increase(100);
        } else {
            d.drawText(120, 20, "Score: "
                    + g.getScore().getValue(), 20);
            d.drawText(g.getScreenWidth() - 300, 20, "Level Name: "
                    + this.str, 20);
        }
    }

    /**
     * Implements method in Sprites interface.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Add the object to the game.
     *
     * @param g - the game to add to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
