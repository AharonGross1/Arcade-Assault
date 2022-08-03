// 209345180 Aharon Gross

/**
 * A counter keeps track of a number representing something.
 */
public class Counter {
    private int value;

    /**
     * Constructor for the class.
     *
     * @param num - initial number to start the counter off with.
     */
    public Counter(int num) {
        this.value = num;
    }

    /**
     * Add number to current count.
     *
     * @param number - the number to add to the count.
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * Subtract number from current count.
     *
     * @param number - the number to subtract.
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * Getter for the class's value.
     *
     * @return this.value.
     */
    public int getValue() {
        return this.value;
    }
}