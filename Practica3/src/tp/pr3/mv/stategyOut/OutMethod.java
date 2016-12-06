package tp.pr3.mv.stategyOut;

import java.io.IOException;

/**
 * @author Javier Druet
 * @author Álvaro Asenjo
 * 
 */

//Interfaz que determina el funcionamiento de los metodos de salida.
public interface OutMethod {

	// Escribe un caracter en la salida.
	public void writeChar(char c) throws IOException;

	public void cerrarArchivo();
}
