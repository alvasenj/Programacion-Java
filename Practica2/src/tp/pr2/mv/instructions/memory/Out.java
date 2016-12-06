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
 * Es la clase que define el funcionamiento y parseo de la instruccion out.
 */

public class Out extends Instruction {

	public Out() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion) {
		boolean noError = true;
		// SI SE INTROCUDE LA INSTRUCCION OUT, ESCRIBE EL CARACTER
		// ALMACENADO EN LA CIMA DE LA PILA
		if ((pila.getContador() > 0)) {
			char caracter = (char) pila.getCima();
			pila.eliminarCima();
			// UNICO SYSO QUE NO SE PUEDE PONER EN MAIN.
			System.out.println(caracter);
		} else
			noError = false;
		return noError;
	}

	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("OUT")) {
			if (words.length == 1) {
				parse = new Out();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "OUT";
		return cadena;
	}
}