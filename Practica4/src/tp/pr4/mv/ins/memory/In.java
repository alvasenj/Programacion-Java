package tp.pr4.mv.ins.memory;

import java.io.IOException;

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
 * Es la clase que define el funcionamiento y parseo de la instruccion in.
 */

public class In extends Instruction {

	public In() {
		// TODO Auto-generated constructor stub
	}

	// Metodo para ejecutar la operacion in.
	@Override
	public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida) {
		// Intentamos ejecutar la instruccion In segun el metodo de entrada que
		// nos llegue
		try {
			int i = mEntrada.readChar();
			pila.guardarEntero(i);
			// Si hay cualquier error al acceder al fichero de entrada.
		} catch (IOException e) {
			System.err.println("Error en el metodo de entrada");
		}
	}

	// Metodo para parsear la operacion in.
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("IN")) {
			if (words.length == 1) {
				parse = new In();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "IN";
		return cadena;
	}
}