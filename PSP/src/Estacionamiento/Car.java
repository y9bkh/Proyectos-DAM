package Estacionamiento;

import java.util.Random;

public class Car extends Thread{
	private final int idCoche;
    private final Parking parking;
    private final Random random;
    
    
    public Car(int idCoche, Parking parking) {
    	this.idCoche = idCoche;
    	this.parking = parking;
        this.random = new Random();
    }

    @Override
    public void run() {
    	try {
            System.out.println("Coche " + idCoche + " listo para aparcar");
            parking.aparcar(idCoche);
            int tiempoParking= random.nextInt(5000) + 1000;; // Simulación del tiempo de aparcamiento
            Thread.sleep(tiempoParking);
            parking.dejarParking(idCoche);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
