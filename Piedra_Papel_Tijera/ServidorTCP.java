package Piedra_Papel_Tijera;

import java.io.*;
import java.net.*;
import java.nio.channels.SocketChannel;

public class ServidorTCP {
	public static final int PORT = 8888;

	public static void main(String[] args) throws IOException {
		
		ServerSocket socketServidor = null;
		try {
			socketServidor = new ServerSocket(PORT);
		} catch (IOException e) {
			System.out.println("No puede escuchar en el puerto: " + PORT);
			System.exit(-1);
		}

		Socket socketCliente = null;
		BufferedReader entrada = null;
		PrintWriter salida = null;

		System.out.println("Escuchando: " + socketServidor);
		try {

			while (true) {
				socketCliente = socketServidor.accept();
				System.out.println("Connexión Establecida: " + socketCliente);
				System.out.println(socketCliente.getPort());
				entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
				salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())),true);

				boolean sw = true;
				while (sw) {
					String str = entrada.readLine();
					if (str.equals("Salir"))
						sw = false;
					else {
						String a = str;
						switch (str) {						
						case "1":
							str = "papel";
							break;
						case "2":
							str = "piedra";
							break;
						case "3":
							str = "tijera";
							break;
						default:
							str = "Opcion Invalida";
							break;
						}
						System.out.println("Opcion " + a + ": "+str);
						
						salida.println("Opcion " + a + ": "+str);
					}

				}
			}

		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
		salida.close();
		entrada.close();
		socketCliente.close();
		socketServidor.close();
	}
}