package segundaEvaluacion;

import java.util.*;

public class espaciosBlanco {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner pt = new Scanner(System.in);
		System.out.print("Introduce un texto, el programa contara los espacios: ");
		String us = pt.nextLine(); //Me recoge todo lo que ponga

		int contEspacios = 0;
		for (int pos = 0; pos < us.length(); pos++) { // Recorre la frase
			if (Character.isSpaceChar(us.charAt(pos))) { // Verifica si el caracter es un espacio blanco, si el caracter
														// de la pos(x) devuelve un true
				contEspacios++;							// Incrementa el contador
			}
		}

		System.out.print("La frase integrada tiene " + contEspacios + " espacios");
	}

}
