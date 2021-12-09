package carPackage;

public class Car {

	String rgn;
	int year;
	int weight;
	int price;
	double gasMeter;
	int mileage;
	double qualityValue;
	String status;
	double maxGasLevel = 10;

	public Car(String rgn, int year, int weight, int price, double gasMeter, int mileage, double qualityValue,
			String status) {
		this.rgn = rgn;
		this.year = year;
		this.weight = weight;
		this.price = price;
		this.gasMeter = gasMeter;
		this.mileage = mileage;
		this.qualityValue = qualityValue;
		this.status = status;
		System.out.println("Car has been created.");
	}

	public String toString() {
		final String END_OF_LINE = System.lineSeparator();
		String carDetails = this.rgn + ": " + getClassification() + " car" + END_OF_LINE;
		carDetails += "Model Year: " + this.year + END_OF_LINE;
		carDetails += "Weight: " + this.weight + " kg" + END_OF_LINE;
		carDetails += "Price: " + this.price + " kr" + END_OF_LINE;
		carDetails += "Gas Level: " + this.gasMeter + " gallons " + String.format("%.2f", this.getGasMeter()) + " L"
				+ END_OF_LINE;
		carDetails += "Mileage: " + this.mileage + " miles " + this.getMileage() + " km" + END_OF_LINE;
		carDetails += "Quality Value: " + this.qualityValue + END_OF_LINE;
		carDetails += "Status: " + this.status + END_OF_LINE;
		return carDetails;
	}

	public double getGasMeter() {
		// Gas meter in liters
		return this.gasMeter * 3.78;
	}

	public void drive(double amountOfMiles) {
		double gasConsumed = amountOfMiles / 25;

		if (amountOfMiles >= 0) {
			// check if gas that will be consumed is greater than gasMeter
			if (this.gasMeter - gasConsumed >= 0) {
				this.mileage = (int) (mileage + amountOfMiles);
				this.gasMeter = gasMeter - gasConsumed;
			} else {
				amountOfMiles = this.gasMeter * 25;
				this.gasMeter = 0;
				this.mileage = (int) (mileage + amountOfMiles);
				System.err.println("Error when driving car " + this.rgn + ". Gas tank is empty");
			}
		} else {
			System.err.println("Error when driving car " + this.rgn + " with gas. Amount of miles cannot be negative");
		}
	}

	public void fillGas(double amountOfRefill) {

		if (amountOfRefill >= 0) {
			if (amountOfRefill + gasMeter <= maxGasLevel) {
				this.gasMeter = gasMeter + amountOfRefill;
			} else {
				this.gasMeter = maxGasLevel;
				System.err.println("Error when filling car " + this.rgn + " with gas. Gas tank is full");
			}
		} else {
			System.err
					.println("Error when filling car " + this.rgn + " with gas. Amount of gallons cannot be negative");
		}
	}

	public String getRgn() {
		return this.rgn;
	}

	public int getMileage() {
		// Mileage in kilometers
		return (int) (this.mileage * 1.6);
	}

	public String getClassification() {
		String classification = "";
		if (this.weight <= 1600)
			classification = "Light";
		if (this.weight >= 1600)
			classification = "Medium";
		if (this.weight >= 2300)
			classification = "Heavy";

		return classification;

	}

}
