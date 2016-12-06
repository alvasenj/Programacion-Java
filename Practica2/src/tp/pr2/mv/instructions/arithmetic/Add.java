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
 * Es la clase que define el funcionamiento y parseo de la instruccion add.
 */

public class Add extends Instruction {

	public Add() {
	}

	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion) {
		boolean noError = true;
		if (pila.getContador() >= 2) {
			int sumando1 = pila.getCima();
			int sumando2 = pila.getSubCima();
			pila.eliminarCima();
			pila.eliminarCima();

			int resultado = sumando1 + sumando2;
			pila.guardarEntero(resultado);

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
			if (words[0].equalsIgnoreCase("ADD")) {
				parse = new Add();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "ADD";
		return cadena;
	}

}
