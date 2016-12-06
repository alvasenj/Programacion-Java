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
 * Es la clase que define el funcionamiento y parseo de la instruccion out.
 */

public class Out extends Instruction {

	public Out() {
		// TODO Auto-generated constructor stub
	}

	// Metodo para ejecutar la operacion out.
	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida) {
		boolean noError = true;
		// SI SE INTROCUDE LA INSTRUCCION OUT, se llamara al write del metodo de
		// salida
		// si la pila tiene un operando
		if ((pila.getContador() > 0)) {
			char caracter = (char) pila.getCima();
			pila.eliminarCima();
			try {
				mSalida.writeChar(caracter);
				// Si hay cualquier problema al abrir el fichero de salida.
			} catch (IOException e) {
				System.err.println("Error al abrir el fichero de salida");
			}
		} else
			noError = false;
		return noError;
	}

	// Metodo para parsear la operacion out.
	public Instruction parse(String instruccion) {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("OUT")) {
			if (words.length == 1) {
				parse = new Out();
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "OUT";
		return cadena;
	}
}