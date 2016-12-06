package tp.pr3.mv.ins.memory;

import java.io.IOException;

import tp.pr3.mv.cpu.ExecutionManager;
import tp.pr3.mv.cpu.Memory;
import tp.pr3.mv.cpu.OperandStack;
import tp.pr3.mv.ins.Instruction;
import tp.pr3.mv.stategyOut.OutMethod;
import tp.pr3.mv.strategyIn.InMethod;

/**
 @author Javier Druet
 @author Álvaro Asenjo 
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
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida) {
		boolean noError = true;
		// Intentamos ejecutar la instruccion In segun el metodo de entrada que
		// nos llegue
		try {
			int i = mEntrada.readChar();
			pila.guardarEntero(i);
			// Si hay cualquier error al acceder al fichero de entrada.
		} catch (IOException e) {
			System.err.println("Error en el fichero de entrada");
		}
		return noError;
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