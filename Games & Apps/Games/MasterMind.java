import java.util.*;

public class MasterMind {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[]tabla1=new int[0];
        int[]tabla2=new int[0];
        JUEGO(tabla1,tabla2);

    }


    static void JUEGO(int[]tabla1,int[]tabla2) {
        Scanner pt = new Scanner(System.in);
        System.out.println(" ▄▄       ▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄       ▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄        ▄  ▄▄▄▄▄▄▄▄▄▄  \r\n"
                + "▐░░▌     ▐░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░▌     ▐░░▌▐░░░░░░░░░░░▌▐░░▌      ▐░▌▐░░░░░░░░░░▌ \r\n"
                + "▐░▌░▌   ▐░▐░▌▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀▀▀  ▀▀▀▀█░█▀▀▀▀ ▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░▌░▌   ▐░▐░▌ ▀▀▀▀█░█▀▀▀▀ ▐░▌░▌     ▐░▌▐░█▀▀▀▀▀▀▀█░▌\r\n"
                + "▐░▌▐░▌ ▐░▌▐░▌▐░▌       ▐░▌▐░▌               ▐░▌     ▐░▌          ▐░▌       ▐░▌▐░▌▐░▌ ▐░▌▐░▌     ▐░▌     ▐░▌▐░▌    ▐░▌▐░▌       ▐░▌\r\n"
                + "▐░▌ ▐░▐░▌ ▐░▌▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄▄▄      ▐░▌     ▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌▐░▌ ▐░▐░▌ ▐░▌     ▐░▌     ▐░▌ ▐░▌   ▐░▌▐░▌       ▐░▌\r\n"
                + "▐░▌  ▐░▌  ▐░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌     ▐░▌     ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌  ▐░▌  ▐░▌     ▐░▌     ▐░▌  ▐░▌  ▐░▌▐░▌       ▐░▌\r\n"
                + "▐░▌   ▀   ▐░▌▐░█▀▀▀▀▀▀▀█░▌ ▀▀▀▀▀▀▀▀▀█░▌     ▐░▌     ▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀█░█▀▀ ▐░▌   ▀   ▐░▌     ▐░▌     ▐░▌   ▐░▌ ▐░▌▐░▌       ▐░▌\r\n"
                + "▐░▌       ▐░▌▐░▌       ▐░▌          ▐░▌     ▐░▌     ▐░▌          ▐░▌     ▐░▌  ▐░▌       ▐░▌     ▐░▌     ▐░▌    ▐░▌▐░▌▐░▌       ▐░▌\r\n"
                + "▐░▌       ▐░▌▐░▌       ▐░▌ ▄▄▄▄▄▄▄▄▄█░▌     ▐░▌     ▐░█▄▄▄▄▄▄▄▄▄ ▐░▌      ▐░▌ ▐░▌       ▐░▌ ▄▄▄▄█░█▄▄▄▄ ▐░▌     ▐░▐░▌▐░█▄▄▄▄▄▄▄█░▌\r\n"
                + "▐░▌       ▐░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌     ▐░▌     ▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░▌      ▐░░▌▐░░░░░░░░░░▌ \r\n"
                + " ▀         ▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀       ▀       ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀        ▀▀  ▀▀▀▀▀▀▀▀▀▀  \r\n"
                + "                                                                                                                                  ");
        System.out.print("Introduce nombre Jugador 1: ");
        String user1 = pt.next();
        System.out.print("Introduce nombre Jugador 2: ");
        String user2 = pt.next();

        System.out.println();
        System.out.println();


        System.out.print(user1+" introduce la longitud de tu tabla: ");
        int longitud1 = pt.nextInt();
        tabla1 = new int [longitud1];
        System.out.print(user2+" introduce la longitud de tu tabla: ");
        int longitud2 = pt.nextInt();
        tabla2 = new int [longitud2];

        System.out.println("----------------------------------------------");
        System.out.println("¿Quién insertara primero los números, 1 o 2? ");
        System.out.println(user1+": 1 ");
        System.out.println(user2+": 2 ");
        System.out.println("----------------------------------------------");
        int decision = pt.nextInt();
        if(decision==1) {
            jugador1(tabla1,user1,user2);
            adivina2(tabla1,user1,user2);
        }else if(decision==2) {
            jugador2(tabla2,user1,user2);
            adivina1(tabla2,user1,user2);
        }

    }
    //FUNCION DE LA TABLA 1:
    static void jugador1(int[]tabla1, String user1, String user2) {
        Scanner pt = new Scanner(System.in);
        for (int pos = 0; pos < tabla1.length; pos++) {
            System.out.print(user1+" introduce numeros de tu tabla: ");
            int jugador1 = pt.nextInt();
            tabla1[pos] = jugador1;
        }
        for (int posi = 0; posi < 25; posi++) {
            System.out.println();
        }
    }
    //FUNCION DE LA TABLA 2:
    static void jugador2(int[]tabla2, String user1, String user2) {
        Scanner pt = new Scanner(System.in);
        for (int pos = 0; pos < tabla2.length; pos++) {
            System.out.print(user2+" 2 introduce numeros de tu tabla: ");
            int jugador2 = pt.nextInt();
            tabla2[pos] = jugador2;
        }
        for (int posi = 0; posi < 25; posi++) {
            System.out.println();
        }
    }

    static void adivina1(int[]tabla2, String user1, String user2){
        int[]apuesta1 = new int[tabla2.length];
        int intentos=3;
        while(apuesta1!=tabla2) {
            for (int pos = 0; pos < tabla2.length; pos++) {
                Scanner pt = new Scanner(System.in);
                System.out.print(user1+" adivina los numeros de la tabla "+user2+": ");
                int jugador1 = pt.nextInt();
                apuesta1[pos] = jugador1;
            }
            heridosAdivina1(tabla2,apuesta1);
            muertosAdivina1(tabla2,apuesta1);
            System.out.println();
            if(apuesta1!=tabla2) {
                intentos--;
                System.out.println("Intentos: "+intentos);
                if(intentos==0) {
                    System.exit(1);
                }
            }
        }
    }

    static void adivina2(int[]tabla1, String user1, String user2){
        int[]apuesta2 = new int[tabla1.length];
        for (int pos = 0; pos < tabla1.length; pos++) {
            Scanner pt = new Scanner(System.in);
            System.out.print(user2+" adivina los numeros de la tabla "+user1+": ");
            int jugador2 = pt.nextInt();
            apuesta2[pos] = jugador2;
        }
        heridosAdivina2(tabla1,apuesta2);
        muertosAdivina2(tabla1,apuesta2);
    }

    static void heridosAdivina1(int[]tabla2, int[]apuesta1) {
        System.out.println();
        System.out.print("HERIDOS: [");
        for(int pos=0; pos<tabla2.length; pos++) {
            boolean pintar = false;
            for(int p=0; p<tabla2.length; p++) {
                if(tabla2[pos]==apuesta1[p]&&p!=pos) {
                    pintar=true;
                    break;
                }
            }
            if(pintar==true) {
                System.out.print(apuesta1[pos]);
            }else {
                System.out.print("x");
            }
            if(pos<tabla2.length-1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");

    }

    static void heridosAdivina2(int[]tabla1, int[]apuesta2) {
        System.out.println();
        int contheridos=0;
        System.out.print("HERIDOS: [");
        for(int pos=0; pos<tabla1.length; pos++) {
            boolean pintar = false;
            for(int p=0; p<tabla1.length; p++) {
                if(tabla1[pos]==apuesta2[p]&&p!=pos) {
                    pintar=true;
                    break;
                }
            }
            if(pintar==true) {
                System.out.print(apuesta2[pos]);
            }else {
                System.out.print("x");
            }
            if(pos<tabla1.length-1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");

    }

    static void muertosAdivina1(int[]tabla2, int[]apuesta1) {
        System.out.println();
        System.out.print("MUERTOS: [");
        for(int pos=0; pos<tabla2.length; pos++) {
            boolean pintar = false;
            for(int p=0; p<tabla2.length; p++) {
                if(tabla2[pos]==apuesta1[pos]&&p!=pos) {
                    pintar=true;
                    break;
                }
            }
            if(pintar==true) {
                System.out.print(apuesta1[pos]);
            }else {
                System.out.print("x");
            }
            if(pos<tabla2.length-1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
        if(tabla2==apuesta1) {
            System.out.print("HAS ACERTADO TODAS, ENHORABUENA");
            System.exit(1);
        }
    }




    static void muertosAdivina2(int[]tabla1, int[]apuesta2) {
        System.out.println();
        System.out.print("MUERTOS: [");
        for(int pos=0; pos<tabla1.length; pos++) {
            boolean pintar = false;
            for(int p=0; p<tabla1.length; p++) {
                if(tabla1[pos]==apuesta2[pos]&&p!=pos) {
                    pintar=true;
                    break;
                }
            }
            if(pintar==true) {
                System.out.print(apuesta2[pos]);
            }else {
                System.out.print("x");
            }
            if(pos<tabla1.length-1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");

    }
}
