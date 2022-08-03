// 209345180 Aharon Gross

import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The class in charge of running the game's different levels.
 */
public class GameFlow {

    private AnimationRunner ar;
    private KeyboardSensor ks;
    private GUI gui;
    private Counter score;

    /**
     * Constructor for the class.
     *
     * @param ar  - The animation runner to run the game.
     * @param ks  - The gui's keyboard.
     * @param gui - The gui.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.ar = ar;
        this.ks = ks;
        this.gui = gui;
        this.score = new Counter(0);
    }

    /**
     * The method to run the different levels.
     *
     * @param levels - The list of levels to run.
     */
    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar, this.gui, this.score);
            if (levelInfo.equals(levels.get(levels.size() - 1))) {
                level.setLastLevel(true);
            }
            level.initialize();

            while (level.getRemainingBlocks().getValue() > 0 && level.getRemainingBalls().getValue() > 0) {
                level.run();
            }

            if (level.getRemainingBalls().getValue() == 0) {
                break;
            }
        }
    }
}