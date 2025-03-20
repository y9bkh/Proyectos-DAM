package paquetespruebas;

public class cuenta {

	public static void main(String[] args) {
			// TODO Auto-generated method stub
			CuentaCorriente c1, c2, c3;
			//creamos dos gestores

			Gestor g1 = new Gestor ("Antonio", 333444, 444);
			Gestor g2 = new Gestor ("Pepa", 111111222, 12000);

			//creamos varias cuentas
			c1 = new CuentaCorriente ("111111111-T", "Antoñito", g1); //cuenta administrada por g1
			c2 = new CuentaCorriente ("222222222-T", "Amparito", g1);
			c3 = new CuentaCorriente ("333333333-T", "Sancho", null);  //cuenta sin gestor

			c1.mostrarInformacion();
			c2.mostrarInformacion();
			c3.mostrarInformacion();
			c1.setGestor(g2);//cambiamos de gestor
			c1.mostrarInformacion();

		}
}