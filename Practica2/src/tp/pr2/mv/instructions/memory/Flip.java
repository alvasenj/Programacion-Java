package tp.pr2.mv.instructions.memory;

import tp.pr2.mv.ExecutionManager;
import tp.pr2.mv.Memory;
import tp.pr2.mv.OperandStack;
import tp.pr2.mv.instructions.Instruction;

/**
 @author Javier Druet
 @author Álvaro Asenjo 
 */

/**
 * Es la clase que define el funcionamiento y parseo de la instruccion flip.
 */

public class Flip extends Instruction {

	public Flip() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion) {
		boolean noError = true;
		if (pila.getContador() > 0) {
			int numero = pila.getCima();
			int numero2 = pila.getSubCima();
			pila.eliminarCima();
			pila.eliminarCima();

			pila.guardarEntero(numero);
			pila.guardarEntero(numero2);

		} else {
			noError = false;
		}
		return noError;
	}

	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("FLIP")) {
			if (words.length == 1) {
				parse = new Flip();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "FLIP";
		return cadena;
	}

}