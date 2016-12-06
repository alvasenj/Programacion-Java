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

public class RBf extends Instruction {
	private int parametro;

	public RBf() {
		// TODO Auto-generated constructor stub
	}

	// Constructora de la clase Bf con un parametro.
	public RBf(String parametro) throws MVTrap {
		try{
		this.parametro = Integer.parseInt(parametro);
		}  catch (NumberFormatException e){
			throw new MVTrap("Parametro no valido para la instruccion RBf");
		}
	}

	// Metodo para ejecutar la operacion bf.
	public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		// Si el parametro del salto es mayor que 1 seguimos, si no lanzamos
		// excepcion pues daria error de hardware.
		if ((this.parametro != 0) && (this.parametro != 1)) {
			// Si la cima == 0
			if (pila.getContador() > 0) {
				if (pila.getCima() == 0) {
					int salto = ejecucion.getPC() + this.parametro;
					// Si la direccion del salto es negativa, lanzamos
					// excepcion,
					// caso contrario, ejecutamos el salto.
					if (salto >= 0) {
						ejecucion.setnextPC(salto);
						pila.eliminarCima();
					} else {
						ejecucion.pararMaquina();
						throw new MVTrap("Error ejecutando RBF "
								+ this.parametro + ": dirección incorrecta "
								+ salto);
					}
				} else {
					pila.eliminarCima();
				}
				// Si no hay elementos en la pila
			} else {
				throw new MVTrap(
						"Error ejecutando RBF: faltan operandos en la pila (hay "
								+ pila.getContador() + ")");
			}
		} else {
			ejecucion.pararMaquina();
			throw new MVTrap("Error ejecutando RBF " + this.parametro
					+ ": dirección incorrecta " + this.parametro);

		}
	}

	// Metodo para parsear la operacion rbf.
	public Instruction parse(String instruccion) throws MVTrap {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("RBF")) {
			if (words.length == 2) {
				parse = new RBf(words[1]);
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "RBF " + this.parametro;
		return cadena;
	}

}
