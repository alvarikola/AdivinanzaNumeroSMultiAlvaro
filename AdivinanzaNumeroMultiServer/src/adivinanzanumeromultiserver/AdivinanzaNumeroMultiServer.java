package adivinanzanumeromultiserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AdivinanzaNumeroMultiServer {

    public static void main(String[] args) {
        // Puerto del servidor
        int port = 9876;
        
        try {
            // Socket para aceptar conexiones
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Servidor para generar números iniciado en el puerto " + port + ".");
            // Recibir y atender peticiones indefinidamente
            while (true) {
                // Aceptar una conexion
                Socket conexion = serverSocket.accept();
                // Crear y lanzar hilo para antender la peticion
                Thread hilo = new Thread(new HiloConexion(conexion));
                hilo.start();
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
