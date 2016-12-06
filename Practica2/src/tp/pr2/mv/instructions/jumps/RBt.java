package tp.pr2.mv.instructions.jumps;

import tp.pr2.mv.ExecutionManager;
import tp.pr2.mv.Memory;
import tp.pr2.mv.OperandStack;
import tp.pr2.mv.instructions.Instruction;

public class RBt extends Instruction {
	private int parametro;

	public RBt() {
		super();
	}

	public RBt(String parametro) {
		this.parametro = Integer.parseInt(parametro);
	}

	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion) {
		boolean noError = true;
		if ((this.parametro != 0) && (this.parametro != 1)) {
			if (pila.getCima() == 0) {
				int salto = this.parametro + ejecucion.getPC();
				if (salto >= 0) {
					ejecucion.setnextPC(salto);
					pila.eliminarCima();
				}
				else{
					pila.eliminarCima();
				}
			} else {
				noError = false;
			}
		}
		return noError;
	}

	@Override
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words.length == 2) {
			if (words[0].equalsIgnoreCase("RBT")) {
				parse = new RBt(words[1]);
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "RBt " + this.parametro;
		return cadena;
	}

}
