package tp.pr4.mv.strategyIn;

import java.io.IOException;

import javax.swing.JTextArea;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

// Interfaz que controla el funcionamiento de los diferentes metodos In
public interface InMethod {
	
	public int readChar() throws IOException;
	
	public void setTextoVentana(JTextArea j);

}
