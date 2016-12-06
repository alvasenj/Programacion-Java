package tp.pr5.mv.ins.memory;

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
 * Es la clase que define el funcionamiento y parseo de la instruccion store.
 */

public class Store extends Instruction {
	private int parametro;
	private String valor = "";

	public Store() {
		super();// TODO Auto-generated constructor stub
	}

	// Constructora de la clase Load con parametro.
	public Store(String parametro) throws MVTrap {
		try{
		this.parametro = Integer.parseInt(parametro);
		} catch (NumberFormatException e){
			throw new MVTrap("Parametro no valido para la instruccion Store");
		}
	}

	public Store(String posicion, String valor) {
		this.parametro = Integer.parseInt(posicion);
		this.valor = valor;
	}

	// Metodo para ejecutar la operacion store.
	@Override
	public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap {
		// si la posicion donde guardar es mayor o igual que 0.
		if (pila.getContador() > 0) {
			if (this.parametro >= 0 && this.valor.equalsIgnoreCase("")) {
				memoria.store(this.parametro, pila.getCima());
				pila.eliminarCima();
			} else if (this.parametro >= 0 && !this.valor.equalsIgnoreCase("")) {
				memoria.store(this.parametro, Integer.parseInt(valor));
			} else {
				ejecucion.pararMaquina();
				throw new MVTrap("Error ejecutando STORE " + this.parametro
						+ ": dirección incorrecta (" + this.parametro + ")");
			}

		} else if (this.parametro >= 0 && !this.valor.equalsIgnoreCase("")) {
			if (this.parametro >= 0) {
				memoria.store(this.parametro, Integer.parseInt(valor));
			} else {
				ejecucion.pararMaquina();
				throw new MVTrap("Error ejecutando STORE " + this.parametro
						+ ": dirección incorrecta (" + this.parametro + ")");
			}
		} else {
			throw new MVTrap(
					"Error ejecutando STORE: faltan operandos en la pila (hay "
							+ pila.getContador() + ")");
		}
	}

	// Metodo para parsear la operacion store.
	public Instruction parse(String instruccion) throws MVTrap {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("STORE")) {
			if (words.length == 2) {
				parse = new Store(words[1]);
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "STORE " + this.parametro;
		return cadena;
	}
}