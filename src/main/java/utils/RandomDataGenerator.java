package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.luma.pages.CommonPage;

public class RandomDataGenerator extends CommonPage {

	public RandomDataGenerator(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	static String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static String lowercase = "abcdefghijklmnopqrstuvwxyz";
	static String digits = "0123456789";
	static String specialChars = "!@#$%^&*()-_=+";

	// Method to generate random first name, last name, and email
	public static String generateRandomName() {

		String allChars = uppercase + lowercase;
		String firstName = generateRandomString(allChars, 1, 12);
		return firstName;
		// return shuffleString(firstName); // Shuffle to ensure randomness

	}

	public static String generateRandomEmail() {
		// email structure: at least one of each type
		String username = generateRandomString(uppercase + lowercase + digits, 1, 10);
		String domain = generateRandomString(lowercase, 5, 8); // Random domain name length
		String extension = generateRandomString(lowercase, 2, 3); // Random extension length (e.g.,
																	// .com, .net)

		return username + "@" + domain + "." + extension;
	}

	public static String generateRandomPassword() {
		// Password structure: at least one of each type
		String password = generateRandomString(uppercase, 1, 1) + // One uppercase letter
				generateRandomString(lowercase, 1, 1) + // One lowercase letter
				generateRandomString(digits, 1, 1) + // One digit
				generateRandomString(specialChars, 1, 1); // One special character

		// Fill the rest of the password with random characters from all sets
		String allChars = uppercase + lowercase + digits + specialChars;
		password += generateRandomString(allChars, 8, 12); // Random length between 8-12 characters

		return password;
	}

	// Helper method to generate a random string from a set of characters
	private static String generateRandomString(String chars, int minLength, int maxLength) {
		int length = (int) (Math.random() * (maxLength - minLength + 1)) + minLength;
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int randomIndex = (int) (Math.random() * chars.length());
			stringBuilder.append(chars.charAt(randomIndex));
		}
		return stringBuilder.toString();
	}

}
