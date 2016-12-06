package tp.pr3.mv.ins.memory;

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
 * Es la clase que define el funcionamiento y parseo de la instruccion loadind.
 */

public class LoadInd extends Instruction {

	public LoadInd() {
		// TODO Auto-generated constructor stub
	}

	// Metodo para ejecutar la operacion loadind.
	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		boolean noError = true;
		int parametro = pila.getCima();
		// Si la cima de la pila es mayor que 0 y existe la subcima, y es mayor
		// o igual que 0
		if (pila.getContador() > 0) {
			if (parametro >= 0) {
				pila.eliminarCima();
				pila.guardarEntero(memoria.load(parametro));
			} else {
				noError = false;
				throw new MVTrap("Error ejecutando LoadInd " + parametro
						+ ": dirección incorrecta " + parametro);
			}
		} else
			throw new MVTrap(
					"Error ejecutando ADD: faltan operandos en la pila (hay "
							+ pila.getContador() + ")");
		return noError;
	}

	// Metodo para parsear la operacion storeInd.
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("LOADIND")) {
			if (words.length == 1) {
				parse = new LoadInd();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "LOADIND";
		return cadena;
	}

}
