package tp.pr3.mv.ins.booleans;

import tp.pr3.mv.cpu.ExecutionManager;
import tp.pr3.mv.cpu.Memory;
import tp.pr3.mv.cpu.OperandStack;
import tp.pr3.mv.exceptions.MVTrap;
import tp.pr3.mv.ins.Instruction;
import tp.pr3.mv.stategyOut.OutMethod;
import tp.pr3.mv.strategyIn.InMethod;

/**
 @author Javier Druet
 @author Álvaro Asenjo 
 */

/**
 * Es la clase que define el funcionamiento y parseo de la instruccion and.
 */

public class And extends Instruction {

	public And() {
		// TODO Auto-generated constructor stub
	}

	// Metodo para ejecutar la operacion and.

	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		boolean noError = true;

		// Si la pila tiene dos operandos
		if (pila.getContador() >= 2) {
			int numero1 = pila.getCima();
			int numero2 = pila.getSubCima();
			pila.eliminarCima();
			pila.eliminarCima();
			// Si cualquiera de los dos numeros cogidos son 0, guardamos 0, de
			// lo contrario, guardamos un 1.
			if (numero1 == 0 || numero2 == 0) {
				pila.guardarEntero(0);
				noError = true;
			} else {
				pila.guardarEntero(1);
				noError = true;
			}
		} else {
			noError = false;
			throw new MVTrap(
					"Error ejecutando AND: faltan operandos en la pila (hay "
							+ pila.getContador() + ")");
		}

		return noError;
	}

	// Metodo para parsear la operacion and.
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("AND")) {
			if (words.length == 1) {
				parse = new And();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "AND";
		return cadena;
	}

}
