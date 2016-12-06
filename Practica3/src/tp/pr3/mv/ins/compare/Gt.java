package tp.pr3.mv.ins.compare;

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
 * Es la clase que define el funcionamiento y parseo de la instruccion gt.
 */

public class Gt extends Instruction {

	public Gt() {
		// TODO Auto-generated constructor stub
	}

	// Metodo para ejecutar la operacion gt.
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

			// si subcima > cima, guardamos un 1, cc guardamos un 0.
			if (numero2 > numero1) {
				pila.guardarEntero(1);
			} else {
				pila.guardarEntero(0);
			}
		} else {
			noError = false;
			throw new MVTrap(
					"Error ejecutando GT: faltan operandos en la pila (hay "
							+ pila.getContador() + ")");
		}
		return noError;
	}

	// Metodo para parsear la operacion gt.
	@Override
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("GT")) {
			if (words.length == 1) {
				parse = new Gt();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "GT";
		return cadena;
	}

}
