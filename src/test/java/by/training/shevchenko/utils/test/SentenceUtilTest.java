package by.training.shevchenko.utils.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import by.training.shevchenko.utils.SentenceUtil;
import by.training.shevchenko.beans.Sentence;

public class SentenceUtilTest {
	private SentenceUtil sentUtil;

	@Test
	public void changeCharTest() {
		List<Sentence> sentences = new ArrayList<Sentence>();
		Sentence sentence = new Sentence("Если ты крепок духом...");
		sentences.add(sentence);
		sentUtil = new SentenceUtil(sentences);
		sentUtil.changeChar(2, 'O');

		Sentence sentenceExpected = new Sentence("ЕсOи ты крOпок дуOом...");
		Sentence sentenceActual = sentUtil.getSentences().get(0);

		assertEquals(sentenceExpected, sentenceActual);
	}

}
