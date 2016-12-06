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
 * Es la clase que define el funcionamiento y parseo de la instruccion jump.
 */

public class Jump extends Instruction {
	private int parametro;

	public Jump() {
		// TODO Auto-generated constructor stub
	}

	// Constructora de la clase Jump con un parametro.
	public Jump(String parametro) {
		this.parametro = Integer.parseInt(parametro);
	}

	// Metodo para ejecutar la operacion jump.
	@Override
	public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		// Si se ha introducido como parametro algo mayor o igual que 0, cc
		// lanzamos excepcion a salto imposible.
		if (this.parametro >= 0) {
			ejecucion.setnextPC(this.parametro);
		} else {
			ejecucion.pararMaquina();
			throw new MVTrap("Error ejecutando JUMP " + this.parametro
					+ ": dirección incorrecta " + this.parametro);
		}
	}

	// Metodo para parsear la operacion jump.
	public Instruction parse(String instruccion) throws MVTrap {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("JUMP")) {
			if (words.length == 2) {
				parse = new Jump(words[1]);
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "JUMP " + this.parametro;
		return cadena;
	}

}
