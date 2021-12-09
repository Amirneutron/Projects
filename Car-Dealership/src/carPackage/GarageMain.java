package carPackage;

//you may need to change the package to match your project's package.
import java.util.Scanner;

public class GarageMain {

	private static final int REGISTER_CAR = 1;
	private static final int PRINT_CARS = 2;
	private static final int PRINT_CAR = 3;
	private static final int PRINT_CHEAPEST_CAR = 4;
	private static final int DRIVE_CAR = 5;
	private static final int FILL_GAS = 6;
	private static final int REGISTER_MACHANIC = 7;
	private static final int REPAIR_CAR = 8;
	private static final int QUIT = 9;

	private Car[] cars;
	private Mechanic[] mechanics;
	private final int maxGasLevel = 10; // 10 gallons.
	private int registeredCars;
	private int registeredMechanics;

	private Scanner input;

	/*
	 * Constructor to initialize your data set of cars and mechanics. It begins
	 * empty. The default size is 5 cars and 5 mechanics.
	 */

	public GarageMain() {
		final int MAX_CARS = 5;
		this.cars = new Car[MAX_CARS];
		this.registeredCars = 0;
		final int MAX_MECHANICS = 5;
		this.mechanics = new Mechanic[MAX_MECHANICS];
		this.registeredMechanics = 0;
		input = new Scanner(System.in);
	}

	/*
	 * This method will retrieve a car from the array based on a specified rgn. If
	 * the car was not created and added to the array, it will return NULL, meaning
	 * that the car does not exist in the system.
	 * 
	 */
	public Car retrieveCar(String carRgn) {
		for (int i = 0; i < this.cars.length; i++) {
			if (cars[i] != null && cars[i].getRgn().equals(carRgn)) {
				return cars[i];
			}
		}

		/*
		 * example of a null... if no car with the give rgn exists, the object does not
		 * exist (i.e. "nothing").
		 */

		return null;
	}

	public Car createCar() {
		/*
		 * TODO: Add logic to this method.
		 * 
		 * 1. read the car's registration number (rgn), model year, weight, price, gas
		 * meter, mileage, quality value and status. 2. use that information to create
		 * the car. 3. change the null below to the right reference that you need to
		 * return!!
		 */
		Scanner input = new Scanner(System.in);
		System.out.print("Enter registration number: ");
		String rgn = input.nextLine();
		System.out.print("Enter model year: ");
		int year = input.nextInt();
		input.nextLine();
		System.out.print("Enter weight: ");
		int weight = input.nextInt();
		input.nextLine();
		System.out.print("Enter price: ");
		int price = input.nextInt();
		input.nextLine();
		System.out.print("Enter gas meter(1.0 - 10.0): ");
		double gasMeter = NewScanner.readDouble(0, 10);
		System.out.print("Enter mileage: ");
		int mileage = input.nextInt();
		input.nextLine();
		System.out.println("Enter quality value(0.0 - 1.0): ");
		double qualityValue = NewScanner.readDouble(0, 1);
		System.out.print("Enter status: ");
		String status = NewScanner.readStatus();
		
		Car newCar = null;
		
		if (status.equals("")) {
			newCar = new Car(rgn, year, weight, price, gasMeter, mileage, qualityValue, "Good");
		} else {
			newCar = new Car(rgn, year, weight, price, gasMeter, mileage, qualityValue, status);
		}

		return newCar;
	}

	public Mechanic createMechanic() {

		Scanner input = new Scanner(System.in);
		System.out.println("Enter mechanic's name: ");
		String mechanicName = input.nextLine();

		System.out.println("Enter mechanic type : ");
		String mechanicType = NewScanner.readMechanicType();

		Mechanic newMechanic = new Mechanic(mechanicName, mechanicType);

		/*
		 * TODO: Add logic to this method.
		 * 
		 * 1. read the mechanic's name and type. 2. use that information to create the
		 * mechanic. 3. change the null below to the right reference that you need to
		 * return!!
		 */

		return newMechanic; // <--- change this.
	}

	public void run() {

		int option;
		do {
			printMenuOptions();
			System.out.print(" Type the option number: ");

			option = input.nextInt();
			input.nextLine(); // this skips the enter
								// that the user types after
								// typing the integer option.

			switch (option) {
			case REGISTER_CAR:

				Car newCar = createCar();
				this.cars[registeredCars] = newCar;
				this.registeredCars = this.registeredCars + 1;

				break;

			case PRINT_CARS:
				printAllCars();
				break;

			case PRINT_CAR:
				printOneCar();
				break;

			case PRINT_CHEAPEST_CAR:
				printCheapest();
				break;

			case DRIVE_CAR:
				drive();
				break;

			case FILL_GAS:
				fillGas();
				break;

			case REGISTER_MACHANIC:
				Mechanic newMechanic = createMechanic();
				this.mechanics[registeredMechanics] = newMechanic;
				this.registeredMechanics = this.registeredMechanics + 1;

				break;

			case REPAIR_CAR:
				repairCar();
				break;

			case QUIT:
				System.out.println("Thank you for visiting our Garage. See you soon!");
				System.out.println();
				break;

//			case EDIT_CAR_INFORMATION:
//				editInfo();
//				break;

			default:
				System.out.println("Option " + option + " is not valid.");
				System.out.println();
				break;
			}
		} while (option != QUIT);
	}

	// This method is private because it should be used only by
	// this class since the menu is specific to this main.
	private void printMenuOptions() {
		System.out.println(" === Welcome to DIT042 Garage === ");
		System.out.println(" Choose an option below: ");
		System.out.println(" ");
		System.out.println(" 1. Register a car. ");
		System.out.println(" 2. Print all cars. ");
		System.out.println(" 3. Print a car's information. ");
		System.out.println(" 4. Print the cheapest car's information. ");
		System.out.println(" 5. Test-drive a car ");
		System.out.println(" 6. Fill a car's gas tank ");
		System.out.println(" 7. Hire a mechanic ");
		System.out.println(" 8. Repair a car ");
		System.out.println(" 9. Quit this program. ");
		System.out.println();
	}

	public void printAllCars() {
		// TODO: Add the code for the logic below
		// 1. Iterate through all cars and print each one of them
		// if they are not null!
		// BE CAREFUL! avoid printing nulls by checking:
		// if( car[i] != null ) { ... print it ... }
		for (int i = 0; i < this.registeredCars; i++) {
			System.out.println(cars[i]);

		}
	}

	public void printOneCar() {
		String carRgn = readCarRgn();

		Car foundCar = retrieveCar(carRgn);

		if (foundCar != null) {
			System.out.println(foundCar);
		} else {
			System.out.println("Error: " + carRgn + " is not registered.");
		}

		// TODO: Add the code for the logic below
		// 1. Print the found car, according to assignment's format (Task 2)
		// 2. Remember that the car may not exist, so...
		// print only if the foundCar is NOT null.
	}

	public void printCheapest() {

		System.out.println("Please enter car's classification: ");
		String carClass = input.nextLine();
		Car cheapestCar = null;

		for (int i = 0; i < this.registeredCars; i++) {
			if (cars[i].getClassification().equals(carClass)) {

				if (cheapestCar == null)
					cheapestCar = cars[i];
				if (cars[i].price < cheapestCar.price) {
					cheapestCar = cars[i];
				}
			}
		}

		System.out.println(cheapestCar);

		// TODO: Add the code for the logic below
		// 1. ask for the car's classification
		// 2. Iterate through all cars to get the ones with the classification specified
		// 3. Iterate through all the cars with this classification and find the one
		// with the lowest price
		// 4. Print the found (cheapest) car
		// BE CAREFUL! avoid printing nulls by checking:
		// if( car[i] != null ) { ... print it ... }

	}

	/*
	 * This method only reads a String that here, will be the rgn of a car that you
	 * want to use (for printing, driving, filling gas, etc.)
	 */
	public String readCarRgn() {
		System.out.print("Type the rgn of the car that you want to use: ");
		String carRgn = input.nextLine();
		return carRgn;
	}

	public void drive() {
		String carRgn = readCarRgn();
		Car foundCar = retrieveCar(carRgn);

		if (foundCar != null) {
			System.out.print("Enter amount of miles to be driven: ");
			double amountOfMiles = input.nextDouble();
			input.nextLine();

			foundCar.drive(amountOfMiles);

			System.out.println(carRgn + " new gas level is " + foundCar.gasMeter);
		} else {
			System.out.println(carRgn + " doesn't exist");
		}
		// TODO: write code for the following logic:
		// 1. Read a double value for the amount of miles to be driven.
		// 2. IF the car was really found, drive(i.e. lower the gas level of the car)
		// it.
		// 3. Print the message: "<car_rgn> new gas level is <car_gas_meter>"
		// 4. Remember to not allow changing the gas level to negative values.
	}

	public void fillGas() {

		String carRgn = readCarRgn();
		Car foundCar = retrieveCar(carRgn);

		if (foundCar != null) {
			System.out.print("Enter amount to refill: ");
			double amountOfRefill = input.nextDouble();
			input.nextLine();

			foundCar.fillGas(amountOfRefill);

			System.out.println(carRgn + " new gas level is " + foundCar.gasMeter);
		} else {
			System.out.println("Error: " + carRgn + "is not registered.");
		}

		// TODO: write code for the following logic:
		// 1. Read a double value for the amount of gas to be filled with.
		// 2. IF the car was really found, use the reference to increase the gas level
		// (consider MAX_GAS_LEVEL so the gas level doesn't go beyond it).
		// 3. Print the message: "<car_rgn> new gas level is <car_gas_meter>"
		// 4. Remember to not allow changing the gas level to negative values.

	}

	public void repairCar() {
		String carRgn = readCarRgn();
		Car foundCar = retrieveCar(carRgn);

		if (foundCar != null) {
			Mechanic hiredMechanic = null;

			System.out.print("Enter name of mechanic: ");
			String mechanicName = input.nextLine();

			for (int i = 0; i < this.registeredMechanics; i++) {
				if (mechanics[i].name.equals(mechanicName)) {
					hiredMechanic = mechanics[i];
				}
			}

			if (hiredMechanic != null) {
				hiredMechanic.repair(foundCar);

				System.out.println(
						carRgn + " new status is " + foundCar.status + " and quality value " + foundCar.qualityValue);
			} else {
				System.out.println("Error: Mechanic not found");
			}
		} else {
			System.out.println("Error: Car not found");

		}
		// TODO: write code for the following logic:
		// 1. Read the car rgn and check if it exists.
		// 2. Read the machanic name and check if they exist.
		// 3. Read the machanic type and check if it matches the car classification.
		// 2. IF the car and the mechanic satisfy the conditions, use the reference to
		// change the car status to Good and lower the quality value by 0.1.
		// 3. Print the message: "<car_rgn> new status is <car_status> and quality value
		// <car_quality_value>"
		// 4. Remember to not allow changing the quality value to negative values.
	}

	public static void main(String[] args) {
		GarageMain program = new GarageMain();
		program.run();
	}
}