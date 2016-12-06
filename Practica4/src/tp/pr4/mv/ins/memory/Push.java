package tp.pr4.mv.ins.memory;

import tp.pr4.mv.cpu.ExecutionManager;
import tp.pr4.mv.cpu.Memory;
import tp.pr4.mv.cpu.OperandStack;
import tp.pr4.mv.ins.Instruction;
import tp.pr4.mv.stategyOut.OutMethod;
import tp.pr4.mv.strategyIn.InMethod;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

/**
 * Es la clase que define el funcionamiento y parseo de la instruccion push.
 */

public class Push extends Instruction {

	private int parametro;

	public Push() {
		super();// TODO Auto-generated constructor stub
	}

	// Constructora de la clase Load con parametro.
	public Push(String parametro) {
		this.parametro = Integer.parseInt(parametro);
	}

	// Metodo para ejecutar la operacion push.
	@Override
	public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida) {
		pila.guardarEntero(this.parametro);
	}

	// Metodo para parsear la operacion push.
	@Override
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("PUSH")) {
			if (words.length == 2) {
				parse = new Push(words[1]);
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "PUSH " + this.parametro;
		return cadena;
	}

}
