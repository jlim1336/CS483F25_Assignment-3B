package main;
import java.util.*;
import java.util.Random;

public class Dictionary {
	private List<String> words;

	public Dictionary() {
		words = new ArrayList<>();

		/**
		 * Some uppercase, some lowercase
		 */
		words.add("APPLE");
		words.add("brave");
		words.add("CIDER");
		words.add("delta");
		words.add("EAGLE");
		words.add("flint");
		
		words.replaceAll(String::toLowerCase);
	}

	/**
	 * Returns random 5 letter word
	 * 
	 */
	public String getRandomWord() {
		if (words.isEmpty()) {
			return null;
		} else {
			Random random = new Random();
			int randInt = random.nextInt(words.size());
			return words.get(randInt).toLowerCase(); // picks random value in array list + bug fix
		}
	}

	
	/**
	 * Checks for word validity
	 * 
	 */
	public boolean isValidWord(String guess) {
		return words.contains(guess);
	}
}