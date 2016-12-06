package tp.pr4.mv.ins.arithmetic;

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
 * Es la clase que define el funcionamiento y parseo de la instruccion add.
 */

public class Add extends Instruction {

	public Add() {
	}

	// Metodo para ejecutar la suma.
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

			int resultado = sumando1 + sumando2;
			pila.guardarEntero(resultado);

		} else {
			throw new MVTrap(
					"Error ejecutando ADD: faltan operandos en la pila (hay "
							+ pila.getContador() + ")");
		}
	}

	// Metodo para parsear la suma.
	@Override
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("ADD")) {
			if (words.length == 1) {
				parse = new Add();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "ADD";
		return cadena;
	}

}
