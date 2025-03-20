package Estacionamiento;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int espacioTotal = 6;
		int coches = 7;
		
		Parking parking = new Parking(espacioTotal);
		Random random = new Random();
		
		for(int i = 1; i <= coches; i++) {
			Car car = new Car(i, parking);
			try {
                int retraso = random.nextInt(1500) + 500; // Retrasamos la salida de info para que la info sea más gradual.
                Thread.sleep(retraso);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            car.start();
		}
	}
}
