package tp.pr4.mv.ins.jumps;

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
 * Es la clase que define el funcionamiento y parseo de la instruccion bt.
 */

public class Bt extends Instruction {
	private int parametro;

	public Bt() {
		// TODO Auto-generated constructor stub
	}

	// Constructora de la clase Bt con un parametro.
	public Bt(String parametro) {
		this.parametro = Integer.parseInt(parametro);
	}

	// Metodo para ejecutar la operacion bt.
	@Override
	public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		// Si se ha introducido como parametro algo mayor o igual que 0.
		if (this.parametro >= 0) {
			// Si la cima > 0, realizamos el salto, cc eliminamos la cima y
			// continuamos la ejecucion normal.
			if (pila.getContador() > 0) {
				if (pila.getCima() > 0) {
					ejecucion.setnextPC(this.parametro);
					pila.eliminarCima();
				} else {
					pila.eliminarCima();
				}
			} else {
				// Si no hay elementos en la pila
				throw new MVTrap(
						"Error ejecutando BT: faltan operandos en la pila (hay "
								+ pila.getContador() + ")");
			}
		} else {
			ejecucion.pararMaquina();
			throw new MVTrap("Error ejecutando BT " + this.parametro
					+ ": dirección incorrecta " + this.parametro);
		}
	}

	// Metodo para parsear la operacion bt.
	@Override
	public Instruction parse(String instruccion) throws MVTrap {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("BT")) {
			if (words.length == 2) {
				parse = new Bt(words[1]);
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "BT " + this.parametro;
		return cadena;
	}

}
