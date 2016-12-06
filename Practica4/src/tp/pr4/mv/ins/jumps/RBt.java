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

public class RBt extends Instruction {
	private int parametro;

	public RBt() {
		// TODO Auto-generated constructor stub
	}

	// Constructora de la clase Bt con un parametro.
	public RBt(String parametro) {
		this.parametro = Integer.parseInt(parametro);
	}

	// Metodo para ejecutar la operacion bt.
	@Override
	public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		// Si el parametro del salto es mayor que 1 seguimos, si no lanzamos
		// excepcion pues daria error de hardware.
		if ((this.parametro != 0) && (this.parametro != 1)) {
			// Si la cima > 0
			if (pila.getContador() > 0) {
				if (pila.getCima() > 0) {
					int salto = ejecucion.getPC() + this.parametro;
					// Si la direccion del salto es negativa, lanzamos
					// excepcion,
					// caso contrario, ejecutamos el salto.
					if (salto >= 0) {
						ejecucion.setnextPC(salto);
						pila.eliminarCima();
					} else {
						throw new MVTrap("Error ejecutando RBT "
								+ this.parametro + ": dirección incorrecta "
								+ salto);
					}
				} else {
					pila.eliminarCima();
				}
				// Si no hay elementos en la pila
			} else {
				throw new MVTrap(
						"Error ejecutando RBT: faltan operandos en la pila (hay "
								+ pila.getContador() + ")");
			}
		} else {
			ejecucion.pararMaquina();
			throw new MVTrap("Error ejecutando RBT " + this.parametro
					+ ": dirección incorrecta " + this.parametro);
		}
	}

	// Metodo para parsear la operacionr rbt.
	@Override
	public Instruction parse(String instruccion) throws MVTrap {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("RBT")) {
			if (words.length == 2) {
				parse = new RBt(words[1]);
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "RBT " + this.parametro;
		return cadena;
	}

}
