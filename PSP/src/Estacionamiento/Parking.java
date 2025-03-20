package Estacionamiento;

public class Parking {
	
	private int espacioTotal;
	private int espacioDisponible = espacioTotal;
	
	public Parking(int espacioTotal) {
		this.espacioTotal = espacioTotal;
		this.espacioDisponible = espacioTotal;
	}
	
	public synchronized void aparcar(int idCoche) throws InterruptedException {
		while(espacioDisponible == 0) {
			System.out.println("Estacionamiento lleno, el coche "+idCoche+" debe esperar");
			wait();
		}
		espacioDisponible--;
        System.out.println("Coche " + idCoche + " ha aparcado");
	}
	
	public synchronized void dejarParking(int idCoche) {
		espacioDisponible++;
		System.out.println("Coche "+ idCoche +" ha dejado un espacio libre");
		notifyAll();
	}
	
	
	
}
