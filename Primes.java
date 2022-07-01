
import java.util.ArrayList;
import java.util.List;

public class Primes {

    public static List<Integer> primes(int max) {
        // If max is < 2, throw an IllegalArgumentException.
    	if(max < 2) {
    		throw new IllegalArgumentException();
    	}
        // Declare and create an integer list of prime candidates named candidates. The
        // candidates type should be List<Integer> and you should construct
        // an instance of ArrayList<Integer>. You will need to import these
        // types from the package java.util.
    	List<Integer> candidates = new ArrayList<Integer>();
    	for(int i = 2; i <= max; i++) {
    		candidates.add(i);
    	}
        // Initialize the candidates list with integers from 2 to max inclusive.

        // Declare and create an integer list of prime numbers named primes.
    	List<Integer> primes = new ArrayList<Integer>();
        // Call the filterCandidates method to extract the prime numbers from the list.
    	filterCandidates(candidates, primes);
        // Return the primes list.
        return primes;
    }

    /**
     * filterCandidates - Remove candidates that are divisible by prime from the candidates list.
     *
     * @param  candidates   A list of integers holding the candidate values.
     * @param  primes       A list of integers holding the prime values.
     */
    public static void filterCandidates(List<Integer> candidates, List<Integer> primes) {
        // If candidates list is empty, return.
    	if(candidates.isEmpty()) {
    		return;
    	}
        // Remove the first integer in the candidates list
        // and assign it to an int variable named prime.
    	int prime = candidates.remove(0);
        // Add the prime number to the primes list.
    	primes.add(prime);
        // Iterate over the candidates list using a for
        // loop with an empty update section.
        for (int i = 0; i < candidates.size();) {
            // Get the next int from candidates and assign to
            // an int variable named candidate.
        	int candidate = candidates.get(i);
            // If candidate is divisible by prime, remove from the candidates
            // list, otherwise increment the for loop counter variable.
        	if(candidate % prime == 0) {
        		candidates.remove(i);
        	}
        	else {
        		i++;
        	}
        }

        // Call filterCandidates recursively.
        filterCandidates(candidates, primes);
    }

}
