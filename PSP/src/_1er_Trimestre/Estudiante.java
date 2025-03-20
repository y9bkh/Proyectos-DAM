package _1er_Trimestre;

import java.util.ArrayList;

/**
 * 
 */
public class Estudiante {
	private String nombre;
	private int edad;
	private String matricula;
	
	public Estudiante(String nombre, int edad, String matricula) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.matricula = matricula;
	} 
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
}