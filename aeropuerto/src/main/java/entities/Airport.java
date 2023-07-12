package entities;

import exception.GatesException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Airport {
	private List<BoardingGate> boardingGates = new ArrayList<>();
	private List<Plane> planeList = new ArrayList<>();
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
		for (Plane plane:this.planeList) {
			System.out.println(plane);
		}
	}
	public void showBoardingGates(){
		System.out.println("Gates: ");
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

	public void editPlane(String numberRegistration) {
		Plane plane = searchPlane(numberRegistration);
		if (plane != null) {
			Scanner sc = new Scanner(System.in);
			System.out.println("""
						1- Change airline
						2- Change capacity
						3- Change state
						4- Back to previous menu""");
		} else {
			System.out.println("The plane is not on the list");
		}
	}

	public void assignGate() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el registro del avion");
		String numberRegistration = sc.nextLine();
		Plane plane = searchPlane(numberRegistration);
		if (plane != null) {
			try {
				System.out.println("Ingrese la puerta");
				int numberGate = sc.nextInt();
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
				if(!boardingGate.isEnabled()) {
					boardingGate.setEnabled(true);
					boardingGate.setPlane(plane);
					this.boardingGates.set(numberGate, boardingGate);
					System.out.println("Update of Boarding Gate " + boardingGate.getGateNumber() + " for Status In use\n");
				} else {
					throw new GatesException("The boarding gate" + (numberGate + 1) + " is in use");
				}
			} catch (InputMismatchException e) {
				System.out.println("Necesita ser un numero");
			}
			catch (GatesException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("The plane does not exist");
		}
	}
}
