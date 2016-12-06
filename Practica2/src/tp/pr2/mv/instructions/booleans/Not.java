package tp.pr2.mv.instructions.booleans;

import tp.pr2.mv.ExecutionManager;
import tp.pr2.mv.Memory;
import tp.pr2.mv.OperandStack;
import tp.pr2.mv.instructions.Instruction;


/**
 @author Javier Druet
 @author Álvaro Asenjo 
 */

/**
 * Es la clase que define el funcionamiento y parseo de la instruccion not.
 */

public class Not extends Instruction {

	public Not() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion) {
		boolean noError = true;

		if (pila.getContador() > 0) {
			int numero1 = pila.getCima();

			if (numero1 == 0) {
				pila.eliminarCima();
				pila.guardarEntero(1);
				noError = true;
			} else {
				pila.eliminarCima();
				pila.guardarEntero(0);
				noError = true;
			}

		} else
			noError = false;

		return noError;
	}

	@Override
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words.length == 1) {
			if (words[0].equalsIgnoreCase("NOT")) {
				parse = new Not();
			}
		}
		return parse;
	}


	public String toString() {
		String cadena = "NOT";
		return cadena;
	}

}
