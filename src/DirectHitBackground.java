// 209345180 Aharon Gross

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Creates the background sprite for the level.
 */
public class DirectHitBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.blue);
        d.drawLine(375, 150, 270, 150);
        d.drawLine(425, 150, 530, 150);
        d.drawLine(400, 175, 400, 280);
        d.drawLine(400, 125, 400, 20);
        d.drawCircle(400, 150, 110);
        d.drawCircle(400, 150, 80);
        d.drawCircle(400, 150, 50);
    }

    @Override
    public void timePassed() {
    }
}
