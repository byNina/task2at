package by.training.shevchenko.beans;

/**
 * @author Shevchenko
 *
 */
public class Word {
	private StringBuilder word;
	private int index;

	@Override
	public String toString() {
		return word.toString();
	}

	public StringBuilder getWord() {
		return word;
	}

	public void setWord(StringBuilder word) {
		this.word = word;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Word(StringBuilder word) {
		super();
		this.word = word;
	}

	public Word() {
		super();
		StringBuilder word = new StringBuilder();
		this.word = word;
	}

	public StringBuilder replaceChar(int index, char c) {
		if (word != null && word.length() > index) {
			word.deleteCharAt(index);
			word.insert(index, c);
		}
		return word;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
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
		Word other = (Word) obj;
		if (index != other.index)
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

	
	
}
