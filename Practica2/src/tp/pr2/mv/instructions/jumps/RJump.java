package tp.pr2.mv.instructions.jumps;

import tp.pr2.mv.ExecutionManager;
import tp.pr2.mv.Memory;
import tp.pr2.mv.OperandStack;
import tp.pr2.mv.instructions.Instruction;

public class RJump extends Instruction {
	private int parametro;

	public RJump(String parametro) {
		this.parametro = Integer.parseInt(parametro);
	}

	public RJump() {
		super();
	}

	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion) {
		boolean noError = true;

		if ((this.parametro) != 0 && (this.parametro != 1)) {
			int salto = ejecucion.getPC() + this.parametro;
			if(salto >= 0){
				ejecucion.setnextPC(salto);
				noError = true;
			}
		} else
			noError = false;

		return noError;
	}

	@Override
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words.length == 2) {
			if (words[0].equalsIgnoreCase("RJUMP")) {
				parse = new RJump(words[1]);
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "RJump " + this.parametro;
		return cadena;
	}

}
