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
 * Es la clase que define el funcionamiento y parseo de la instruccion dup.
 */

public class Dup extends Instruction {

	public Dup() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion) {
		boolean noError = true;

		if (pila.getContador() > 0) {
			int numero = pila.getCima();
			pila.guardarEntero(numero);
		} else {
			noError = false;
		}

		return noError;
	}

	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("DUP")) {
			if (words.length == 1) {
				parse = new Dup();
			}
		}
		return parse;
	}


	public String toString() {
		String cadena = "DUP";
		return cadena;
	}

}
