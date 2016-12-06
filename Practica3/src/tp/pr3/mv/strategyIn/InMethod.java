package tp.pr3.mv.strategyIn;

import java.io.IOException;

/**
 * @author Javier Druet
 * @author Álvaro Asenjo
 * 
 */

//Interfaz que controla el funcionamiento de los diferentes metodos In
public interface InMethod {

	public int readChar() throws IOException;

}
