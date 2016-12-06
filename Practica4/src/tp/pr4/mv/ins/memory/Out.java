package tp.pr4.mv.ins.memory;

import java.io.IOException;

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
 * Es la clase que define el funcionamiento y parseo de la instruccion out.
 */

public class Out extends Instruction {

	public Out() {
		// TODO Auto-generated constructor stub
	}

	// Metodo para ejecutar la operacion out.
	@Override
	public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
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
		} else {
			throw new MVTrap(
					"Error ejecutando OUT: faltan operandos en la pila (hay "
							+ pila.getContador() + ")");
		}
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