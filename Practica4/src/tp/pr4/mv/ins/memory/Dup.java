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
 * Es la clase que define el funcionamiento y parseo de la instruccion dup.
 */

public class Dup extends Instruction {

	public Dup() {
		// TODO Auto-generated constructor stub
	}

	// Metodo para ejecutar la operacion dup.
	@Override
	public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		// Si la pila tiene un operando.
		if (pila.getContador() > 0) {
			int numero = pila.getCima();
			pila.guardarEntero(numero);
		} else {
			throw new MVTrap(
					"Error ejecutando DUP: faltan operandos en la pila (hay "
							+ pila.getContador() + ")");
		}
	}

	// Metodo para parsear la operacion dup.
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("DUP")) {
			if (words.length == 1) {
				parse = new Dup();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "DUP";
		return cadena;
	}

}
