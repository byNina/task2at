package by.training.shevchenko.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.training.shevchenko.beans.Sentence;
import by.training.shevchenko.beans.Word;

/**
 * This class provides tools to edit sentence.
 * 
 * @author Shevchenko
 *
 */

public class SentenceUtil {

	private List<Sentence> sentences;

	/**
	 * Construstor with params
	 * 
	 * @param sentences
	 */
	public SentenceUtil(List<Sentence> sentences) {
		super();
		this.sentences = sentences;
	}

	public List<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(List<Sentence> sentences) {
		this.sentences = sentences;
	}

	/**
	 * Method changes char in charIndex-position in each word of sentence to
	 * given char
	 * 
	 * @return void
	 * @param charIndex
	 * @param charToChange
	 */
	public void changeChar(int charIndex, char charToChange) {

		List<Word> words = new ArrayList<Word>();
		if (sentences != null && !sentences.isEmpty()) {
			for (Sentence sentence : sentences) {
				if (sentence != null) {
					words = toWords(sentence);
					sentence.setWords(words);
					for (Word word : words) {
						word.replaceChar(charIndex, charToChange);
					}
					sentence.update(words);
				}
			}
		}
	}

	private List<Word> toWords(Sentence sentence) {
		List<Word> words = new ArrayList<Word>();
		char lastChar = '\u0000';
		char currentChar = '\u0000';
		int index = 0;
		Word word = null;
		for (int i = sentence.getSentence().length() - 1; i >= -1; i--) {
			if (i != -1) {
				currentChar = sentence.getSentence().charAt(i);
			} else {
				currentChar = '\u0000';
			}
			if (Character.isLetter(currentChar) && Character.isLetter(lastChar)) {
				if (word != null) {
					word.getWord().insert(0, currentChar);
				}
			} else {
				if (Character.isLetter(currentChar) && !Character.isLetter(lastChar)) {
					word = new Word();
					word.getWord().insert(0, currentChar);
				} else {
					if (!Character.isLetter(currentChar) && Character.isLetter(lastChar)) {
						index = i + 1;
						word.setIndex(index);
						words.add(word);
					}
				}
			}
			lastChar = currentChar;
		}
		return words;
	}

}
