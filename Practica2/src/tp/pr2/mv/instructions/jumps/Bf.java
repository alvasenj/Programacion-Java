package tp.pr2.mv.instructions.jumps;

import tp.pr2.mv.ExecutionManager;
import tp.pr2.mv.Memory;
import tp.pr2.mv.OperandStack;
import tp.pr2.mv.instructions.Instruction;

/**
 @author Javier Druet
 @author Álvaro Asenjo 
 */

/**
 * Es la clase que define el funcionamiento y parseo de la instruccion bf.
 */

public class Bf extends Instruction {
	private int parametro;

	public Bf() {
		// TODO Auto-generated constructor stub
	}

	public Bf(String parametro) {
		this.parametro = Integer.parseInt(parametro);
	}

	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion) {
		boolean noError = true;

		if (this.parametro >= 0) {
			if (pila.getCima() == 0) {
				ejecucion.setnextPC(this.parametro);
				pila.eliminarCima();
			}
			else {
				pila.eliminarCima();
			}
		} 
		else{
			noError = true;
		}
		return noError;
	}

	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words.length == 2) {
			if (words[0].equalsIgnoreCase("BF")) {
				parse = new Bf(words[1]);
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "BF " + this.parametro;
		return cadena;
	}

}
