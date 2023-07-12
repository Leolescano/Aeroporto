package entities;

import exception.GatesException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Airport {
	private List<BoardingGate> boardingGates = new ArrayList<>();
	private List<Plane> planeList = new ArrayList<>();
	private final Scanner SC = new Scanner(System.in);

	public Airport(int numberGates) {
		if (numberGates > 0) {
			for (int i = 1; i <= numberGates ; i++) {
				this.boardingGates.add(new BoardingGate(i));
			}
		} else {
			this.boardingGates.add(new BoardingGate(1));
		}
	}
	public void showPlaneList(){
		if (this.planeList.size() > 0) {
			for (Plane plane:this.planeList) {
				System.out.println(plane);
			}
		} else {
			System.out.println("There are no airplanes.");
		}
	}
	public void showBoardingGates(){
		for (BoardingGate gate: this.boardingGates) {
			System.out.println(gate);
		}
	}
	public void addPlane(Plane plane) {
		this.planeList.add(plane);
	}
	public Plane searchPlane(String numberRegistration) {
		for (Plane plane:this.planeList) {
			if (plane.getNumberRegistration().equals(numberRegistration)) {
				return plane;
			}
		}
		return null;
	}
	public void editPlane() {
		String op;
		if (this.planeList.size() > 0) {
			System.out.print("Enter the registration number of the aircraft to edit ");
			String numberRegistration = SC.nextLine();
			Plane plane = searchPlane(numberRegistration);
			if (plane != null) {
				System.out.println("""
						1- Change Airline
						2- Change Capacity
						3- Change State
						4- Back to previous menu""");
				op = SC.nextLine();



			} else {
				System.out.println("The plane is not on the list");
			}
		} else {
			System.out.println("There are no airplanes.");
		}
	}
	public void deletePlane() {
		if (this.planeList.size() > 0) {
			System.out.print("Enter the registration number of the airplane to delete ");
			String numberRegistration = SC.nextLine();
			Plane plane = searchPlane(numberRegistration);
			if (plane != null) {
				this.planeList.remove(plane);
				System.out.println("The airplane has been successfully deleted.");
			} else {
				System.out.println("The plane is not on the list");
			}
		} else {
			System.out.println("There are no airplanes.");
		}
	}
	public void assignGate() {
		if (this.planeList.size() > 0) {
			System.out.print("Please enter the registration number of the airplane ");
			String numberRegistration = SC.nextLine();
			Plane plane = searchPlane(numberRegistration);
			if (plane != null) {
				try {
					System.out.print("Ingrese la puerta ");
					int numberGate = SC.nextInt();
					SC.nextLine();
					if ((numberGate > this.boardingGates.size()) || (numberGate < 1) ) {
						throw new GatesException("That boarding gate does not exist");
					}
					numberGate--;
					for (BoardingGate gate : this.boardingGates) {
						if (gate.getPlane() != null && gate.getPlane().equals(plane)) {
							throw new GatesException("This plane is already at the gate " + gate.getGateNumber());
						}
					}
					BoardingGate boardingGate = this.boardingGates.get(numberGate);
					if(!boardingGate.isStatus()) {
						boardingGate.setStatus(true);
						boardingGate.setPlane(plane);
						this.boardingGates.set(numberGate, boardingGate);
						System.out.println("Update of Boarding Gate " + boardingGate.getGateNumber() + " for Status In use\n");
					} else {
						throw new GatesException("The boarding gate " + (numberGate + 1) + " is in use");
					}
				} catch (InputMismatchException e) {
					System.out.println("It needs to be a number.");
				}
				catch (GatesException e) {
					System.out.println(e.getMessage());
				}
			} else {
				System.out.println("The plane does not exist");
			}
		} else {
			System.out.println("There are no airplanes.");
		}
	}
}
