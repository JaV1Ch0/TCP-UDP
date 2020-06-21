package Piedra_Papel_Tijera;

import java.net.*;
import java.io.*;

public class ClienteTCP {
	public static void main(String[] args) throws IOException {
		Socket socketCliente = null;
		BufferedReader entrada = null;
		PrintWriter salida = null;
		try {
			socketCliente = new Socket("localhost",8888);
			entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);
		} catch (IOException e) {
			System.err.println("No puede establer canales de E/S para la conexión");
			System.exit(-1);
		}
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String linea;

		try {
			boolean sw = true;
			while (sw) {
				
				System.out.println("MENU\n" + 
						"-opcion 1\n" + 
						"-opcion 2\n" + 
						"-opcion 3\n" + 
						"-salir");

				linea = stdIn.readLine();
				salida.println(linea);

				if (linea.equals("Salir"))
					sw = false;
				else {
					linea = entrada.readLine();
					System.out.println("Respuesta servidor: ");
					System.out.println(linea);
					System.out.println(" ");
				}

			}
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
		salida.close();
		entrada.close();
		stdIn.close();
		socketCliente.close();
	}
}