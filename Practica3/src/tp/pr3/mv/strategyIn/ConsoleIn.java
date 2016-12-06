package tp.pr3.mv.strategyIn;

import java.io.IOException;

/**
 * @author Javier Druet
 * @author Álvaro Asenjo
 * 
 */

public class ConsoleIn implements InMethod {

	@Override
	// Metodo que devuelve como entero un caracter que le llega por un
	// system.in.
	public int readChar() throws IOException {
		int inChar = 0;
		try {
			System.out.print("Escriba un caracter para convertirlo a entero > ");
			inChar = System.in.read();
		} catch (IOException e) {
			System.err.println("Error con la lectura del char");
		}
		return inChar;
	}

}
