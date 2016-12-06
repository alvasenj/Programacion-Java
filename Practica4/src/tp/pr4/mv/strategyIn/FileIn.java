package tp.pr4.mv.strategyIn;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextArea;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

public class FileIn implements InMethod {

	private File archivo;
	private FileReader bf;
	private int n;

	// Constructora del metodo de entrada de archivo de texto que abre el
	// archivo para su lectura.
	public FileIn(String nombreArchivo) {
		this.archivo = new File(nombreArchivo);
		bf = null;
		n = 0;
	}

	@Override
	// Metodo que coge del archivo de texto un caracter y lo devuelve como
	// entero.
	public int readChar() {
		try {
			// Si el buffer no se ha inicializado antes, se inicializa y se lee
			// el primer caracter.
			if (bf == null) {
				bf = new FileReader(this.archivo);
			}
			n = bf.read();

			// Si hemos llegado al ultimo caracter del archivo, cerramos el
			// archivo y devolvemos un -1.
			if (n == -1) {
				bf.close();
			}
		} catch (IOException e) {
			// Si existe cualquier problema, se muestra el error y se cierra el
			// programa.
			System.err.println("Error ejecutando IN: " + e.getMessage());
			System.exit(2);
		}
		return n;
	}
	
	@Override
	public void setTextoVentana(JTextArea j) {
	}

}
