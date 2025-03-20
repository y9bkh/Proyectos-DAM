package Hilos; // Define el paquete en el que se encuentra la clase

public class TicTac { // Declara la clase TicTac

    public static void main(String[] args) { // Método principal, punto de entrada del programa
        TicThread ticThread = new TicThread(); // Crea una instancia del hilo TicThread
        TacThread tacThread = new TacThread(); // Crea una instancia del hilo TacThread

        ticThread.start(); // Inicia el hilo que imprime "TIC"
        tacThread.start(); // Inicia el hilo que imprime "TAC"
    }

    static class TicThread extends Thread { // Clase que extiende Thread para crear un hilo
        public void run() { // Método que se ejecuta al iniciar el hilo
            while (true) { // Bucle infinito
                System.out.println("TIC"); // Imprime "TIC" en la consola
                try {
                    Thread.sleep(1000); // Pausa la ejecución del hilo durante 500 milisegundos
                } catch (InterruptedException e) { // Captura una posible excepción si el hilo es interrumpido
                    e.printStackTrace(); // Imprime el seguimiento de la excepción en la consola
                }
            }
        }
    }

    static class TacThread extends Thread { // Clase que extiende Thread para crear otro hilo
        public void run() { // Método que se ejecuta al iniciar el hilo
            while (true) { // Bucle infinito
                System.out.println("TAC"); // Imprime "TAC" en la consola
                try {
                    Thread.sleep(1010); // Pausa la ejecución del hilo durante 500 milisegundos
                } catch (InterruptedException e) { // Captura una posible excepción si el hilo es interrumpido
                    e.printStackTrace(); // Imprime el seguimiento de la excepción en la consola
                }
            }
        }
    }
}
