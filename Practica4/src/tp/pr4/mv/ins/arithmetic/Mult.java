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
 * Es la clase que define el funcionamiento y parseo de la instruccion mult.
 */

public class Mult extends Instruction {

	public Mult() {
		// TODO Auto-generated constructor stub
	}

	// Metodo para ejecutar la multiplicacion
	@Override
	public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		// Si la pila tiene dos operandos
		if (pila.getContador() >= 2) {
			int factor1 = pila.getCima();
			int factor2 = pila.getSubCima();
			pila.eliminarCima();
			pila.eliminarCima();

			int resultado = factor1 * factor2;
			pila.guardarEntero(resultado);

		} else {
			throw new MVTrap(
					"Error ejecutando MUL: faltan operandos en la pila (hay "
							+ pila.getContador() + ")");
		}
	}

	// Metodo para parsear la multiplicacion

	@Override
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("MUL")) {
			if (words.length == 1) {
				parse = new Mult();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "MUL";
		return cadena;
	}

}
