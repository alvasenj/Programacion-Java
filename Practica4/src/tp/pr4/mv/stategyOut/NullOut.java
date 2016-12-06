package tp.pr4.mv.stategyOut;

import java.io.IOException;

import javax.swing.JTextArea;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
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
	
	public void setTextoVentana(JTextArea j){
	}

}
