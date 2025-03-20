package _1er_Trimestre;

import java.util.ArrayList;
import java.util.Arrays;

public class RegistroEstudiantes {

	// CREO UN ARRAYLIST QUE ME GUARDARA TODOS LOS ESTUDIANTES QUE INTRODUZCA
	static ArrayList<Estudiante> Alumnos = new ArrayList<Estudiante>();

	public static void main(String args[]) {

		System.out.println("Bienvenido");

		// CREO LOS ESTUDIANTES CON EL MÉTODO agregarEstudiante() QUE SU f(x) ES CREAR
		// ESTUDIANTES
		agregarEstudiante("Younes", 22, "01");
		agregarEstudiante("David", 22, "0001");

		mostrarEstudiantes();
		eliminarEstudiante("01");
		mostrarEstudiantes();

		/*
		 * Alumnos.add(new Estudiante("Younes", 22, "01")); Alumnos.add(new
		 * Estudiante("David", 21, "0001"));
		 */
	}

	// Crea el estudiante aunque se pueda hacerlo directamente desde el main pero me
	// lo pide el ejercicio
	public static void agregarEstudiante(String nombre, int edad, String matricula) {
		Estudiante e = new Estudiante(nombre, edad, matricula); // CREAMOS EL ESTUDIANTE PASANDOLE LOS PARAMETROS
																// NECESARIOS PARA SU CREACIÓN
		Alumnos.add(e); // LUEGO LO AÑADIMOS A NUESTRA LISTA
	}

	public static void buscarEstudiante(String matriculaBuscada) {
		boolean encontrado = false; // CREAMOS UN BOLEANO PARA VERIFICAR LA BÚSQUEDA DE LA MATRICULA
		for (Estudiante e : Alumnos) {
			if (e.getMatricula().equals(matriculaBuscada)) { // SI EL ESTUDIANTE EN ESA POSICIÓN TIENE LA MISMA
																// matriculaBuscada SE IMPRIME
				System.out.println("[Nombre: " + e.getNombre() + "]");
				System.out.println("[Edad: " + e.getEdad() + "]");
				System.out.println("[Matricula: " + e.getMatricula() + "]");
				System.out.println();
				encontrado = true; // Y SE ADVIERTE QUE SE HA ENCONTRADO
				break; // SALIR DEL BUCLE, SI USO system.exit(0) PARO TODO EL PROGRAMA DE GOLPE AL
						// ENCONTRARLO
			}
		}
		if (!encontrado) { // SI NO LO ENCUENTRO IMPRIMO ESTO
			System.out.println("El estudiante con matrícula " + matriculaBuscada + " no existe.");
		}
	}

	public static void eliminarEstudiante(String matriculaAeliminar) {
		boolean encontrado = false;
		for (Estudiante e : Alumnos) {
			if (e.getMatricula().equals(matriculaAeliminar)) {
				Alumnos.remove(e); // ME BORRA EL ESTUDIANTE EN ESE INDICE, SI QUISIERA QUE ME BORRARA ALGO EN
									// CONTRETO DE ESE ESTUDIANTE USARÍA EL .indexOf()
				encontrado = true;
				break;
			}
		}

		if (!encontrado) {
			System.out.println("El estudiante con matrícula " + matriculaAeliminar + " no existe.");
		}
	}

	// Muestra información del estudiante
	public static void mostrarEstudiantes() {
		for (Estudiante e : Alumnos) {
			System.out.println("[Nombre: " + e.getNombre() + "]");
			System.out.println("[Edad: " + e.getEdad() + "]");
			System.out.println("[Matricula: " + e.getMatricula() + "]");
			System.out.println();
		}

	}
}
