package tp.pr5.mv.strategyIn;

import java.io.IOException;

import javax.swing.JTextArea;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

public class NullIn implements InMethod {

	@Override
	// Devuelve siempre un -1 pues no hay de donde leer el caracter.
	public int readChar() throws IOException {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public void setTextoVentana(JTextArea j) {
	}

}
