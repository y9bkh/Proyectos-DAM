package mesas;

import Estacionamiento.Car;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int capacidadMaximaPatas=6;
		for(int i = 1; i <= capacidadMaximaPatas; i++) {
			ProductorPatas productor = new ProductorPatas(8);
			try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
			productor.start();
		}
        
	}

}
