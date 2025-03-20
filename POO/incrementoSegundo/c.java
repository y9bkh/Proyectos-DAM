package horaCualquiera;
import java.util.*;
public class fMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner pt = new Scanner(System.in);
		
		System.out.print("Introduce horas: ");
		int hora = pt.nextByte();	//USAMOS EL BYTE PARA QUE AHORRE ESPACIO
		System.out.print("Introduce minutos: ");
		int min = pt.nextByte();
		System.out.print("Introduce segundos: ");
		int seg = pt.nextByte();

		System.out.print("Fecha introducida: ");
		f1.mostrar();

		System.out.println();
		
		System.out.print("Introduce la cantidad de segundos que quieres aumentar: ");
		int upSeg = pt.nextByte();
		
		fecha f1= new fecha(hora,min,seg);
		
		
		System.out.println("Fecha incrementada: ");
		f1.mostrar();
		for(int pos = 0; pos<upSeg; pos++) {
			f1.setIncremento(1);
			f1.mostrar();
		}
	}

}
