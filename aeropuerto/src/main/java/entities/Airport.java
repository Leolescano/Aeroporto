package entities;

import exception.GatesException;
import interfaces.interfacesairport.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Airport implements ShowAircraft, AddAircraft, AssignGate, SearchAircraft, DeleteAircraft {
	private List<BoardingGate> boardingGates = new ArrayList<>();
	private List<Aircraft> aircraftList = new ArrayList<>();
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
	@Override
	public void showAircraft(){
		if (this.aircraftList.size() > 0) {
			for (Aircraft aircraft :this.aircraftList) {
				System.out.println(aircraft);
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
	@Override
	public void addAircraft(Aircraft aircraft) {
		this.aircraftList.add(aircraft);
	}
	@Override
	public Aircraft searchAircraft(String searchCriteria) {
		for (Aircraft aircraft :this.aircraftList) {
			if (aircraft.getNumberRegistration().equals(searchCriteria)) {
				return aircraft;
			}
		}
		return null;
	}
	public void editAircraft() {
		String op;
		if (this.aircraftList.size() > 0) {
			System.out.print("Enter the registration number of the airplane to edit ");
			String numberRegistration = SC.nextLine();
			Aircraft aircraft = searchAircraft(numberRegistration);
			if (aircraft != null) {
				System.out.println("""
						1- Change Airline
						2- Change Capacity
						3- Change State
						4- Back to previous menu""");
				op = SC.nextLine();



			} else {
				System.out.println("The airplane is not on the list");
			}
		} else {
			System.out.println("There are no airplanes.");
		}
	}
	@Override
	public void deleteAircraft() {
		if (this.aircraftList.size() > 0) {
			System.out.print("Enter the registration number of the airplane to delete ");
			String numberRegistration = SC.nextLine();
			Airplane airPlane = (Airplane) searchAircraft(numberRegistration);
			if (airPlane != null) {
				this.aircraftList.remove(airPlane);
				System.out.println("The airplane has been successfully deleted.");
			} else {
				System.out.println("The airplane is not on the list");
			}
		} else {
			System.out.println("There are no airplanes.");
		}
	}
	@Override
	public void assignGate() {
		if (this.aircraftList.size() > 0) {
			System.out.print("Please enter the registration number of the airplane ");
			String numberRegistration = SC.nextLine();
			Aircraft aircraft = searchAircraft(numberRegistration);
			if (aircraft != null) {
				try {
					System.out.print("Enter the boarding gate ");
					int numberGate = SC.nextInt();
					SC.nextLine();
					if ((numberGate > this.boardingGates.size()) || (numberGate < 1) ) {
						throw new GatesException("That boarding gate does not exist");
					}
					numberGate--;
					for (BoardingGate gate : this.boardingGates) {
						if (gate.getAircraft() != null && gate.getAircraft().equals(aircraft)) {
							throw new GatesException("This plane is already at the gate " + gate.getGateNumber());
						}
					}
					BoardingGate boardingGate = this.boardingGates.get(numberGate);
					if(!boardingGate.isStatus()) {
						boardingGate.setStatus(true);
						boardingGate.setAircraft(aircraft);
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
				System.out.println("The airplane does not exist");
			}
		} else {
			System.out.println("There are no airplanes.");
		}
	}
}
