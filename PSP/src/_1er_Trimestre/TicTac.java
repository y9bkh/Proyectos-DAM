package _1er_Trimestre;

public class TicTac {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TicTac ticTac = new TicTac();
		
		Tic tic = ticTac.new Tic();
		Tac tac = ticTac.new Tac();
		
		tic.start();
		tac.start();
	}
	
	public class Tic extends Thread {
		public void run() {
			while (true) {
				System.out.println("Tic");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public class Tac extends Thread {
		public void run() {
			while (true) {
				System.out.println("Tac");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	

}
