package main;
import java.util.*;

/**
 * Represents the logic of a simplified Wordle game.
 *
 */
public class WordleGame {

	private static final int MAX_ATTEMPTS = 6;
	private String wordleWord;
	private int attempts;
	private boolean gameOver;
	private Dictionary dictionary;

	/**
	 * Constructs a new Wordle Game using a dictionary.
	 */
	public WordleGame() {
		dictionary = new Dictionary();
		startGame();
	}

	/**
	 * Starts the game with a new word. Resets attempts and game-over flag.
	 */
	public void startGame() {
		wordleWord = dictionary.getRandomWord();
		attempts = 0;
		gameOver = false;
	}

	/**
	 * Accepts a player guess, validates it, and sends feedback.
	 *
	 * @param guess the guessed 5-letter word
	 * @return feedback object describing match results
	 * @throws IllegalArgumentException if guess is invalid
	 * @throws IllegalStateException    if game is already over
	 */
	public Feedback makeGuess(String guess) {
		guess = guess.toLowerCase(); //Bug fix for caps
		if (gameOver)
			throw new IllegalStateException("Game already ended.");

		if (guess == null || guess.length() != 5)
			throw new IllegalArgumentException("Guess must be exactly 5 letters.");

		if (!dictionary.isValidWord(guess))
			throw new IllegalArgumentException("Word not found in dictionary.");

		attempts++;

		if (attempts > MAX_ATTEMPTS)
			gameOver = true;

		Feedback feedback = generateFeedback(guess);

		if (feedback.isCorrect()) {
			System.out.println("Hooray! You got it!");
			gameOver=true;//bug-fix: ended the game once guessed correctly
		}

		return feedback;
	}

	/**
	 * Generates feedback comparing the guess to the secret word.
	 *
	 * @param guess player guess
	 * @return feedback pattern object
	 */
	private Feedback generateFeedback(String guess) {
		char[] pattern = new char[5];
		boolean[] usedSecret = new boolean[5];
		boolean dupe = false;
		// Step 1: green boxes
		for (int i = 0; i < 5; i++) {
			if (guess.charAt(i) == wordleWord.charAt(i)) {
				//bug fix: check secret for dupes
				for(int j =0; j < i;j++) {
					if(wordleWord.charAt(j) == wordleWord.charAt(i)) {
						dupe = true;
					}
				}
				System.out.print(guess.charAt(i) + " | " + wordleWord.charAt(i) + " | " + dupe + "\n");
				if(dupe) {
					pattern[i] = 'G';
					usedSecret[i] = true;
				}
				else {
					pattern[i] = 'G';
					usedSecret[i] = true;
				}
				
			}
		}

		// Step 2: yellows and gray boxes
		for (int i = 0; i < 5; i++) {
			if (pattern[i] == 'G')
				continue;
			char g = guess.charAt(i);
			boolean found = false;

			for (int j = 0; j < 5; j++) {
				if (g == wordleWord.charAt(j) && !usedSecret[j] && !dupe) {
					found = true;
					usedSecret[j] = true;
					break;
				}
			}
			pattern[i] = found ? 'Y' : 'B';
		}

		return new Feedback(guess, new String(pattern), wordleWord);
	}

	/**
	 * Checks if the game has finished (either guessed or all attempts used).
	 * 
	 * @return true if game is over
	 */
	public boolean isGameOver() {

		return gameOver || attempts == MAX_ATTEMPTS + 1;
	}

	/**
	 * Returns the secret word (hidden in real gameplay).
	 * 
	 * @return the secret word
	 */
	public String getSecretWord() {
		return wordleWord;
	}

	/**
	 * Returns the number of guesses made so far.
	 * 
	 * @return number of attempts
	 */
	public int getAttempts() {
		return attempts;
	}
	
	/**
	 * Debug purposes: Sets the secret word
	 * @param the word set as the secret word
	 */
	public void setSecretWord(String newWord) {
		wordleWord = newWord.toLowerCase();
	}
}
