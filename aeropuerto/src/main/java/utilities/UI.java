package utilities;

import entities.Airport;
import entities.Plane;

import java.util.Scanner;

public class UI {
	private static final Scanner SC = new Scanner(System.in);
	public static String showMenu() {
		return """
				\n1- Create Plane
				2- Edit Plane
				3- Delete Plane
				4- Assign Boarding Gate
				5- Show Planes
				6- Show Boarding Gates
				7- Exit""";
	}
	public static void usar() {
		Airport airport = new Airport(5);
		String op;
		boolean flag = true;
		do {
			System.out.println(showMenu());
			System.out.print(":");
			op = SC.next();
			switch (op) {
				case "1" :
					airport.addPlane(Plane.createPlane());
				    break;
				case "2" :
					airport.editPlane();
					break;
				case "3" :
					airport.deletePlane();
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
				case "7" :
					flag = false;
					break;
				default:
					System.out.println("The option is not correct");
			}
		} while(flag);
		System.out.println("Thank you for using our system.");
	}
}
