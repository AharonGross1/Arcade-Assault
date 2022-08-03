// 209345180 Aharon Gross
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Creates the background sprite for the level.
 */
public class WideEasyBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(255, 255, 153));
        for (int i = 0; i < 650; i += 7) {
            d.drawLine(150, 150, i, 250);
        }
        d.fillCircle(150, 150, 60);
        d.setColor(new Color(230, 230, 0));
        d.fillCircle(150, 150, 50);
        d.setColor(Color.yellow);
        d.fillCircle(150, 150, 40);

    }

    @Override
    public void timePassed() {
    }
}
