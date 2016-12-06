package tp.pr3.mv.ins.arithmetic;

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
 * Es la clase que define el funcionamiento y parseo de la instruccion div.
 */

public class Div extends Instruction {

	public Div() {
		// TODO Auto-generated constructor stub
	}

	// Metodo para ejecutar la division.
	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		boolean noError = true;
		// Si la pila tiene dos operandos
		if (pila.getContador() >= 2) {
			int divisor = pila.getCima();
			int dividendo = pila.getSubCima();
			pila.eliminarCima();
			pila.eliminarCima();

			// si el divisor es diferente de 0, continuamos con la ejecucion.
			if (divisor != 0) {
				int resultado = dividendo / divisor;
				pila.guardarEntero(resultado);
			} else {
				noError = false;
				throw new MVTrap("División por cero");
			}
		} else {
			noError = false;
			throw new MVTrap(
					"Error ejecutando DIV: faltan operandos en la pila (hay "
							+ pila.getContador() + ")");
		}
		return noError;
	}

	// Metodo para parsear la division
	@Override
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("DIV")) {
			if (words.length == 1) {
				parse = new Div();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "DIV";
		return cadena;
	}

}
