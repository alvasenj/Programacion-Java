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
 * Es la clase que define el funcionamiento y parseo de la instruccion not.
 */

public class Not extends Instruction {

	public Not() {
		// TODO Auto-generated constructor stub
	}

	// Metodo para ejecutar la operacion not.
	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		boolean noError = true;

		// Si la pila tiene un operando.
		if (pila.getContador() > 0) {
			int numero1 = pila.getCima();

			// si la cima es un cero, la guardamos como un 1.
			if (numero1 == 0) {
				pila.eliminarCima();
				pila.guardarEntero(1);
				noError = true;
				// si la cima tiene algo diferente de 0, guardamos un 0.
			} else {
				pila.eliminarCima();
				pila.guardarEntero(0);
				noError = true;
			}

		} else {
			noError = false;
			throw new MVTrap(
					"Error ejecutando NOT: faltan operandos en la pila (hay "
							+ pila.getContador() + ")");
		}
		return noError;
	}

	// Metodo para parsear la operacion not.
	@Override
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("NOT")) {
			if (words.length == 1) {
				parse = new Not();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "NOT";
		return cadena;
	}

}
