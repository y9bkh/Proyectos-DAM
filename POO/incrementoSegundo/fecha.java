package horaCualquiera;

import java.util.Scanner;

public class fecha {
	private int hora;
	private int min;
	private int seg;
	private int incremento;
	
	Scanner pt = new Scanner(System.in);
	fecha(int hora, int min, int seg) {
		this.hora = hora;
		this.min = min;
		this.seg = seg;
	}
	
	void setHora(int hora) {
		this.hora = hora;
	}

	int getHora() {
		return hora;
	}
	
	void setMin(int Min) {
		this.min = min;
	}

	int getMin() {
		return min;
	}

	
	void setSeg(int Seg) {
		this.hora = hora;
	}

	int getSeg() {
		return seg;
	}
	
	void setIncremento(int incremento) {
		this.incremento=incremento;
		while (incremento > 0) {
			seg++;
			if(seg>=60) {
				seg=0;
				min++;
			}
			if(min>59) {
				min=0;
				hora++;
			}
			if(hora>23) {
				hora=0;
			}
			incremento--;
		}
	}

	int getIncremento() {
		return incremento;
	}

	void mostrar() {
		System.out.println(hora + ":" + min + ":" + seg);
	}

	
}
