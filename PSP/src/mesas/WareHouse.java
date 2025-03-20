package mesas;

public class WareHouse {

	private final int MAX_LEGS = 6;
	private final int MAX_TABLETOPS = 2;
	
	private int contadorPatas = 0;
	private int contadorTableros = 0;

	public synchronized void storeLeg() throws InterruptedException {
		while(contadorPatas == MAX_LEGS) {
			System.out.println("El almacén ha alcanzado el límite de patas. Esperando a que haya espacio...");
			wait();
		}
		contadorPatas++;
		System.out.println("Se ha añadido 1 pata. \nTotal de patas: " + contadorPatas);
		
		notifyAll();
	}

	public synchronized void storeTabletop() throws InterruptedException {
		while(contadorTableros == MAX_TABLETOPS) {
			System.out.println("El almacén ha alcanzado el límite de tableros. Esperando a que haya espacio...");
			wait();
		}
		contadorTableros++;
		System.out.println("Se ha añadido 1 tablero. \nTotal de tableros: " + contadorTableros);
		
		notifyAll();
	}

	public synchronized void buildTable() throws InterruptedException {
		while(contadorTableros<4 && contadorPatas <1) {
			System.out.println("No tenemos suficientes recursos para fabricar la mesa...");
			wait();
		}
		contadorTableros -=4; //Cogemos los recursos que nos hace falta para construir la mesa.
		contadorPatas -=1; //Cogemos los recursos para construir la mesa.
		
		System.out.println("Mesa construida... Tienes "+contadorTableros+" tableros y "+contadorPatas+" patas.");
		
		notifyAll();
	}

}
