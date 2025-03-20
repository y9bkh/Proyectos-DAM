package paquetespruebas;

public class Gestor {

	public String nombre;
	private int telefono;
	double importe = 10000;

	Gestor(String nombre, int telefono) {
		this.nombre = nombre;
		this.telefono = telefono;
	}

	Gestor(String nombre, int telefono, double importe) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.importe = importe;
	}

	int getTfno() {
		return telefono; // Al ser telefono privado permite consultar el telefono de un gestor
	}

	void mostraInfo() {
		System.out.println("-----------------------------");
		System.out.println("Nombre: " + nombre);
		System.out.println("Telefono: " + telefono);
		System.out.println("Importe: " + importe + " €");
	}
}
