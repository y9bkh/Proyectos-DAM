import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Crear_Escribir {
    public static void main(String[] args) throws IOException   {

        // TODO Auto-generated method stub
        File archivo = new File("alu/archivo.txt");
        EscribirArchivo(archivo,3);
    }

    public static void EscribirArchivo (File archivo, int Rep) throws IOException {

        BufferedWriter bfwrite = new BufferedWriter(new FileWriter("archivo.txt"));

        for (int pos = Rep; pos>0;pos--) {
            bfwrite.write("Esta es la línea "+pos);
            bfwrite.newLine();
        }
        bfwrite.close();
    }

}


