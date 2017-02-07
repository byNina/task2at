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
		System.out.println("Данное приложение в каждом слове текста k-ю букву заменяет заданным символом.");
		System.out.println("Если k больше длины слова, корректировка не выполняетcя.");
	}

	public void run() {
		System.out.println("***************");
		System.out.println("Выберите действие:");

		if (sc == null) {
			sc = new Scanner(System.in);
		}

		int userChoice = 0;
		boolean isNumber;
		do {
			isNumber = true;
			System.out.println("1. Ввести текст в консоли.");
			System.out.println("0. Выйти из приложения.");
			try {
				userChoice = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				isNumber = false;
				System.out.println("Ошибка! Введите номер пункта меню.");
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
					"Ошибка! Пункта \"" + userChoice + "\" нет в меню. Введите номер пункта из предложенных ниже.");
			run();
		}

	}

	private void enterText() {
		System.out.println("Введите текст (Чтобы закончить ввод текста нажмите Enter дважды):");
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
				System.out.println("Ошибка! Введите текст.");
				isAcceptable = false;
			}
		} while (!isAcceptable);

		System.out.println("Текст успешно введен.");
		System.out.println();

		afterEnterText(sentences);
	}

	private void afterEnterText(List<Sentence> sentences) {

		System.out.println("***************");
		System.out.println("Выберите действие:");
		System.out.println("1. Заменить k-ю букву заданным символом.");
		System.out.println("0. Выйти из приложения.");

		int userChoice = 0;
		boolean isNumber;
		do {
			isNumber = true;
			try {
				userChoice = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("Ошибка! Введите номер пункта меню.");
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
					"Ошибка! Пункта \"" + userChoice + "\" нет в меню. Введите номер пункта из предложенных ниже.");
			afterEnterText(sentences);
		}

	}

	private void setChar() {
		System.out.println("***************");

		System.out.println("Введите номер заменяемой буквы:");
		boolean isNumber;
		int charIndex = -1;
		do {
			isNumber = true;
			try {
				charIndex = Integer.parseInt(sc.nextLine()) - 1;
				if (charIndex < 0) {
					System.out.println("Ошибка! Введите номер заменяемой буквы в виде целого положительного числа.");
					isNumber = false;
				}
			} catch (Exception e) {
				System.out.println("Ошибка! Введите номер заменяемой буквы.");
				isNumber = false;
			}
		} while (!isNumber);

		System.out.println("Введите необходимую букву:");
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
					System.out.println("Ошибка! Введите необходимую букву в виде одного символа.");
					isChar = false;
				}
			} catch (Exception e) {
				System.out.println("Ошибка! Введите необходимую букву в виде одного символа.");
				isChar = false;
			}
		} while (!isChar);

		afterSetChar(charIndex, charToChange);
	}

	private boolean isChar(String userEnter) {
		Pattern pattern = Pattern.compile("[a-zA-Zа-яА-ЯёЁ]");
		Matcher matcher = pattern.matcher(userEnter);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	private void afterSetChar(int charIndex, char charToChange) {

		System.out.println("1. Заменить " + (charIndex + 1) + "-ую букву каждого слова на " + charToChange);
		System.out.println("2. Ввести другие значения");
		System.out.println("0. Выйти из приложения.");

		int userChoice = 0;
		boolean isNumber;
		do {
			isNumber = true;
			try {
				userChoice = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("Ошибка! Введите номер пункта меню.");
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
					"Ошибка! Пункта \"" + userChoice + "\" нет в меню. Введите номер пункта из предложенных ниже.");
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
		System.out.println("1. Ввести новый текст");
		System.out.println("2. Ввести другие значения");
		System.out.println("0. Выйти из приложения.");

		int userChoice = 0;
		boolean isNumber;
		do {
			isNumber = true;
			try {
				userChoice = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("Ошибка! Введите номер пункта меню.");
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
					"Ошибка! Пункта \"" + userChoice + "\" нет в меню. Введите номер пункта из предложенных ниже.");
			afterShowResult();
		}

	}

	private void exit() {
		System.out.println("Приложение завершило работу.");
		sc.close();
	}
}
