package Cont_Palabras;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class ServerUDP {
	
	
	public static String conv(String x, int ta) {
		String res = "";
		for(int i = 0 ; i < ta ; i++) {
			res +=x.charAt(i);
		}
		
		return res;
		
	}
 
    public static void main(String[] args) {
 
        final int PUERTO = 8888;
        byte[] buffer = new byte[1024];
        try {
        	
            System.out.println("Iniciado el servidor UDP");
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);
            while (true) {
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);
                System.out.println("Recibo la informacion del cliente");
                String mensaje = new String(peticion.getData());
                System.out.println(mensaje);
                
                StringTokenizer st = new StringTokenizer(mensaje);
                String a =  st.countTokens()+"";
                mensaje = a;
                System.out.println(mensaje);
                
                int puertoCliente = peticion.getPort();
                InetAddress direccion = peticion.getAddress();
 
                buffer = mensaje.getBytes();
                System.out.println("puerto cliente :"+puertoCliente);
                System.out.println("direccion cliente :"+direccion);
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
 
                System.out.println("Envio la informacion del cliente");
                socketUDP.send(respuesta);
                 
            }
 
        } catch (SocketException ex) {
            Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
 
}