package carPackage;

public class Mechanic {
	String name;
	String type;

	public Mechanic(String name, String type) {
		this.name = name;
		// Mechanic type is either Light, Medium, Heavy
		this.type = type;
	}

	public void repair(Car car) {
		// check if mechanic type matches car type AND car status is either Bad or Ok
		// change car status to good
		// lower car quality by 0.1 unless it's 0

		if (this.type.equals(car.getClassification())) {
				car.status ="good";
				if (car.qualityValue > 0.0) {
					car.qualityValue = car.qualityValue -0.1;
				} 
				System.out.println(car.rgn + " successfully repaired by " + this.name + " ." + car.rgn + " quality is "
						+ car.qualityValue);
		} else {
				System.out.println(this.name + " could not repair " + car.rgn);

			}

		}

	}
