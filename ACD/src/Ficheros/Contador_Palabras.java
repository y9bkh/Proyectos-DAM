import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Contador_Palabras {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        File archivo = new File("archivo.txt");// Crea un objeto File que representa el archivo "streams1.txt"
        numPalabrasFichero(archivo); // Llama al método numPalabrasFichero para contar las palabras en el archivo
    }

    // Método para contar el número de palabras en un archivo
    public static void numPalabrasFichero(File archivo_A_contar) throws IOException {

        // Crea un BufferedReader para leer el archivo
        BufferedReader bfReader = new BufferedReader(new FileReader(archivo_A_contar));

        String palabrasArchivo; // Variable para almacenar cada línea leída del archivo
        int contar = 0; // Variable para contar el número total de palabras

        // Bucle que lee el archivo línea por línea
        while ((palabrasArchivo = bfReader.readLine()) != null) {
            // Divide cada línea en palabras usando el espacio como delimitador
            String[] a = palabrasArchivo.split(" ");

            // Asigna el número de palabras en la línea actual a la variable contar
            contar = a.length;
        }

        // Imprime el número total de palabras (de la última línea leída)
        System.out.println(contar);

        // Cierra el BufferedReader para liberar recursos
        bfReader.close();
    }
}
