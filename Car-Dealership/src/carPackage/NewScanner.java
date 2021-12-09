package carPackage;

import java.util.Scanner;

public class NewScanner {

	public static double readDouble(int minAllowed, int maxAllowed) {
		Scanner chopper = new Scanner(System.in);
		double input = chopper.nextDouble();
		chopper.nextLine();

		while (input > maxAllowed || input <= minAllowed) {
			System.err.println("Error: input must be between " + minAllowed + " and " + maxAllowed);
			System.out.println("Enter input again: ");
			input = chopper.nextDouble();
			chopper.nextLine();
		}
		return input;
	}

	public static String readStatus() {
		Scanner chopper = new Scanner(System.in);
		String input = chopper.nextLine();

		while (!(input.equals("Good") || input.equals("Ok") || input.equals("Bad") || input.equals(""))) {
			System.err.println("Error: status must be either Good, Bad, or Ok");
			System.out.println("Enter input again: ");
			input = chopper.nextLine();
		}
		return input;
	}

	public static String readMechanicType() {
		Scanner chopper = new Scanner(System.in);
		String input = chopper.nextLine();

		while (!(input.equals("Light") || input.equals("Medium") || input.equals("Heavy"))) {
			System.err.println("Error: mechanic type must be either Light, Medium, or Heavy");
			System.out.println("Enter input again: ");
			input = chopper.nextLine();
		}
		return input;
	}
}
