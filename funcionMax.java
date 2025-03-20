import java.util.*;
public class funcionMax {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner pt = new Scanner(System.in);
		System.out.print("Solicita los registros que desea: ");
		int registros = pt.nextInt();
		int tabla[];
		tabla = new int[registros];
		

        System.out.println("------------------------------------------------------");
        System.out.println("Programa que localiza el numero mayor en una tabla");
        System.out.println("------------------------------------------------------");
        //CREA NUMEROS ALEATORIOS PARA LA TABLA:
        for(int pos=0;pos<registros;pos++) {
    		System.out.print("Introduce numeros: ");
    		int intro_us = pt.nextInt();
    		tabla[pos]= intro_us; //	<- Introduce los valores en las tablas.
    		}
        
        //IMPRIME LA TABLA:
        System.out.println("La tabla es: ");
        System.out.println(Arrays.toString(tabla));

        Arrays.sort(tabla); // <- Ordena la tabla
        
        //NOS INFORMA EL VALOR MAXIMO DE LA TABLA:
        System.out.println("El numero mas grande es: "+devolverMax(tabla));
    }
	
	//DEVUELVE EL MAXIMO VALOR:
	static int devolverMax(int tabla[]){
		int maximo = tabla[tabla.length-1];
		return maximo;
	}
    
}
