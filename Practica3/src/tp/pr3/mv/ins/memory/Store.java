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
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		boolean noError = true;
		// si la posicion donde guardar es mayor o igual que 0.
		if (this.parametro >= 0) {
			memoria.store(this.parametro, pila.getCima());
			pila.eliminarCima();
		} else {
			noError = false;
			ejecucion.pararMaquina();
			throw new MVTrap("Error ejecutando STORE " + this.parametro
					+ ": dirección incorrecta (" + this.parametro + ")");
		}

		return noError;
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