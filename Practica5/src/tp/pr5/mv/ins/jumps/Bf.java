package tp.pr5.mv.ins.jumps;

import tp.pr5.mv.cpu.ExecutionManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exceptions.MVTrap;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.stategyOut.OutMethod;
import tp.pr5.mv.strategyIn.InMethod;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

/**
 * Es la clase que define el funcionamiento y parseo de la instruccion bf.
 */

public class Bf extends Instruction {
	private int parametro;

	public Bf() {
		// TODO Auto-generated constructor stub
	}

	// Constructora de la clase Bf con un parametro.
	public Bf(String parametro) throws MVTrap {
		try{
		this.parametro = Integer.parseInt(parametro);
		}  catch (NumberFormatException e){
			throw new MVTrap("Parametro no valido para la instruccion Bf");
		}
	}

	// Metodo para ejecutar la operacion bf.
	@Override
	public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		// Si se ha introducido como parametro algo mayor o igual que 0.
		if (this.parametro >= 0) {
			// Si la cima = 0, realizamos el salto, cc eliminamos la cima y
			// continuamos la ejecucion normal.
			if (pila.getContador() > 0) {
				if (pila.getCima() == 0) {
					ejecucion.setnextPC(this.parametro);
					pila.eliminarCima();

				} else {
					pila.eliminarCima();
				}
				// Si no hay elementos en la pila
			} else {
				throw new MVTrap(
						"Error ejecutando BF: faltan operandos en la pila (hay "
								+ pila.getContador() + ")");
			}
		} else {
			ejecucion.pararMaquina();
			throw new MVTrap("Error ejecutando BF " + this.parametro
					+ ": dirección incorrecta " + this.parametro);

		}
	}

	// Metodo para parsear la operacion bf.
	public Instruction parse(String instruccion) throws MVTrap {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("BF")) {
			if (words.length == 2) {
				parse = new Bf(words[1]);
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "BF " + this.parametro;
		return cadena;
	}

}
