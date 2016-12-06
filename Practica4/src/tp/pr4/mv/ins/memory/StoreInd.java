package tp.pr4.mv.ins.memory;

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
 * Es la clase que define el funcionamiento y parseo de la instruccion storeind.
 */

public class StoreInd extends Instruction {

	public StoreInd() {
		// TODO Auto-generated constructor stub
	}

	// Metodo para ejecutar la operacion storeInd.
	@Override
	public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		// Si la pila contiene dos operandos.
		if (pila.getContador() >= 2) {
			int parametro = pila.getSubCima();
			// Si la subcima de la pila es mayor que 0.
			if (parametro >= 0) {
				memoria.store(parametro, pila.getCima());
				pila.eliminarCima();
				pila.eliminarCima();
			} else {
				throw new MVTrap("Error ejecutando STOREIND " + parametro
						+ ": dirección incorrecta " + parametro);
			}
		} else {
			throw new MVTrap(
					"Error ejecutando STOREIND: faltan operandos en la pila (hay "
							+ pila.getContador() + ")");
		}
	}

	// Metodo para parsear la operacion storeInd.
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("STOREIND")) {
			if (words.length == 1) {
				parse = new StoreInd();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "STOREIND";
		return cadena;
	}

}
