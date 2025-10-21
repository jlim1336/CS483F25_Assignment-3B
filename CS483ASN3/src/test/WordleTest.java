/* Wordle Test Class
 * Tester: Jordan Lim
 * Purpose: To test and ensure a 90% or greater class coverage of the provided Wordle game.
 */

package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.WordleGame;
import main.Dictionary;
import main.Feedback;

public class WordleTest {
	
	@Test
	void testGameCaps() {
		var game = new WordleGame();
		var fb = new Feedback("CIDER", game.makeGuess("CIDER").getPattern() , "APPLE");	
		assertEquals("BBBYB",fb.getPattern(),"Feedback on all uppercase guesses");
	}
	
	@Test
	void testLowerCase() {
		var game = new WordleGame();
		var fb = new Feedback("brave", game.makeGuess("brave").getPattern() , "APPLE");	
		assertEquals("BBBYB",fb.getPattern(),"Feedback on all lowercase guesses");
	}
	
	@Test
	void testDupes() {
		var game = new WordleGame();
		var fb = new Feedback("EAGLE", game.makeGuess("EAGLE").getPattern() , "APPLE");	
		assertEquals("YYBGG",fb.getPattern(),"Feedback on duplicate letter guesses");
	}
	
	@Test
	void testLetterAmount() {
		var game = new WordleGame();
		IllegalArgumentException thrown = assertThrows(
	            IllegalArgumentException.class,
	            () -> game.makeGuess("APPLES")
	        );
		assertEquals("Guess must be exactly 5 letters.",thrown.getMessage(),"Feedback on a > 5 letter word");
		thrown = assertThrows(
	            IllegalArgumentException.class,
	            () -> game.makeGuess("APPL")
	        );
		assertEquals("Guess must be exactly 5 letters.",thrown.getMessage(),"Feedback on a < 5 letter word");
	}
	
	@Test
	void testDictionary() {
		var game = new WordleGame();
		IllegalArgumentException thrown;
		String[] inputs = {"TLPAE","23ADS", "WOOL!"};
		for(String a: inputs) {
			thrown = assertThrows(
		            IllegalArgumentException.class,
		            () -> game.makeGuess(a)
		     );
			assertEquals("Word not found in dictionary.",thrown.getMessage(),"Feedback on a inelligible word");
		}
	}
	
	@Test
	void testAttempts() {
		var game = new WordleGame();
		game = new WordleGame();
		for(int i = 0; i < 7; i++) {
			game.makeGuess("EAGLE").getPattern();
		}
		assertEquals(6,game.getAttempts());
	}
	
	@Test
	void testGameOver() {
		var game = new WordleGame();
		for(int i = 0; i < 6; i++) {
			game.makeGuess("EAGLE").getPattern();
		}
		assertEquals(false,game.isGameOver());
		var game2 = new WordleGame();
		for(int i = 0; i < 7; i++) {
			game2.makeGuess("EAGLE").getPattern();
		}
		assertEquals(true,game2.isGameOver(),"Check if game is over attempts");
		IllegalStateException thrown = assertThrows(
	            IllegalStateException.class,
	            () -> new Feedback("EAGLE", game2.makeGuess("EAGLE").getPattern() , "APPLE")
	        );
		assertEquals("Game already ended.",thrown.getMessage(),"Exception thrown if input ongoing to a ended game");
		
	}
	
	@Test
	void testToString() {
		var game = new WordleGame();
		var fb = new Feedback("CIDER", game.makeGuess("CIDER").getPattern() , "APPLE");	
		assertEquals("CIDER\nBBBYB",fb.toString(),"Output Test");
	}
	
	@Test
	void testRandom() {
		var game = new WordleGame();
		game.startGame();
		String word1 = game.getSecretWord();
		game.startGame();
		String word2 = game.getSecretWord();
		assertEquals(false,word1.equals(word2),"Randomized Selection on Secret Word");
		
	}
}
