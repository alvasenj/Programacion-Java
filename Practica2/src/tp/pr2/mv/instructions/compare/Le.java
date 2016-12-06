package tp.pr2.mv.instructions.compare;

import tp.pr2.mv.ExecutionManager;
import tp.pr2.mv.Memory;
import tp.pr2.mv.OperandStack;
import tp.pr2.mv.instructions.Instruction;


/**
 @author Javier Druet
 @author Álvaro Asenjo 
 */

/**
 * Es la clase que define el funcionamiento y parseo de la instruccion le.
 */

public class Le extends Instruction {

	public Le() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion) {
		boolean noError = true;
		if (pila.getContador() >= 2) {
			int numero1 = pila.getCima();
			int numero2 = pila.getSubCima();
			pila.eliminarCima();
			pila.eliminarCima();

			if (numero2 <= numero1) {
				pila.guardarEntero(1);
			} else {
				pila.guardarEntero(0);
			}
		} else
			noError = false;
		return noError;
	}

	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words.length == 1) {
			if (words[0].equalsIgnoreCase("LE")) {
				parse = new Le();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "LE";
		return cadena;
	}

}
