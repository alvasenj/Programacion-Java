package tp.pr4.mv.ins.memory;

import tp.pr4.mv.cpu.ExecutionManager;
import tp.pr4.mv.cpu.Memory;
import tp.pr4.mv.cpu.OperandStack;
import tp.pr4.mv.exceptions.MVTrap;
import tp.pr4.mv.ins.Instruction;
import tp.pr4.mv.stategyOut.OutMethod;
import tp.pr4.mv.strategyIn.InMethod;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

/**
 * Es la clase que define el funcionamiento y parseo de la instruccion neg.
 */

public class Neg extends Instruction {

	public Neg() {
		// TODO Auto-generated constructor stub
	}

	// Metodo para ejecutar la operacion neg.
	@Override
	public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		// Si la pila tiene un operando.
		if (pila.getContador() > 0) {
			int numero = pila.getCima();
			pila.eliminarCima();
			pila.guardarEntero(-numero);
		} else {
			throw new MVTrap(
					"Error ejecutando NEG: faltan operandos en la pila (hay "
							+ pila.getContador() + ")");
		}
	}

	// Metodo para parsear la operacion neg.
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("NEG")) {
			if (words.length == 1) {
				parse = new Neg();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "NEG";
		return cadena;
	}
}