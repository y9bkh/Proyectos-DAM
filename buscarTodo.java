package segundaEvaluacion;
import java.util.*;
public class buscarTodo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner pt = new Scanner(System.in);
		int[]tabla = new int[5];
		registrar(tabla);
		System.out.print("Introduce que numero quieres que busque su posicion: ");
		int clave=pt.nextInt();
		buscar(tabla,clave);
	}
	
	static int[] registrar (int[] tabla) {
		Scanner pt = new Scanner(System.in);
		for(int posicion=0; posicion<tabla.length; posicion++) {
			System.out.print("Introduce los valores en los registros: ");
			int registro = pt.nextInt();
			tabla[posicion] = registro;
		}
		return tabla;
	}

	static int[] buscar (int[]tabla, int clave) {
		System.out.println();
		boolean funciona = true;
		for(int pos=0; pos<tabla.length; pos++) {
			if(clave==tabla[pos]) {
				System.out.println("El número "+clave+" esta en la posición "+pos);
			}else {
				funciona = false;
				break;
			}
		}
		if(funciona==false) {
			System.out.println("El número "+clave+" introducido no existe en los registros");
		}
		return tabla;
	}
}