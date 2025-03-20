package mesas;

public class ProductorPatas extends Thread {
	private int patas;
	private int contadorTableros;

	ProductorPatas(int patas) {
		this.patas = patas;
	}

	public synchronized void producirPatas(int MAX_TABLETOPS) throws InterruptedException {
		while (contadorTableros == 6) {
			System.out.println("El almacén ha alcanzado el límite de patas. Esperando a que haya espacio...");
			wait();
		}
		contadorTableros++;
		System.out.println("Se ha añadido 1 tablero. \nTotal de tableros: " + contadorTableros);

		notifyAll();
	}

	public void run() {
		while(true) {
			try {
				producirPatas(8);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
