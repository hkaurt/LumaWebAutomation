package utils;

public class RandomDataGenerator {

	static String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static String lowercase = "abcdefghijklmnopqrstuvwxyz";
	static String digits = "0123456789";
	static String specialChars = "!@#$%^&*()-_=+";

	// Method to generate random first name, last name, and email
	public static String generateRandomFirstName() {
		// firstName structure: at least one of each type
		String firstName = generateRandomString(uppercase, 1, 1) + // One uppercase letter
				generateRandomString(lowercase, 1, 1); // One lowercase letter

		// Fill the rest of the password with random characters from all sets
		String allChars = uppercase + lowercase;
		firstName += generateRandomString(allChars, 8, 12); // Random length between 8-12 characters

		return shuffleString(firstName); // Shuffle to ensure randomness
	}

	public static String generateRandomLastName() {
		// lastName structure: at least one of each type
		String lastName = generateRandomString(uppercase, 1, 1) + // One uppercase letter
				generateRandomString(lowercase, 1, 1); // One lowercase letter

		// Fill the rest of the password with random characters from all sets
		String allChars = uppercase + lowercase;
		lastName += generateRandomString(allChars, 8, 12); // Random length between 8-12 characters

		return shuffleString(lastName); // Shuffle to ensure randomness
	}

	public static String generateRandomEmail() {
		// email structure: at least one of each type
		String username = generateRandomString("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", 5, 10); // Random
																															// length
																															// between
																															// 5-10
		String domain = generateRandomString("abcdefghijklmnopqrstuvwxyz", 5, 8); // Random domain name length
		String extension = generateRandomString("abcdefghijklmnopqrstuvwxyz", 2, 3); // Random extension length (e.g.,
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

		return shuffleString(password); // Shuffle to ensure randomness
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

	// Helper method to shuffle a string for added randomness
	private static String shuffleString(String input) {
		StringBuilder shuffled = new StringBuilder(input);
		for (int i = 0; i < shuffled.length(); i++) {
			int j = (int) (Math.random() * shuffled.length());
			char temp = shuffled.charAt(i);
			shuffled.setCharAt(i, shuffled.charAt(j));
			shuffled.setCharAt(j, temp);
		}
		return shuffled.toString();
	}
}
