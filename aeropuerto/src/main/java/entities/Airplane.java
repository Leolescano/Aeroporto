package entities;

import interfaces.validationinterfaces.ValidationCapacity;
import interfaces.validationinterfaces.ValidationNumberRegistration;
import interfaces.validationinterfaces.ValidationStates;
import lombok.*;

import java.util.InputMismatchException;
import java.util.Scanner;

import static entities.StatesAircraft.getStateByNumber;

//Interfaz de usuario (UI):
//La aplicación debe contar con una interfaz de usuario intuitiva y fácil de usar.
//Debe proporcionar opciones para agregar, editar y eliminar aviones.
//Debe mostrar información relevante sobre los aviones, como su número de registro, aerolínea, capacidad, estado, etc.
//Debe permitir la asignación de aviones a diferentes puertas de embarque.
//
//Gestión de aviones:
//La aplicación debe permitir la creación, edición y eliminación de aviones.
//Cada avión debe tener atributos como número de registro, aerolínea, capacidad de pasajeros, estado (disponible, en mantenimiento, en vuelo, etc.).
//Los aviones deben poder ser asignados a una puerta de embarque específica.
//Gestión de puertas de embarque:
//La aplicación debe permitir la asignación de aviones a puertas de embarque disponibles.
//Debe mostrar la disponibilidad de las puertas de embarque y permitir la asignación de aviones a puertas específicas.
//Debe proporcionar una vista clara de las puertas de embarque ocupadas y disponibles.
//Principios SOLID y buenas prácticas:
//La solución debe seguir los principios SOLID de programación orientada a objetos.
//Debe utilizar buenas prácticas de programación, como nomenclatura clara, modularidad, cohesión y bajo acoplamiento.
//Debe emplear patrones de diseño adecuados para una estructura eficiente y mantenible del código.


@NoArgsConstructor
public class Airplane extends Aircraft implements ValidationNumberRegistration, ValidationCapacity, ValidationStates {

	public final Scanner SC = new Scanner(System.in);
	public Airplane(String model, String numberRegistration, String airline, int capacity, StatesAircraft state) {
		super(model, numberRegistration, airline, capacity, state);
	}

	@Override
	public Aircraft createAircraft() {

		System.out.print("Enter the model of the airplane: ");
		String model = this.SC.nextLine();

		// Verificando que el número de registro sea un número y tenga exactamente 4 digitos.
        String numberRegistration = validateNumberRegistration();

		System.out.print("Enter the name of the airline: ");
		String airline = this.SC.nextLine();

		// Verificando que la capacidad sea un número y sea mayor que 0.
		int capacity = validateCapacity();

		// Verificando que el estado del avión sea un Enum válido.
		StatesAircraft state = validateState();

		return new Airplane(model, numberRegistration, airline, capacity, state);
		//return new Airplane(null, numberRegistration, null, 120, StatesAircraft.valueOf("AVAILABLE"));
	}

	@Override
	public String validateNumberRegistration(){
		boolean flag = true;
		while(true) {
			try {
				System.out.print("Enter the airplane registration number with 4 digits: ");
				String numberRegistration = this.SC.nextLine();
				Integer.parseInt(numberRegistration);
				if (numberRegistration.length() == 4) {
					return numberRegistration;
				} else {
					System.out.println("Your registration number does not contain 4 digits, please enter it again.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Your registration number contains an invalid digit.");
			}
		}
	}

	@Override
	public int validateCapacity(){
		boolean flag = true;
		while (flag) {
			try {
				System.out.print("Enter the capacity of the airplane: ");
				capacity = this.SC.nextInt();
				if (capacity > 0) {
					flag = false;
				} else {
					System.out.println("The airplane capacity must be greater than 0.");
				}
				this.SC.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("You need to enter a number");
				this.SC.nextLine();
			}
		}
		return capacity;
	}

	@Override
	public StatesAircraft validateState(){
		StatesAircraft state = null;
		while (state == null) {
			try {
				System.out.println("Enter the airplane status");
				int cont = 1;
				for (StatesAircraft value : StatesAircraft.values()) {
					System.out.println(cont + "- " + value);
					cont++;
				}
				String op = this.SC.nextLine();
				state = getStateByNumber(Integer.parseInt(op));
			} catch (NumberFormatException e) {
				System.out.println("You must enter a number from 1 to " +
						StatesAircraft.values().length + " as an option.");
			}
		}
		return state;
	}

	@Override
	public String toString() {
		return """
			------------------------------
			Model: %s                  
			Registration Number: %s     
			Airline: %s                 
			Capacity: %d                
			State: %s                   
			------------------------------
			""".formatted(model, numberRegistration, airline, capacity, state);
	}
}
