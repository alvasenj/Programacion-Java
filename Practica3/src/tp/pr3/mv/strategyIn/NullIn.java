package tp.pr3.mv.strategyIn;

import java.io.IOException;

/**
 * @author Javier Druet
 * @author Álvaro Asenjo
 * 
 */

public class NullIn implements InMethod {

	@Override
	// Devuelve siempre un -1 pues no hay de donde leer el caracter.
	public int readChar() throws IOException {
		// TODO Auto-generated method stub
		return -1;
	}

}
