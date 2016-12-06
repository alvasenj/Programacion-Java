package tp.pr4.mv.ins;

import tp.pr4.mv.exceptions.MVTrap;
import tp.pr4.mv.ins.arithmetic.*;
import tp.pr4.mv.ins.booleans.*;
import tp.pr4.mv.ins.compare.*;
import tp.pr4.mv.ins.jumps.*;
import tp.pr4.mv.ins.memory.*;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

/**
 * 
 * Es la clase encargada de parsear un string que contiene una posible
 * instruccion. Concretamente dispone de un metodo Instruction parse(String s)
 * que devuelve la instruccion almacenada en s o bien null si s no representa
 * ninguna instruccion de las permitidas.
 */

// Clase que define el parseador de todas las Instrucciones.
public class InstructionParser {

	// Array con todas las posibles instrucciones de la MV.
	private static Instruction[] listainstrucciones = { new Push(), new Add(),
			new Div(), new Mult(), new Sub(), new And(), new Not(), new Or(),
			new Eq(), new Gt(), new Le(), new Lt(), new Bf(), new Bt(),
			new Jump(), new Dup(), new Flip(), new Halt(), new Load(),
			new Neg(), new Out(), new Store(), new Pop(), new JumpInd(),
			new LoadInd(), new StoreInd(), new In(), new RBt(), new RBf(),
			new RJump() };

	// metodo que devuelve una instruccion inicializada si lo que se ha
	// introducido coincide con una instruccion.
	public static Instruction parse(String instruccion) throws MVTrap {
		Instruction instruc = null;
		boolean insparseada = false;

		for (int i = 0; i < listainstrucciones.length; i++) {
			if (instruc == null) {
				if (listainstrucciones[i].parse(instruccion) != null) {
					insparseada = true;
					instruc = listainstrucciones[i].parse(instruccion);
				}

			}
		}

		// Si no se encuentra ninguna instruccion con las palabras que el
		// usuario ha introducido.
		if ((!insparseada) && (!instruccion.equalsIgnoreCase("END"))) {
			throw new MVTrap("Error: Instruccion incorrecta");
		}

		return instruc;
	}

}
