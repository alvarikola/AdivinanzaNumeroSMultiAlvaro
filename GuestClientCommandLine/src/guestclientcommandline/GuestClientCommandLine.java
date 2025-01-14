package guestclientcommandline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class GuestClientCommandLine {

    public static void main(String[] args) {
        
        //Comprobacion de argumentos
        if (args.length < 2) {
            System.out.println("Error");
            System.exit(1);
        }
        
        // Dirección y puerto del servidor
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        
        int LimiteInferior = 1;
        int LimiteSuperior = 100;
        Random rand = new Random();
        int myNumber; 
        
        try {
            // Conexión al servidor
            Socket conexion = new Socket(host, port);
            System.out.println("Conectado al servidor " + host + " en el puerto " + port + ".");
            
            // Streams para enviar y recibir mensajes
            BufferedReader input = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            PrintWriter output = new PrintWriter(conexion.getOutputStream(), true);
            BufferedReader stdInput = new BufferedReader (new InputStreamReader(System.in));
            
            // Comunicacion con el servidor
            // Envio
            boolean continuar = true;
            while (continuar) {
                
                // numeroAleatorio = rand.nextInt(LimiteInferior, LimiteSuperior); 
                myNumber = Integer.parseInt(stdInput.readLine());
                output.println(myNumber);

                String answer = input.readLine();
                System.out.println("La respuesta del servidor es: " + answer); 
                if ("menor".equals(answer)) {
                    LimiteSuperior = myNumber;
                }
                if ("mayor".equals(answer)) {
                    LimiteInferior = myNumber;
                }
                if ("correcto".equals(answer)) {
                    continuar = false;
                    System.out.println("Salí");
                }
            }
            output.flush();
            // Cerrar los streams
            input.close();
            
        } catch (IOException ex) {
            
        }
    }
    
}
