package tp.pr3.mv.ins.jumps;

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
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		boolean noError = true;
		// Si se ha introducido como parametro algo mayor o igual que 0.
		if (this.parametro >= 0) {
			// Si la cima > 0, realizamos el salto, cc eliminamos la cima y
			// continuamos la ejecucion normal.
			if (pila.getCima() > 0) {
				ejecucion.setnextPC(this.parametro);
				pila.eliminarCima();
			} else {
				pila.eliminarCima();
			}
		} else {
			noError = false;
			ejecucion.pararMaquina();
			throw new MVTrap("Error ejecutando BT " + this.parametro
					+ ": dirección incorrecta " + this.parametro);
		}
		return noError;
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
