package _1er_Trimestre;

import java.util.Scanner;

public class Calculadora {

	private static boolean isTimeUp = false; // Variable compartida entre los hilos que nos indica cuando se termina el tiempo.

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculadora c = new Calculadora();
		Contador cont = c.new Contador();
		CalculosAleatorios cal = c.new CalculosAleatorios();
		cont.start();
		cal.start();
		
	}
	
	public class CalculosAleatorios extends Thread {
		Scanner pt = new Scanner(System.in);
		public void run() {
			int puntuacion = 0;
			
			while(!isTimeUp) {
				
				int num1 = (int)(Math.random()*101); //Crea numeros aleatorios desde 0-100
				int num2 = (int)(Math.random()*101);//Crea numeros aleatorios desde 0-100
				int suma = num1+num2; //Resultado de la suma de estos
				System.out.print("La suma de "+ num1 +"+"+ num2+ "es ");
				int res = pt.nextInt(); //Recoge la respuesta por teclado del usaurio
				
				if(res == suma) {
					System.out.println("Respuesta correcta!!!");
					puntuacion = puntuacion + 1;
				}else {
					System.out.println("Respuesta incorrecta!!!");
				}
				
				if (isTimeUp) {
                    System.out.println("Tiempo agotado. Puntuación obtenida: " + puntuacion);
                    break;
                }
				
			}
		}
	}
	
	public class Contador extends Thread {
		public void run() {
			for (int contador = 10 ; contador>0; contador--) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			// Cuando el contador llegue a 0, se establece isTimeUp como true
            isTimeUp = true;
		}
	}
	
	

}
