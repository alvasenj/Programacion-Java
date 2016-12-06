package tp.pr3.mv.stategyOut;

import java.io.IOException;

/**
 * @author Javier Druet
 * @author Álvaro Asenjo
 * 
 */

public class NullOut implements OutMethod {

	@Override
	// Metodo que imprime por pantalla el caracter que le venga por la cima de
	// la pila.
	public void writeChar(char c) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(c);
	}

	public void cerrarArchivo() {
	}

}
