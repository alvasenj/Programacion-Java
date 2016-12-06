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
 * Es la clase que define el funcionamiento y parseo de la instruccion and.
 */

public class And extends Instruction {

	public And() {
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
			if (numero1 == 0 || numero2 == 0) {
				pila.guardarEntero(0);
				noError = true;
			}
			else{
				pila.guardarEntero(1);
				noError = true;
			}
		} else
			noError = false;
		return noError;
	}

	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words.length == 1) {
			if (words[0].equalsIgnoreCase("AND")) {
				parse = new And();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "AND";
		return cadena;
	}

}
