package tp.pr2.mv.instructions.memory;

import tp.pr2.mv.ExecutionManager;
import tp.pr2.mv.Memory;
import tp.pr2.mv.OperandStack;
import tp.pr2.mv.instructions.Instruction;



public class Pop extends Instruction {

	public Pop() {
		// TODO Auto-generated constructor stub
	}

	// Metodo para ejecutar la operacion dup.
	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion) {
		boolean noError = true;
		// si la pila tiene un operando
		if (pila.getContador() > 0) {
			pila.eliminarCima();
		} else{
			noError = false;
		}
		return noError;
	}

	// Metodo para parsear la operacion pop.
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("POP")) {
			if (words.length == 1) {
				parse = new Pop();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "POP";
		return cadena;
	}

}