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
 * Es la clase que define el funcionamiento y parseo de la instruccion bt.
 */

public class Bt extends Instruction {
	private int parametro;

	public Bt() {
		// TODO Auto-generated constructor stub
	}

	public Bt(String parametro) {
		this.parametro = Integer.parseInt(parametro);
	}

	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion) {
		if(pila.getCima() >= 0){
			ejecucion.setnextPC(this.parametro);
			return true;
		}
		else{
			return true;
		}
	}

	@Override
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words.length == 2) {
			if (words[0].equalsIgnoreCase("BT")) {
				parse = new Bt(words[1]);
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "BT " + this.parametro;
		return cadena;
	}

}
