package tp.pr4.mv.ins.booleans;

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
 * Es la clase que define el funcionamiento y parseo de la instruccion or.
 */

public class Or extends Instruction {

	public Or() {
		// TODO Auto-generated constructor stub
	}

	// Metodo para ejecutar la operacion or.
	@Override
	public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {

		// Si la pila tiene dos operandos
		if (pila.getContador() >= 2) {
			int numero1 = pila.getCima();
			int numero2 = pila.getSubCima();
			pila.eliminarCima();
			pila.eliminarCima();
			// Si la cima y la subcima son 0´s, guardamos un 0, caso contrario
			// guardamos un 1.
			if (numero1 == 0 && numero2 == 0) {
				pila.guardarEntero(0);
			} else {
				pila.guardarEntero(1);
			}
		} else {
			throw new MVTrap(
					"Error ejecutando OR: faltan operandos en la pila (hay "
							+ pila.getContador() + ")");
		}
	}

	// Metodo para parsear la operacion or.
	@Override
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("OR")) {
			if (words.length == 1) {
				parse = new Or();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "OR";
		return cadena;
	}
}
