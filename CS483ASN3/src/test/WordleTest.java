package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.WordleGame;
import main.Feedback;

public class WordleTest {
	@Test
	void testGameCaps() {
		var game = new WordleGame();
		var fb = new Feedback("CIDER", game.makeGuess("CIDER").getPattern() , "APPLE");	
		assertEquals("BBBYB",fb.getPattern());
	}
	
	@Test
	void testLowerCase() {
		var game = new WordleGame();
		var fb = new Feedback("brave", game.makeGuess("brave").getPattern() , "APPLE");	
		assertEquals("BBBYB",fb.getPattern());
	}
	
	@Test
	void testDupes() {
		var game = new WordleGame();
		var fb = new Feedback("EAGLE", game.makeGuess("EAGLE").getPattern() , "APPLE");	
		assertEquals("YYBGG",fb.getPattern());
	}
	
	@Test
	void testDictionary() {
		var game = new WordleGame();
		var fb = new Feedback("APPLY", game.makeGuess("APPLY").getPattern() , "APPLE");	
		assertEquals("GGGGB",fb.getPattern());
	}
	
	@Test
	void testRandom() {
		var game = new WordleGame();
		game.startGame();
		String word1 = game.getSecretWord();
		game.startGame();
		String word2 = game.getSecretWord();
		assertEquals(false,word1.equals(word2));
	}
}
