// 209345180 Aharon Gross
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Creates the background sprite for the level.
 */
public class Green3Background implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(70, 70, 70));
        d.fillRectangle(100, 200, 10, 400);
        d.setColor(new Color(50, 50, 50));
        d.fillRectangle(90, 400, 30, 200);
        d.setColor(Color.BLACK);
        d.fillRectangle(60, 470, 90, 400);
        d.setColor(new Color(255, 194, 102));
        d.fillCircle(105, 185, 15);
        d.setColor(new Color(230, 92, 0));
        d.fillCircle(105, 185, 10);
        d.setColor(Color.white);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(67 + j * 17, 477 + i * 32, 10, 25);
            }
        }
        d.fillCircle(105, 185, 5);
    }

    @Override
    public void timePassed() {

    }
}
