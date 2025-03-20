package paquetespruebas;

public class CuentaCorriente {
	String dni; 	// del titular . sin modificador, sólo visible para clases vecinas
	public String nombre; //del titular. público para clases vecinas
	private double saldo;   //público para clases vecinas
	static private String nombreBanco = "Banco ficticio del mundo"; //valor por defecto	
	
	Gestor g; //AÑADO LA CLASE GESTOR 
	
	void setGestor(Gestor gNEW) {
		g = gNEW;
	}
	
	
	static void setBanco (String nuevoNombre) {
		nombreBanco = nuevoNombre;
	}
	static String getBanco() {
		return nombreBanco;
	}
	
	CuentaCorriente(String dni, String nombre){ //constructor)
		this.dni = dni;
		this.nombre=nombre;
		saldo=0;
	}	
	CuentaCorriente (String dni, double saldo){
		this.dni = dni;
		this.saldo = saldo;
		this.nombre = "Sin asignar";  
	}
	
	CuentaCorriente (String dni, String nombre, double saldo){
		this.dni = dni;
		this.saldo = saldo;
		this.nombre = nombre;
	}	
	
	CuentaCorriente (String dni, String nombre, Gestor g){
		this.dni = dni;
		this.nombre = nombre;
		this.g = g;
	}	
	
	boolean sacardinero (double cant) {  //sacar dinero de la cuenta
		boolean operacionPosible;
		if (saldo >=cant) {
			saldo=saldo-cant;
			operacionPosible = true;
			
		}
		else {
			System.out.println("No hay dinero suficiente");
			operacionPosible = false;
		}
		return operacionPosible;
	}
	

	void ingresar (double cant) {
		saldo = saldo + cant;
	}	

	void mostrarInformacion() {//muetra el estado de la cuenta corriente
		System.out.println();
		System.out.println();
		System.out.println("Cliente ");
		System.out.println("-----------------------------");
		System.out.println("Nombre: "+ nombre);
		System.out.println("Dni: "+ dni);
		System.out.println("Saldo: "+ saldo + " €");
		System.out.println("Banco: "+ nombreBanco+"\n");
		if(g==null) {
			System.out.println("Cuenta sin Gestor ");
		}else {
			System.out.println("Informacion del Gestor ");
			g.mostraInfo();
		}
	}
}
