package utilities;

import entities.Airport;
import entities.Plane;

import java.util.Scanner;

public class InterfaceUssers {
	private static final Scanner sc = new Scanner(System.in);
	public static String showIterfaceUssers() {
		return """
				1- Create Plane
				2- Edit Plane
				3- Delete Plane
				4- Assign Boarding Gate
				5- Show Planes
				6- Show Boarding Gates
				7- Exit""";
	}
	public static void usar() {
		String op;
		String numberRegistration;
		Airport airport = new Airport(5);
		do {
			System.out.println(showIterfaceUssers());
			op = sc.next();
			switch (op) {
				case "1" :
					airport.addPlane(Plane.createPlane());
				    break;
				case "2" :
					System.out.print("Enter the aircraft registration number");
					numberRegistration = sc.nextLine();
					airport.editPlane(numberRegistration);
					break;
				case "4" :
					airport.assignGate();
					break;
				case "5" :
					airport.showPlaneList();
					break;
				case "6" :
					airport.showBoardingGates();
					break;
				default:
					System.out.println("The option is not correct");
			}
		} while(!op.equals("7"));
	}

}
