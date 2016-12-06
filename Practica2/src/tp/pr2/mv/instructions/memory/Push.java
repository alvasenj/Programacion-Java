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
 * Es la clase que define el funcionamiento y parseo de la instruccion push.
 */

public class Push extends Instruction {

	private int parametro;

	public Push() {
		super();// TODO Auto-generated constructor stub
	}

	public Push(String parametro) {
		this.parametro = Integer.parseInt(parametro);
	}

	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion) {
		pila.guardarEntero(this.parametro);
		return true;
	}

	@Override
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words.length == 2) {
			if (words[0].equalsIgnoreCase("PUSH")) {
				parse = new Push(words[1]);
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "PUSH " + this.parametro;
		return cadena;
	}

}
