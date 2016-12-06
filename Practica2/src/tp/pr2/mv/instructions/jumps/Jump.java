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
 * Es la clase que define el funcionamiento y parseo de la instruccion jump.
 */

public class Jump extends Instruction {
	private int parametro;

	public Jump() {
		// TODO Auto-generated constructor stub
	}

	public Jump(String parametro) {
		this.parametro = Integer.parseInt(parametro);
	}

	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion) {
		ejecucion.setnextPC(this.parametro);
		return true;
	}

	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words.length == 2) {
			if (words[0].equalsIgnoreCase("JUMP")) {
				parse = new Jump(words[1]);
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "JUMP " + this.parametro;
		return cadena;
	}

}
