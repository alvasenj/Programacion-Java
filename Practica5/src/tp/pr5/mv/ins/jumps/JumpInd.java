package tp.pr5.mv.ins.jumps;

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
 * Es la clase que define el funcionamiento y parseo de la instruccion jump.
 */

public class JumpInd extends Instruction {

	public JumpInd() {
		// TODO Auto-generated constructor stub
	}

	// Metodo para ejecutar la operacion jump.
	@Override
	public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		if (pila.getContador() > 0) {
			int parametro = pila.getCima();
			// Si la cima >= 0, ejecutamos el salto a la direccion del
			// parametro, cc
			// lanzamos una excepcion.
			if (parametro >= 0) {
				ejecucion.setnextPC(parametro);
				pila.eliminarCima();
			} else {
				throw new MVTrap(
						"Error ejecutando JUMPIND: dirección incorrecta "
								+ parametro);
			}
		} else {
			throw new MVTrap(
					"Error ejecutando JUMPIND: faltan operandos en la pila (hay "
							+ pila.getContador() + ")");
		}
	}

	// Metodo para parsear la operacion jump.
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("JUMPIND")) {
			if (words.length == 1) {
				parse = new JumpInd();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "JUMPIND";
		return cadena;
	}

}
