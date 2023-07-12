package entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.InputMismatchException;
import java.util.Scanner;

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
@AllArgsConstructor
@EqualsAndHashCode
public class Plane {

	@Getter @Setter
	private String model;

	@Getter @Setter
	private String numberRegistration;

	@Getter @Setter
	private String airline;

	@Getter @Setter
	private int capacity;

	@Getter @Setter
	private StatesPlane state;

	public static Plane createPlane() {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		int capacity = 0;
		String state = "";

		System.out.print("Enter the model of the airplane: ");
		String model = sc.nextLine();
		System.out.print("Enter the registration number of the airplane: ");
		String numberRegistration = sc.nextLine();
		System.out.print("Enter the name of the airline: ");
		String airline = sc.nextLine();
		while (flag) {
			try {
				System.out.print("Enter the capacity of the airplane: ");
				capacity = sc.nextInt();
				if (capacity > 0) {
					flag = false;
				} else {
					System.out.println("The airplane capacity must be greater than 0.");
				}
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("You need to enter a number");
				sc.nextLine();
			}
		}
		while (state.equals("")) {
			System.out.println("Enter the airplane status");
			System.out.println("""
				1- AVAILABLE
				2- MAINTENANCE
				3- FLYING""");
			String op = sc.nextLine();
			state = op.equals("1") ?
					"AVAILABLE" : op.equals("2")
						? "MAINTENANCE" : op.equals("3")
							? "FLYING" : "";
		}
		System.out.println("The airplane was successfully created");
		return new Plane(model, numberRegistration, airline, capacity, StatesPlane.valueOf(state));
	}
	@Override
	public String toString() {
		return "Plane:\n" +
		"Model: " + this.model + "\n" +
		"Registration Number: " + this.numberRegistration + "\n" +
	    "Airline: " + this.airline + "\n" +
		"Capacity: " + this.capacity + " passengers \n" +
		"State: " + this.state;
	}
}
