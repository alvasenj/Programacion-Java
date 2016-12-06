package tp.pr2.mv.instructions.arithmetic;

import tp.pr2.mv.ExecutionManager;
import tp.pr2.mv.Memory;
import tp.pr2.mv.OperandStack;
import tp.pr2.mv.instructions.Instruction;

/**
 @author Javier Druet
 @author Álvaro Asenjo 
 */

/**
 * Es la clase que define el funcionamiento y parseo de la instruccion div.
 */

public class Div extends Instruction {

	public Div() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion) {
		boolean noError = true;
		if (pila.getContador() >= 2) {
			int divisor = pila.getCima();
			int dividendo = pila.getSubCima();
			pila.eliminarCima();
			pila.eliminarCima();

			if (divisor != 0) {
				int resultado = dividendo / divisor;
				pila.guardarEntero(resultado);
			} else
				noError = false;

		} else {
			noError = false;
		}
		return noError;
	}

	@Override
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words.length == 1) {
			if (words[0].equalsIgnoreCase("DIV")) {
				parse = new Div();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "DIV";
		return cadena;
	}

}
