package by.training.shevchenko.beans;

import java.util.List;

/**
 * @author Shevchenko
 *
 */
public class Sentence {

	private StringBuilder sentence;
	private List<Word> words;
	
	

	/**
	 * Method update sentence according to list of words
	 * 
	 * @return void
	 * @param words
	 */
	public void update(List<Word> words) {
		int firstIndex;
		int lastIndex;
		for (Word word : words) {
			firstIndex = word.getIndex();
			lastIndex = firstIndex + word.getWord().length();
			sentence.replace(firstIndex, lastIndex, word.toString());
		}

	}

	@Override
	public String toString() {
		return sentence.toString();
	}

	public StringBuilder getSentence() {
		return sentence;
	}

	public void setSentence(StringBuilder sentence) {
		this.sentence = sentence;
	}

	public List<Word> getWords() {
		return words;
	}

	public void setWords(List<Word> words) {
		this.words = words;
	}

	public Sentence(StringBuilder sentence) {
		super();
		this.sentence = sentence;
	}

	public Sentence(String sentence) {
		super();
		this.sentence = new StringBuilder();
		this.sentence.append(sentence);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sentence == null) ? 0 : sentence.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sentence other = (Sentence) obj;
		if (sentence == null) {
			if (other.sentence != null)
				return false;
		} else {
			for (int i = 0; i < sentence.length(); i++) {
				if (sentence.charAt(i) != other.sentence.charAt(i)) {
					return false;
				}
			}
		}
		return true;
	}

}
