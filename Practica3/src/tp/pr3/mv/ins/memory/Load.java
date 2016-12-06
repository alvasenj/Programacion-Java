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
 * Es la clase que define el funcionamiento y parseo de la instruccion load.
 */

public class Load extends Instruction {

	private int parametro;

	public Load() {
		// TODO Auto-generated constructor stub
	}

	// Constructora de la clase Load con parametro.
	public Load(String parametro) {
		this.parametro = Integer.parseInt(parametro);
	}

	// Metodo para ejecutar la operacion load.
	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		boolean noError = true;
		// Si el parametro de la instruccion load es mayor que 0, guardamos el
		// entero.
		if (this.parametro >= 0) {
			pila.guardarEntero(memoria.load(this.parametro));
		} else {
			noError = false;
			ejecucion.pararMaquina();
			throw new MVTrap("Error ejecutando LOAD " + this.parametro
					+ ": dirección incorrecta (" + this.parametro + ")");
		}
		return noError;
	}

	// Metodo para parsear la operacion load.
	public Instruction parse(String instruccion) throws MVTrap {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("LOAD")) {
			if (words.length == 2) {
				parse = new Load(words[1]);
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "LOAD " + this.parametro;
		return cadena;
	}

}