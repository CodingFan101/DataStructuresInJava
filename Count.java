import java.util.List;

public class Count {

    /**
     * countUp - A recursive function that counts up from 1 to n.
     *
     * @param n      The integer value to count up to
     * @param counts List of count values from 1 to n.
     * @throws       IllegalArgumentException if n < 1
     */
    public static void countUp(int n, List<Integer> counts) {
        // TODO: add each value from 1 to n to counts list
        // using recursive calls to countUp.
    	if(n < 1) {
    		throw new IllegalArgumentException("n must be positive");
    	}
    	if(n == 1) {
    		counts.add(n);
    		return;
    	}
    	countUp(n - 1, counts);
    	counts.add(n);
    }

    /**
     * countDown - A recursive function that counts down from n to 1.
     *
     * @param n      The integer value to count down from.
     * @param counts List of count values from n to 1.
     * @throws       IllegalArgumentException if n < 1
     */
    public static void countDown(int n, List<Integer> counts) {
        // TODO: add each value from n down to 1 to counts list
        // using recursive calls to countDown.
    	if(n < 1) {
    		throw new IllegalArgumentException("n must be positive");
    	}
    	if(n > 1) {
    		counts.add(n);
    		countDown(n - 1, counts);
    	}
    	if(n == 1) {
    		counts.add(n);
    		return;
    	}
    }

}
