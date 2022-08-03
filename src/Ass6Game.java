// 209345180 Aharon Gross

import biuoop.KeyboardSensor;
import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to run the Arkanoid game.
 */
public class Ass6Game {

    /**
     * The main method used to run the game.
     *
     * @param args - Not used.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        KeyboardSensor ks = gui.getKeyboardSensor();
        AnimationRunner animationRunner = new AnimationRunner(gui);
        List<LevelInformation> list = new ArrayList<>();
        // Create a list of argument levels
        if (args.length > 0) {
            for (String arg : args) {
                switch (arg) {
                    case "1":
                        list.add(new DirectHit());
                        break;
                    case "2":
                        list.add(new WideEasy());
                        break;
                    case "3":
                        list.add(new Green3());
                        break;
                    case "4":
                        list.add(new FinalFour());
                        break;
                    default:
                        break;
                }
            }
        }

        // Create a default game
        if (list.size() == 0) {
            list.add(new DirectHit());
            list.add(new WideEasy());
            list.add(new Green3());
            list.add(new FinalFour());
        }
        GameFlow gameFlow = new GameFlow(animationRunner, ks, gui);
        gameFlow.runLevels(list);
    }
}
