package tp.pr3.mv.ins.jumps;

import tp.pr3.mv.cpu.ExecutionManager;
import tp.pr3.mv.cpu.Memory;
import tp.pr3.mv.cpu.OperandStack;
import tp.pr3.mv.exceptions.MVTrap;
import tp.pr3.mv.ins.Instruction;
import tp.pr3.mv.stategyOut.OutMethod;
import tp.pr3.mv.strategyIn.InMethod;

/**
 * @author Javier Druet
 * @author Álvaro Asenjo
 */

public class RJump extends Instruction {
	private int parametro;

	public RJump() {
		// TODO Auto-generated constructor stub
	}

	// Constructora de la clase Jump con un parametro.
	public RJump(String parametro) {
		this.parametro = Integer.parseInt(parametro);
	}

	// Metodo para ejecutar la operacion jump.
	@Override
	public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		boolean noError = true;
		// Si el parametro del salto es mayor que 1 seguimos, si no lanzamos
		// excepcion pues daria error de hardware.
		if ((this.parametro != 0) && (this.parametro != 1)) {
			int salto = ejecucion.getPC() + this.parametro;
			// Si la direccion del salto es negativa, lanzamos excepcion, caso
			// contrario, ejecutamos el salto.
			if (salto >= 0) {
				ejecucion.setnextPC(salto);
			} else {
				throw new MVTrap("Error ejecutando RJUMP " + this.parametro
						+ ": dirección incorrecta " + salto);
			}
		} else {
			noError = false;
			ejecucion.pararMaquina();
			throw new MVTrap("Error ejecutando RJUMP " + this.parametro
					+ ": dirección incorrecta " + this.parametro);
		}
		return noError;
	}

	// Metodo para parsear la operacion jump.
	public Instruction parse(String instruccion) throws MVTrap {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("RJUMP")) {
			if (words.length == 2) {
				parse = new RJump(words[1]);
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "RJUMP " + this.parametro;
		return cadena;
	}

}