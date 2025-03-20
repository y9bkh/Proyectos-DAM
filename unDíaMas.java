import java.util.Scanner;
public class unDíaMas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int dia, mes, año;
		boolean funciona = true;
		Scanner tiempo = new Scanner(System.in);
		System.out.print("Introduce día: ");
		dia= tiempo.nextInt();
		if (dia>=32) {
			System.out.println("------------------------------------------------------");
			System.out.println("Valor inválido. :(");
			System.out.println("------------------------------------------------------");
		 	System.exit(1);
		}
		System.out.print("Introduce mes: ");
		mes = tiempo.nextInt();
		if (mes>=13) {
			System.out.println("------------------------------------------------------");
			System.out.println("Valor inválido. :(");
			System.out.println("------------------------------------------------------");
		 	System.exit(1);
		}
		System.out.print("Introduce año: ");
		año = tiempo.nextInt();
		System.out.println("Fecha anterior: " +dia+"-"+mes+"-"+año);
		dia++; //Me suma un día
		
		if (mes==1||mes==3||mes==5||mes==7||mes==8||mes==10||mes==12) {
			if (dia>31) {
			dia = 1;
			mes++;
			System.out.println("");
			System.out.println("------------------------------------------------------");
			System.out.println("Fecha correcta. Enhorabuena. :) Tu fecha es: "+dia+ "/"+mes+ "/"+año);
		 	System.out.println("------------------------------------------------------");
		 	System.exit(1);
			}else {
				System.out.println("");
				System.out.println("------------------------------------------------------");
				System.out.println("Fecha correcta. Enhorabuena. :) Tu fecha es: "+dia+ "/"+mes+ "/"+año);
			 	System.out.println("------------------------------------------------------");
			 	System.exit(1);
			}
		}
		if (mes==4||mes==6||mes==9||mes==11) {
			if (dia>30) {
			dia = 1;
			mes++;
			System.out.println("");
			System.out.println("------------------------------------------------------");
			System.out.println("Fecha correcta. Enhorabuena. :) Tu fecha es: "+dia+ "/"+mes+ "/"+año);
		 	System.out.println("------------------------------------------------------");
		 	System.exit(1);
			}else {
				System.out.println("");
				System.out.println("------------------------------------------------------");
				System.out.println("Fecha correcta. Enhorabuena. :) Tu fecha es: "+dia+ "/"+mes+ "/"+año);
			 	System.out.println("------------------------------------------------------");
			 	System.exit(1);
			}
		}
	 
		if (mes>12) {
			dia = 1;
			mes = 1;
			año++;
			System.out.println("");
			System.out.println("------------------------------------------------------");
			System.out.println("Fecha correcta. Enhorabuena. :) Tu fecha es: "+dia+ "/"+mes+ "/"+año);
		 	System.out.println("------------------------------------------------------");
		 	System.exit(1);
		}

		//Aqui empieza el codigo para detectar el año bisiesto y me diga la fecha.
		if (año%4==0) {
			if (mes==2) {
				if (dia>29&&dia<31) {
					dia = 1;
					mes++;
					System.out.println("");
					System.out.println("------------------------------------------------------");
					System.out.println("Fecha correcta. Enhorabuena. :) Tu fecha es: "+dia+ "/"+mes+ "/"+año);
				 	System.out.println("------------------------------------------------------");
					funciona = true;
				}else if (dia>=30) {
					System.out.println("------------------------------------------------------");
					System.out.println("Fecha introducida inválida. :(");
					System.out.println("------------------------------------------------------");
				 	System.exit(1);
				}
			}
		}else {
			if (dia>28&&dia<30) {
				dia = 1;
				mes++;
				System.out.println("");
				System.out.println("------------------------------------------------------");
				System.out.println("Fecha correcta. Enhorabuena. :) Tu fecha es: "+dia+ "/"+mes+ "/"+año);
			 	System.out.println("------------------------------------------------------");
				funciona = true;
			}else if (dia>=29) {
				System.out.println("------------------------------------------------------");
				System.out.println("Fecha introducida inválida. :(");
				System.out.println("------------------------------------------------------");
			 	System.exit(1);
			}
			
		}
		
		//Aqui solo me permite que dentro de un rango de 0-28 me diga que fecha es
		if (mes==2) {
			if (dia>0||dia<28) {
				System.out.println("");
				System.out.println("------------------------------------------------------");
				System.out.println("Fecha correcta. Enhorabuena. :) Tu fecha es: "+dia+ "/"+mes+ "/"+año);
			 	System.out.println("------------------------------------------------------");
			 	System.exit(1);
			}
		}
	}
}


