import java.util.*;

public class BattleShip {

    public static void main(String[] args) {
        int[][] tablero = new int[5][5]; // Tamaño del tablero, puedes ajustarlo según tus preferencias
        inicializarTablero(tablero);
        mostrarTablero(tablero);

        Scanner scanner = new Scanner(System.in);

        int barcosRestantes = 3; // Puedes ajustar la cantidad de barcos

        while (barcosRestantes > 0) {
            System.out.println("Ingresa la fila y columna a atacar (ej. 2 3): ");
            int fila = scanner.nextInt();
            int columna = scanner.nextInt();

            if (fila < 0 || fila >= tablero.length || columna < 0 || columna >= tablero[0].length) {
                System.out.println("Posición fuera de rango. Intenta de nuevo.");
                continue;
            }

            if (tablero[fila][columna] == 1) {
                System.out.println("¡Hundiste un barco!");
                tablero[fila][columna] = 0;
                barcosRestantes--;
            } else if (tablero[fila][columna] == 0) {
                System.out.println("Ya has atacado esta posición. Intenta de nuevo.");
            } else {
                System.out.println("Agua. Intenta de nuevo.");
            }

            mostrarTablero(tablero);
        }

        System.out.println("¡Felicidades! Hundiste todos los barcos.");
    }

    private static void inicializarTablero(int[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = Math.random() < 0.3 ? 1 : 2; // 1 representa un barco, 2 representa agua
            }
        }
    }

    private static void mostrarTablero(int[][] tablero) {
        System.out.println("Tablero:");
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == 0) {
                    System.out.print("X "); // Barco hundido
                } else if (tablero[i][j] == 1) {
                    System.out.print("O "); // Barco
                } else {
                    System.out.print(". "); // Agua
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
