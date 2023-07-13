package utilities;

import entities.Airport;
import entities.Airplane;
import interfaces.interfacesUI.ManageAirport;
import interfaces.interfacesUI.ShowMenu;

import java.util.Scanner;

public class UI implements ShowMenu, ManageAirport {
	private static final Scanner SC = new Scanner(System.in);
	@Override
	public String showMenu() {
		return """
				\n1- Create Airplane
				2- Edit Airplane
				3- Delete Airplane
				4- Assign Boarding Gate
				5- Show Airplanes
				6- Show Boarding Gates
				7- Exit
				Option:""";
	}
	@Override
	public void manageAirport() {
		Airport airport = new Airport(5);
		Airplane airplane = new Airplane();

		String op;
		boolean flag = true;
		do {
			System.out.print(showMenu());
			op = SC.next();
			System.out.println();
			switch (op) {
				case "1" -> airport.addAircraft((Airplane) airplane.createAircraft());
				case "2" -> airport.editAircraft();
				case "3" -> airport.deleteAircraft();
				case "4" -> airport.assignGate();
				case "5" -> airport.showAircraft();
				case "6" -> airport.showBoardingGates();
				case "7" -> flag = false;
				default -> System.out.println("The option is not correct");
			}
		} while(flag);
		System.out.println("Thank you for using our system.");
	}
}
