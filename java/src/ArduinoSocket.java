import java.io.IOException;
import java.util.Scanner;

public class ArduinoSocket {
    public static void main(String[] args) throws IOException {
        SocketClient socketClient = new SocketClient("albertovettorato.ddns.net", 6850);

        System.out.println("\t     ___             __                          ___            __ ");
        System.out.println("\t    /   |  _________/ /_  ______  ____     _____/ (_)__  ____  / /_");
        System.out.println("\t   / /| | / ___/ __  / / / / __ \\/ __ \\   / ___/ / / _ \\/ __ \\/ __/");
        System.out.println("\t  / ___ |/ /  / /_/ / /_/ / / / / /_/ /  / /__/ / /  __/ / / / /_  ");
        System.out.println("\t /_/  |_/_/   \\__,_/\\__,_/_/ /_/\\____/   \\___/_/_/\\___/_/ /_/\\__/  \n\n");


        System.out.println("\t\t ***** Portale per accedere ai sistemi arduino *****");
        System.out.println("\t\t ===================================================");
        System.out.println("\t\t [1] per accendere il LED");
        System.out.println("\t\t [2] per spegnere il LED");
        System.out.println("\t\t [q] per uscire");
        System.out.println("\t\t ===================================================");
        System.out.println("\t\t *****          By Riccardo Calligaro          *****");

        String choice = null;

        Scanner scan = new Scanner(System.in);
        do {
            System.out.print("\nInserire scelta: ");
            choice = scan.nextLine();
            switch (choice) {
                case "1":
                    socketClient.turnOn();
                    break;
                case "2":
                    socketClient.turnOff();
            } // end of switch
        } while (!choice.equals("q"));
    }
}
