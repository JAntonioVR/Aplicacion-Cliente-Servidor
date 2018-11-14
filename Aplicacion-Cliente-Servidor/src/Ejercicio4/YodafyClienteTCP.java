package Ejercicio4;

//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class YodafyClienteTCP {

	public static void main(String[] args) {
		
		byte []buferEnvio;
		byte []buferRecepcion=new byte[256];
		int bytesLeidos=0;
		
		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8989;
		DatagramPacket paquete;
                InetAddress direccion;
		// Socket para la conexión UDP
		DatagramSocket socketServicio;
		
		try {
			// Creamos un socket que se conecte a "hist" y "port":
			//////////////////////////////////////////////////////
			socketServicio=new DatagramSocket(port);
			//////////////////////////////////////////////////////			

			
			// Si queremos enviar una cadena de caracteres, hay que pasarla primero
			// a un array de bytes:
			buferEnvio="Al monte del volcán debes ir sin demora".getBytes();
			
			// Enviamos el array;
			//////////////////////////////////////////////////////
			direccion=InetAddress.getByName("198.162.0.0");//Ni idea de que poner aqui
                        paquete=new DatagramPacket(buferEnvio, buferEnvio.length, direccion, port);
                        			
			socketServicio.send(paquete);
			//////////////////////////////////////////////////////
			
			
			// Leemos la respuesta del servidor. Para ello le pasamos un array de bytes, que intentará
			// rellenar.
			//////////////////////////////////////////////////////
			paquete=new DatagramPacket(buferRecepcion, buferRecepcion.length);
                        socketServicio.receive(paquete);
                        buferRecepcion=paquete.getData();
                        direccion=paquete.getAddress();
                        port=paquete.getPort();
                        bytesLeidos=buferRecepcion.length;
			//////////////////////////////////////////////////////
			
			// MOstremos la cadena de caracteres recibidos:
			System.out.println("Recibido: ");
			for(int i=0;i<bytesLeidos;i++){
				System.out.print((char)buferRecepcion[i]);
			}
			
			// Una vez terminado el servicio, cerramos el socket
			//////////////////////////////////////////////////////
			socketServicio.close();
			//////////////////////////////////////////////////////
			
			// Excepciones:
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}
