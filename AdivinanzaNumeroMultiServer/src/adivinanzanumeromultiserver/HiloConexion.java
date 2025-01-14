package adivinanzanumeromultiserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class HiloConexion implements Runnable{
    // Propiedades
    BufferedReader bfr;
    PrintWriter pw;
    Socket socket;
    
    Random rand = new Random();
    int numeroAleatorio = rand.nextInt(100);
        
    
    // Constructor
    public HiloConexion(Socket socket) {
        this.socket = socket;
    }
    
    // Metodo run()
    @Override
    public void run() {
        try {
            System.out.println("Cliente conectado: " + socket.getInetAddress());
            System.out.println("El número aleatorio ha sido generado: " + numeroAleatorio);
            
            // Crear los streams
            bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream());
            pw.println("Introduzca su numero: ");
            pw.flush();
            
            int numero = 0;
            String respuesta;
            while (numero != numeroAleatorio) {
                numero = Integer.parseInt(bfr.readLine());
                if (numero < numeroAleatorio) {
                    respuesta = "mayor";
                    // Enviar el resultado
                    pw.println(respuesta);
                    pw.println("Introduzca su numero: ");
                    pw.flush();
                }
                if (numero > numeroAleatorio) {
                    respuesta = "menor";
                    // Enviar el resultado
                    pw.println(respuesta);
                    pw.println("Introduzca su numero: ");
                    pw.flush();
                }
            }
            respuesta = "correcto"; 
            // Enviar el resultado
            pw.println(respuesta);
            pw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
