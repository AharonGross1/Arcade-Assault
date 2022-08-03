// 209345180 Aharon Gross

/**
 * This class offers various calculation methods needed for the project.
 *
 * @author Aharon Gross
 */
public class GeneralCalcs {
    public static final double EPSILON = 1E-6;

    /**
     * This method compares 2 doubles with a margin of error.
     *
     * @param num1 - First double.
     * @param num2 - Second double.
     * @return - True if difference between doubles is smaller than EPSILON;
     */
    public static boolean isEqual(double num1, double num2) {
        return (Math.abs(num1 - num2) < EPSILON);
    }

    /**
     * This method checks whether a double is bigger than the other with a margin of error.
     *
     * @param bigger - First double.
     * @param smaller - Second double.
     * @return - True if difference between doubles is bigger than EPSILON;
     */
    public static boolean isBigger(double bigger, double smaller) {
        return ((bigger - smaller) > EPSILON);
    }
}
