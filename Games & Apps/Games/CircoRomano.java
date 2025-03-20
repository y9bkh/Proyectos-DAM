import java.util.Scanner;

public class CircoRomano {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int jugador,puntos;
        puntos = 100;
        int asaltos = 0;
        int esquivar = 0;
        Scanner pt = new Scanner(System.in);
        System.out.println("\r\n"
                + " ▄▄▄██▀▀▀█    ██ ▓█████   ▄████  ▒█████      ▄████▄   ██▓ ██▀███   ▄████▄   ▒█████      ██▀███   ▒█████   ███▄ ▄███▓ ▄▄▄       ███▄    █  ▒█████  \r\n"
                + "   ▒██   ██  ▓██▒▓█   ▀  ██▒ ▀█▒▒██▒  ██▒   ▒██▀ ▀█  ▓██▒▓██ ▒ ██▒▒██▀ ▀█  ▒██▒  ██▒   ▓██ ▒ ██▒▒██▒  ██▒▓██▒▀█▀ ██▒▒████▄     ██ ▀█   █ ▒██▒  ██▒\r\n"
                + "   ░██  ▓██  ▒██░▒███   ▒██░▄▄▄░▒██░  ██▒   ▒▓█    ▄ ▒██▒▓██ ░▄█ ▒▒▓█    ▄ ▒██░  ██▒   ▓██ ░▄█ ▒▒██░  ██▒▓██    ▓██░▒██  ▀█▄  ▓██  ▀█ ██▒▒██░  ██▒\r\n"
                + "▓██▄██▓ ▓▓█  ░██░▒▓█  ▄ ░▓█  ██▓▒██   ██░   ▒▓▓▄ ▄██▒░██░▒██▀▀█▄  ▒▓▓▄ ▄██▒▒██   ██░   ▒██▀▀█▄  ▒██   ██░▒██    ▒██ ░██▄▄▄▄██ ▓██▒  ▐▌██▒▒██   ██░\r\n"
                + " ▓███▒  ▒▒█████▓ ░▒████▒░▒▓███▀▒░ ████▓▒░   ▒ ▓███▀ ░░██░░██▓ ▒██▒▒ ▓███▀ ░░ ████▓▒░   ░██▓ ▒██▒░ ████▓▒░▒██▒   ░██▒ ▓█   ▓██▒▒██░   ▓██░░ ████▓▒░\r\n"
                + " ▒▓▒▒░  ░▒▓▒ ▒ ▒ ░░ ▒░ ░ ░▒   ▒ ░ ▒░▒░▒░    ░ ░▒ ▒  ░░▓  ░ ▒▓ ░▒▓░░ ░▒ ▒  ░░ ▒░▒░▒░    ░ ▒▓ ░▒▓░░ ▒░▒░▒░ ░ ▒░   ░  ░ ▒▒   ▓▒█░░ ▒░   ▒ ▒ ░ ▒░▒░▒░ \r\n"
                + " ▒ ░▒░  ░░▒░ ░ ░  ░ ░  ░  ░   ░   ░ ▒ ▒░      ░  ▒    ▒ ░  ░▒ ░ ▒░  ░  ▒     ░ ▒ ▒░      ░▒ ░ ▒░  ░ ▒ ▒░ ░  ░      ░  ▒   ▒▒ ░░ ░░   ░ ▒░  ░ ▒ ▒░ \r\n"
                + " ░ ░ ░   ░░░ ░ ░    ░   ░ ░   ░ ░ ░ ░ ▒     ░         ▒ ░  ░░   ░ ░        ░ ░ ░ ▒       ░░   ░ ░ ░ ░ ▒  ░      ░     ░   ▒      ░   ░ ░ ░ ░ ░ ▒  \r\n"
                + " ░   ░     ░        ░  ░      ░     ░ ░     ░ ░       ░     ░     ░ ░          ░ ░        ░         ░ ░         ░         ░  ░         ░     ░ ░  \r\n"
                + "                                            ░                     ░                                                                               \r\n"
                + "");
        System.out.println("\r\n"
                + " |______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|\r\n");
        System.out.println("                                         Luchador vs. Monstruo en el Coliseo\r\n"
                + "                               ¡Enfrenta al monstruo con 100 puntos de vida y demuestra tu valentía!");
        System.out.println("\r\n"
                + " |______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|______|\r\n"
                + "                                                                                                                                              \r\n");
        do {
            int puntos_aleatorios=(int)(Math.random()*31);
            int monstruo = (int)(Math.random()*3);
            esquivar++;
            if (asaltos==15) {
                System.out.print("\r\n"
                        + " ██████╗  █████╗ ███╗   ███╗███████╗    ██╗    ██╗██╗███╗   ██╗       ██╗ \r\n"
                        + "██╔════╝ ██╔══██╗████╗ ████║██╔════╝    ██║    ██║██║████╗  ██║    ██╗╚██╗\r\n"
                        + "██║  ███╗███████║██╔████╔██║█████╗      ██║ █╗ ██║██║██╔██╗ ██║    ╚═╝ ██║\r\n"
                        + "██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      ██║███╗██║██║██║╚██╗██║    ██╗ ██║\r\n"
                        + "╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    ╚███╔███╔╝██║██║ ╚████║    ╚═╝██╔╝\r\n"
                        + " ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝     ╚══╝╚══╝ ╚═╝╚═╝  ╚═══╝       ╚═╝ \r\n"
                        + "                                                                          \r\n"
                        + "");
                System.out.println("");
                System.out.print("Informacion partida: ");
                System.out.print("Puntos: "+puntos);
                System.out.print("Asaltos: "+asaltos);
                System.exit(1);
            }
            asaltos++;
            System.out.println("----------------------------");
            System.out.println("         ASALTO "+asaltos);
            System.out.println("----------------------------");
            System.out.println("Defiendete del monstruo: ");
            System.out.println("1) Bloquear");
            System.out.println("2) Usar escudo");
            System.out.print("--> Seleccione:");
            jugador = pt.nextInt();
            if(jugador<1||jugador>2) {
                System.out.println("\r\n"
                        + "╔═╗╦═╗╦═╗╔═╗╦═╗   \r\n"
                        + "║╣ ╠╦╝╠╦╝║ ║╠╦╝   \r\n"
                        + "╚═╝╩╚═╩╚═╚═╝╩╚═ooo\r\n"
                        + "");
                asaltos--;
            }
            if(jugador==1) {
                System.out.println("");
                System.out.println("");
                System.out.println("0) Bloquear abajo");
                System.out.println("1) Bloquear medio");
                System.out.println("2) Bloquear arriba");
                System.out.print("--> Seleccione:");
                jugador = pt.nextInt();
                if(jugador<0||jugador>2) {
                    System.out.println("\r\n"
                            + "╔═╗╦═╗╦═╗╔═╗╦═╗   \r\n"
                            + "║╣ ╠╦╝╠╦╝║ ║╠╦╝   \r\n"
                            + "╚═╝╩╚═╩╚═╚═╝╩╚═ooo\r\n"
                            + "");
                    asaltos--;
                }
                if(monstruo!=jugador) {
                    puntos = puntos-puntos_aleatorios;
                    esquivar--;
                }
                if (esquivar == 3) {
                    System.out.println("");
                    System.out.println("Ganas 5 puntos. ");
                    puntos = puntos+5;
                    esquivar = 0;
                }
                System.out.println("");
                System.out.println("Puntos: "+puntos);
                System.out.println("");
            }else if(jugador==2) {
                System.out.println("");
                System.out.println("");
                System.out.println("0) Usar escudo abajo");
                System.out.println("1) Usar escudo medio");
                System.out.println("2) Usar escudo arriba");
                System.out.print("--> Seleccione:");
                jugador = pt.nextInt();
                if(jugador<0||jugador>2) {
                    System.out.println("\r\n"
                            + "╔═╗╦═╗╦═╗╔═╗╦═╗   \r\n"
                            + "║╣ ╠╦╝╠╦╝║ ║╠╦╝   \r\n"
                            + "╚═╝╩╚═╩╚═╚═╝╩╚═ooo\r\n"
                            + "");
                    asaltos--;
                }
                if(monstruo!=jugador) {
                    puntos = puntos-puntos_aleatorios;
                    esquivar--;
                }
                if (esquivar == 3) {
                    System.out.println("");
                    System.out.println("Ganas 5 puntos. ");
                    puntos = puntos+5;
                    esquivar = 0;
                }
                System.out.println("");
                System.out.println("Puntos: "+puntos);
                System.out.println("");
            }
        }while(puntos>0&&asaltos<=15);
        if (puntos<=0) {
            System.out.println("\r\n"
                    + " ██████╗  █████╗ ███╗   ███╗███████╗     ██████╗ ██╗   ██╗███████╗██████╗         ██╗\r\n"
                    + "██╔════╝ ██╔══██╗████╗ ████║██╔════╝    ██╔═══██╗██║   ██║██╔════╝██╔══██╗    ██╗██╔╝\r\n"
                    + "██║  ███╗███████║██╔████╔██║█████╗      ██║   ██║██║   ██║█████╗  ██████╔╝    ╚═╝██║ \r\n"
                    + "██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      ██║   ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗    ██╗██║ \r\n"
                    + "╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    ╚██████╔╝ ╚████╔╝ ███████╗██║  ██║    ╚═╝╚██╗\r\n"
                    + " ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝     ╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═╝        ╚═╝\r\n"
                    + "                                                                                     \r\n"
                    + "");
            System.out.println("");
            System.out.println("Informacion partida: ");
            System.out.println("Puntos: "+puntos);
            System.out.println("Asaltos: "+asaltos);
        }
    }
}
