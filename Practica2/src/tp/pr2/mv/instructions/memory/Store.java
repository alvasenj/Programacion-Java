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
 * Es la clase que define el funcionamiento y parseo de la instruccion store.
 */

public class Store extends Instruction {
	private int parametro;

	public Store() {
		super();// TODO Auto-generated constructor stub
	}

	public Store(String parametro) {
		this.parametro = Integer.parseInt(parametro);
	}

	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion) {
		boolean noError = true;
		if (this.parametro >= 0) {
			memoria.store(this.parametro, pila.getCima());
			pila.eliminarCima();
		} else {
			noError = false;
		}

		return noError;
	}

	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words.length == 2) {
			if (words[0].equalsIgnoreCase("STORE")) {
				parse = new Store(words[1]);
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "STORE " + this.parametro;
		return cadena;
	}
}