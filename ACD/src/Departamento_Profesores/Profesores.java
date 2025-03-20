package Departamento_Profesores;

public class Profesores {
	private int id;
	private String nombre;
	private String Apellido;
	private String asignatura;
	private int edad;
	private String correo;
	
	Profesores(String nombre, String apellido, String asignatura, int edad, String correo) {
		this.nombre = nombre;
		this.Apellido = apellido;
		this.asignatura = asignatura;
		this.edad = edad;
		this.correo = correo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		this.Apellido = apellido;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	
}
