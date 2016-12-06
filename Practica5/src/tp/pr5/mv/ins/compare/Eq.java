package tp.pr5.mv.ins.compare;

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
 * Es la clase que define el funcionamiento y parseo de la instruccion eq.
 */

public class Eq extends Instruction {

	public Eq() {
		// TODO Auto-generated constructor stub
	}

	// Metodo para ejecutar la operacion eq.
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

			// Si la cima y la subcima son iguales guardamos un 1, cc guardamos
			// un 0.
			if (numero2 == numero1) {
				pila.guardarEntero(1);
			} else {
				pila.guardarEntero(0);
			}
		} else {
			throw new MVTrap(
					"Error ejecutando EQ: faltan operandos en la pila (hay "
							+ pila.getContador() + ")");
		}
	}

	// Metodo para parsear la operacion eq.
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("EQ")) {
			if (words.length == 1) {
				parse = new Eq();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "EQ";
		return cadena;
	}

}
