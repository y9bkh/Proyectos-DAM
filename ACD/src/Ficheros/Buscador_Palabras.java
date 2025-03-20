import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Buscador_Palabras {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner pt = new Scanner(System.in);
        File archivo = new File("archivo.txt");
        System.out.print("¿Qué palabra desea buscar?... ");
        String palabraBuscada = pt.next();
    }

    public static void buscar(File archivo, String palabraBuscada) throws IOException {
        BufferedReader bfReader = new BufferedReader(new FileReader(archivo));
        String leerLinea;
        int contar = 0;
        String[] a = palabraBuscada.split(" ");
        for(int pos = 0 ; pos<a.length; pos++) {
            if(a[pos]==palabraBuscada) {
                contar++;
            }
        }


        System.out.println(contar);
        bfReader.close();
    }

}
