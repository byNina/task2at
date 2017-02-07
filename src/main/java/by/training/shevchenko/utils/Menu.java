package by.training.shevchenko.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.training.shevchenko.beans.Sentence;

/**
 * this class provides an interface for application
 * 
 * @author Shevchenko
 *
 */
public class Menu {
	private static Scanner sc;
	private SentenceUtil sentUtil;

	public void greeting() {
		System.out.println("������ ���������� � ������ ����� ������ k-� ����� �������� �������� ��������.");
		System.out.println("���� k ������ ����� �����, ������������� �� ���������c�.");
	}

	public void run() {
		System.out.println("***************");
		System.out.println("�������� ��������:");

		if (sc == null) {
			sc = new Scanner(System.in);
		}

		int userChoice = 0;
		boolean isNumber;
		do {
			isNumber = true;
			System.out.println("1. ������ ����� � �������.");
			System.out.println("0. ����� �� ����������.");
			try {
				userChoice = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				isNumber = false;
				System.out.println("������! ������� ����� ������ ����.");
			}
		} while (!isNumber);

		switch (userChoice) {
		case 1:
			enterText();
			break;
		case 0:
			exit();
			break;
		default:
			System.out.println(
					"������! ������ \"" + userChoice + "\" ��� � ����. ������� ����� ������ �� ������������ ����.");
			run();
		}

	}

	private void enterText() {
		System.out.println("������� ����� (����� ��������� ���� ������ ������� Enter ������):");
		List<Sentence> sentences = new ArrayList<Sentence>();

		boolean isAcceptable;

		do {
			isAcceptable = true;
			try {
				while (sc.hasNextLine()) {
					String userEntry = sc.nextLine();
					if (userEntry.isEmpty()) {
						break;
					}
					Sentence sentence = new Sentence(userEntry);
					sentences.add(sentence);
				}
			} catch (Exception e) {
				System.out.println("������! ������� �����.");
				isAcceptable = false;
			}
		} while (!isAcceptable);

		System.out.println("����� ������� ������.");
		System.out.println();

		afterEnterText(sentences);
	}

	private void afterEnterText(List<Sentence> sentences) {

		System.out.println("***************");
		System.out.println("�������� ��������:");
		System.out.println("1. �������� k-� ����� �������� ��������.");
		System.out.println("0. ����� �� ����������.");

		int userChoice = 0;
		boolean isNumber;
		do {
			isNumber = true;
			try {
				userChoice = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("������! ������� ����� ������ ����.");
				isNumber = false;
			}
		} while (!isNumber);

		switch (userChoice) {
		case 1:
			sentUtil = new SentenceUtil(sentences);
			setChar();
			break;
		case 0:
			exit();
			break;
		default:
			System.out.println(
					"������! ������ \"" + userChoice + "\" ��� � ����. ������� ����� ������ �� ������������ ����.");
			afterEnterText(sentences);
		}

	}

	private void setChar() {
		System.out.println("***************");

		System.out.println("������� ����� ���������� �����:");
		boolean isNumber;
		int charIndex = -1;
		do {
			isNumber = true;
			try {
				charIndex = Integer.parseInt(sc.nextLine()) - 1;
				if (charIndex < 0) {
					System.out.println("������! ������� ����� ���������� ����� � ���� ������ �������������� �����.");
					isNumber = false;
				}
			} catch (Exception e) {
				System.out.println("������! ������� ����� ���������� �����.");
				isNumber = false;
			}
		} while (!isNumber);

		System.out.println("������� ����������� �����:");
		boolean isChar;
		char charToChange = '\u0000';
		String userEnter;
		do {
			isChar = true;
			try {
				userEnter = sc.nextLine();
				if (isChar(userEnter)) {
					charToChange = userEnter.charAt(0);
				} else {
					System.out.println("������! ������� ����������� ����� � ���� ������ �������.");
					isChar = false;
				}
			} catch (Exception e) {
				System.out.println("������! ������� ����������� ����� � ���� ������ �������.");
				isChar = false;
			}
		} while (!isChar);

		afterSetChar(charIndex, charToChange);
	}

	private boolean isChar(String userEnter) {
		Pattern pattern = Pattern.compile("[a-zA-Z�-��-߸�]");
		Matcher matcher = pattern.matcher(userEnter);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	private void afterSetChar(int charIndex, char charToChange) {

		System.out.println("1. �������� " + (charIndex + 1) + "-�� ����� ������� ����� �� " + charToChange);
		System.out.println("2. ������ ������ ��������");
		System.out.println("0. ����� �� ����������.");

		int userChoice = 0;
		boolean isNumber;
		do {
			isNumber = true;
			try {
				userChoice = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("������! ������� ����� ������ ����.");
				isNumber = false;
			}
		} while (!isNumber);

		switch (userChoice) {
		case 1:
			sentUtil.changeChar(charIndex, charToChange);
			showResult();
			break;
		case 2:
			setChar();
			break;
		case 0:
			exit();
			break;
		default:
			System.out.println(
					"������! ������ \"" + userChoice + "\" ��� � ����. ������� ����� ������ �� ������������ ����.");
			afterSetChar(charIndex, charToChange);
		}
	}

	private void showResult() {
		List<Sentence> sentences = new ArrayList<Sentence>();
		sentences = sentUtil.getSentences();
		for (Sentence sentence : sentences) {
			System.out.println(sentence);
		}
		afterShowResult();
	}

	private void afterShowResult() {
		System.out.println("1. ������ ����� �����");
		System.out.println("2. ������ ������ ��������");
		System.out.println("0. ����� �� ����������.");

		int userChoice = 0;
		boolean isNumber;
		do {
			isNumber = true;
			try {
				userChoice = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("������! ������� ����� ������ ����.");
				isNumber = false;
			}
		} while (!isNumber);

		switch (userChoice) {
		case 1:
			enterText();
			break;
		case 2:
			setChar();
			break;
		case 0:
			exit();
			break;
		default:
			System.out.println(
					"������! ������ \"" + userChoice + "\" ��� � ����. ������� ����� ������ �� ������������ ����.");
			afterShowResult();
		}

	}

	private void exit() {
		System.out.println("���������� ��������� ������.");
		sc.close();
	}
}
