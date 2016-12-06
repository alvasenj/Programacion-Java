package tp.pr5.mv.ins.arithmetic;

import tp.pr5.mv.cpu.ExecutionManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exceptions.MVTrap;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.stategyOut.OutMethod;
import tp.pr5.mv.strategyIn.InMethod;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

/**
 * Es la clase que define el funcionamiento y parseo de la instruccion sub.
 */

public class Sub extends Instruction {

	public Sub() {
		// TODO Auto-generated constructor stub
	}

	// Metodo para ejecutar la resta

	@Override
	public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		// Si la pila tiene dos operandos
		if (pila.getContador() >= 2) {
			int sumando1 = pila.getCima();
			int sumando2 = pila.getSubCima();
			pila.eliminarCima();
			pila.eliminarCima();

			int resultado = sumando2 - sumando1;
			pila.guardarEntero(resultado);

		} else {
			throw new MVTrap(
					"Error ejecutando SUB: faltan operandos en la pila (hay "
							+ pila.getContador() + ")");
		}
	}

	// Metodo para parsear la resta.

	@Override
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("SUB")) {
			if (words.length == 1) {
				parse = new Sub();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "SUB";
		return cadena;
	}

}
