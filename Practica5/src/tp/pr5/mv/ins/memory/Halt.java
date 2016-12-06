package tp.pr5.mv.ins.memory;

import tp.pr5.mv.cpu.ExecutionManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.stategyOut.OutMethod;
import tp.pr5.mv.strategyIn.InMethod;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

/**
 * Es la clase que define el funcionamiento y parseo de la instruccion halt.
 */

public class Halt extends Instruction {

	public Halt() {
		// TODO Auto-generated constructor stub
	}

	// Metodo para ejecutar la operacion halt.
	@Override
	public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida) {
		// Detenemos la maquina.
		ejecucion.pararMaquina();
	}

	// Metodo para parsear la operacion halt.
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("HALT")) {
			if (words.length == 1) {

				parse = new Halt();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "HALT";
		return cadena;
	}

}