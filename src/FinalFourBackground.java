// 209345180 Aharon Gross
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Creates the background sprite for the level.
 */
public class FinalFourBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        for (int i = 0; i < 10; i++) {
            d.drawLine(120 + i * 9, 450, 100 + i * 9, 600);
        }
        for (int i = 0; i < 10; i++) {
            d.drawLine(570 + i * 9, 530, 540 + i * 9, 600);
        }
        d.setColor(new Color(220, 220, 220));
        d.fillCircle(145, 455, 30);
        d.fillCircle(126, 435, 25);
        d.fillCircle(595, 532, 27);
        d.fillCircle(585, 495, 22);
        d.setColor(new Color(190, 190, 190));
        d.fillCircle(165, 435, 28);
        d.fillCircle(613, 500, 28);
        d.setColor(new Color(170, 170, 170));
        d.fillCircle(200, 450, 30);
        d.fillCircle(650, 510, 30);
        d.fillCircle(177, 470, 25);
        d.fillCircle(627, 530, 25);

    }

    @Override
    public void timePassed() {
    }
}
