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
 * Es la clase que define el funcionamiento y parseo de la instruccion neg.
 */

public class Neg extends Instruction {

	public Neg() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion) {
		boolean noError = true;
		int numero = pila.getCima();
		pila.eliminarCima();
		pila.guardarEntero(-numero);

		return noError;
	}

	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("NEG")) {
			if (words.length == 1) {
				parse = new Neg();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "NEG";
		return cadena;
	}
}