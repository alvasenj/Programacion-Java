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
 * Es la clase que define el funcionamiento y parseo de la instruccion store.
 */

public class Store extends Instruction {
	private int parametro;

	public Store() {
		super();// TODO Auto-generated constructor stub
	}

	// Constructora de la clase Load con parametro.
	public Store(String parametro) {
		this.parametro = Integer.parseInt(parametro);
	}

	// Metodo para ejecutar la operacion store.
	@Override
	public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		// si la posicion donde guardar es mayor o igual que 0.
		if (pila.getContador() > 0) {
			if (this.parametro >= 0) {
				memoria.store(this.parametro, pila.getCima());
				pila.eliminarCima();
			} else {
				ejecucion.pararMaquina();
				throw new MVTrap("Error ejecutando STORE " + this.parametro
						+ ": dirección incorrecta (" + this.parametro + ")");
			}
		} else {
			throw new MVTrap(
					"Error ejecutando STORE: faltan operandos en la pila (hay "
							+ pila.getContador() + ")");
		}
	}

	// Metodo para parsear la operacion store.
	public Instruction parse(String instruccion) throws MVTrap {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("STORE")) {
			if (words.length == 2) {
				parse = new Store(words[1]);
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "STORE " + this.parametro;
		return cadena;
	}
}