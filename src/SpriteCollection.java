// 209345180 Aharon Gross

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * This class represents a collection of sprites.
 */
public class SpriteCollection {
    private List<Sprite> spriteList;

    /**
     * Constructor for the class.
     */
    public SpriteCollection() {
        spriteList = new ArrayList<>();
    }

    /**
     * Add a sprite to the collection.
     *
     * @param s - The sprite to add.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * remove a sprite from the collection.
     *
     * @param s - The sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < spriteList.size(); i++) {
            spriteList.get(i).timePassed();
        }
    }

    /**
     * Call drawOn(d) on all sprites.
     *
     * @param d - The draw surface to draw the sprites on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.spriteList) {
            s.drawOn(d);
        }
    }
}