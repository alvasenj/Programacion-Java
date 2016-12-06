package tp.pr5.mv.stategyOut;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextArea;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

public class FileOut implements OutMethod {

	private File archivo;
	private FileWriter lector;
	int posicion = 0;

	// Constructora de la clase de FileOut que abre un archivo de salida y
	// permite la escritura en el.
	public FileOut(String nombreArchivo) {
		this.archivo = new File(nombreArchivo);
		lector = null;
		// Intentamos escribir en el archivo.
		try {
			lector = new FileWriter(archivo);
		} catch (IOException e) {
			// Si existe cualquier problema, se muestra el error y se cierra el
			// programa.
			System.err.println("Error ejecutando OUT: " + e.getMessage());
			System.exit(2);
		}
	}

	// Metodo que escribe en el archivo de salida el caracter que le venga por
	// la cima de la pila.
	@Override
	public void writeChar(char c) throws IOException {
		lector.write(c);
	}

	// Metodo que cierra el archivo abierto en la constructora.
	public void cerrarArchivo() {
		try {
			this.lector.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error al cerrar el archivo out");
		}
	}

	public void setTextoVentana(JTextArea j) {
	}
}
