package Cont_Palabras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class ClienteUDP {
 
    public static void main(String[] args) {
    	
        final int PUERTO_SERVIDOR = 8888;
        byte[] bufferS = new byte[1024];
        byte[] bufferR = new byte[1024];
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
 
        try {
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            DatagramSocket socketUDP = new DatagramSocket();
            String cadena = stdIn.readLine();
            bufferS = cadena.getBytes();
            DatagramPacket pregunta = new DatagramPacket(bufferS, bufferS.length, direccionServidor, PUERTO_SERVIDOR);
            System.out.println("Envio el datagrama");
            socketUDP.send(pregunta);
            DatagramPacket peticion = new DatagramPacket(bufferR, bufferR.length);
            socketUDP.receive(peticion);
            System.out.println("Recibo la peticion");
            cadena = new String(peticion.getData());
            System.out.println(cadena);
            socketUDP.close();
        } catch (SocketException ex) {
            Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
 
}


