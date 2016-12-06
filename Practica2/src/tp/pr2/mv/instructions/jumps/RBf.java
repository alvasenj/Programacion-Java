package tp.pr2.mv.instructions.jumps;

import tp.pr2.mv.ExecutionManager;
import tp.pr2.mv.Memory;
import tp.pr2.mv.OperandStack;
import tp.pr2.mv.instructions.Instruction;

public class RBf extends Instruction {
	private int parametro;

	public RBf(String parametro) {
		// TODO Auto-generated constructor stub
		this.parametro = Integer.parseInt(parametro);
	}

	public RBf() {
		super();// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion) {
		boolean noError = true;
		int salto = this.parametro + ejecucion.getPC();

		if (pila.getCima() == 0) {
			if (salto >= 0) {
				ejecucion.setnextPC(salto);
				noError = true;
				pila.eliminarCima();
			} else
				pila.eliminarCima();
		} else {
			noError = false;
		}
		return noError;
	}

	@Override
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words.length == 2) {
			if (words[0].equalsIgnoreCase("RBF")) {
				parse = new RBf(words[1]);
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "RBF " + this.parametro;
		return cadena;
	}

}
