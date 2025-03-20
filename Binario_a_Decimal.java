import java.util.Scanner;
public class binario_a_decimal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner pt = new Scanner(System.in);
		System.out.print("Ingrese un número binario: ");
		String binario = pt.next();
		int decimal = conversarAdecimal(binario);	
		System.out.print("Tu número ("+binario+") en decimal equivale a "+decimal);
	}
	
	static int conversarAdecimal(String binario) {
		int longitud= binario.length();
		int decimal = 0;
		for(int contador = 0; contador < longitud; contador++) {
			int digito = ((binario.charAt(longitud -1 -contador))-48);
			decimal += digito * potencia(2,contador);
		}
		return(decimal);
	}
	static int potencia(int base, int exponente) {
		int aux = 1;
		for(int contador = 0; contador < exponente; contador++) {
			aux = aux*base;
		}
		return(aux);
	}
}
