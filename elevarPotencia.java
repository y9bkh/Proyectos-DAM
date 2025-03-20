import java.util.Scanner;
public class elevarpotencia {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner pt = new Scanner(System.in);
		System.out.print("Introduce el exponente: ");
		int us_exponente = pt.nextInt();
		System.out.print("Introduce la potencia: ");
		int us_potencia = pt.nextInt();		
		int resultado = elevarPotencia(us_exponente, us_potencia);
		System.out.print(resultado);
	}
	
	static int elevarPotencia(int exponente, int potencia){
	int resultado = 0;
	if(potencia==0) {
		resultado=1;
	}else {
		resultado = exponente*elevarPotencia(exponente,potencia-1);
	}
	return resultado;
	}
}
