package tp.pr5.mv.ins.memory;

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
 * Es la clase que define el funcionamiento y parseo de la instruccion flip.
 */

public class Flip extends Instruction {

	public Flip() {
		// TODO Auto-generated constructor stub
	}

	// Metodo para ejecutar la operacion flip.
	@Override
	public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		// Si la pila tiene dos operandos cogemos la cima y la subcima y las
		// cambiamos.
		if (pila.getContador() >= 2) {
			int numero = pila.getCima();
			int numero2 = pila.getSubCima();
			pila.eliminarCima();
			pila.eliminarCima();

			pila.guardarEntero(numero);
			pila.guardarEntero(numero2);

		} else {
			throw new MVTrap(
					"Error ejecutando FLIP: faltan operandos en la pila (hay "
							+ pila.getContador() + ")");
		}
	}

	// Metodo para parsear la operacion flip.
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("FLIP")) {
			if (words.length == 1) {

				parse = new Flip();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "FLIP";
		return cadena;
	}

}