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
 * Es la clase que define el funcionamiento y parseo de la instruccion push.
 */

public class Push extends Instruction {

	private int parametro;

	public Push() {
		super();// TODO Auto-generated constructor stub
	}

	// Constructora de la clase Load con parametro.
	public Push(String parametro) throws MVTrap {
		try{
		this.parametro = Integer.parseInt(parametro);
		} catch (NumberFormatException e){
			throw new MVTrap("Parametro no valido para la instruccion Push");
		}
	}

	// Metodo para ejecutar la operacion push.
	@Override
	public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida) {
		pila.guardarEntero(this.parametro);
	}

	// Metodo para parsear la operacion push.
	@Override
	public Instruction parse(String instruccion) throws MVTrap {
		String[] words = instruccion.split(" ");
		Instruction parse = null;
		if (words[0].equalsIgnoreCase("PUSH")) {
			if (words.length == 2) {
				try {
					parse = new Push(words[1]);
				} catch (MVTrap e) {
					throw new MVTrap(e.getMessage());
				}
			}
		}
		return parse;
	}

	public String toString() {
		String cadena = "PUSH " + this.parametro;
		return cadena;
	}

}
