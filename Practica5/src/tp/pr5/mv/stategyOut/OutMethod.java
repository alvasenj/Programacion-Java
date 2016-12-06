package tp.pr5.mv.stategyOut;

import java.io.IOException;

import javax.swing.JTextArea;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

// Interfaz que determina el funcionamiento de los metodos de salida.
public interface OutMethod {

	// Escribe un caracter en la salida.
	public void writeChar(char c) throws IOException;

	public void cerrarArchivo();

	public void setTextoVentana(JTextArea j);
}
