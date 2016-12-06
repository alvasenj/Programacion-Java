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
 * Es la clase que define el funcionamiento y parseo de la instruccion loadind.
 */

public class LoadInd extends Instruction {

	public LoadInd() {
		// TODO Auto-generated constructor stub
	}

	// Metodo para ejecutar la operacion loadind.
	@Override
	public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		int parametro = pila.getCima();
		// Si la cima de la pila es mayor que 0 y existe la subcima, y es mayor
		// o igual que 0
		if (pila.getContador() > 0) {
			if (parametro >= 0) {
				pila.eliminarCima();
				pila.guardarEntero(memoria.load(parametro));
			} else {
				throw new MVTrap("Error ejecutando LOADIND " + parametro
						+ ": dirección incorrecta " + parametro);
			}
		} else
			throw new MVTrap(
					"Error ejecutando LOADIND: faltan operandos en la pila (hay "
							+ pila.getContador() + ")");
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
