package tp.pr2.mv.instructions;

import tp.pr2.mv.instructions.arithmetic.*;
import tp.pr2.mv.instructions.booleans.*;
import tp.pr2.mv.instructions.compare.*;
import tp.pr2.mv.instructions.jumps.*;
import tp.pr2.mv.instructions.memory.*;

/**
 @author Javier Druet
 @author Álvaro Asenjo 
 */

/**
 * 
 * Es la clase encargada de parsear un string que contiene una posible
 * instrucciÃ³n. Concretamente dispone de un mÃ©todo Instruction parse(String s)
 * que devuelve la instrucciÃ³n almacenada en s o bien null si s no representa
 * ninguna instrucci Ã³n de las permitidas.
 */
public class InstructionParser {

	private static Instruction[] listainstrucciones = { new Push(), new Add(),
			new Div(), new Mult(), new Sub(), new And(), new Not(), new Or(),
			new Eq(), new Gt(), new Le(), new Lt(), new Bf(), new Bt(),
			new Jump(), new Dup(), new Flip(), new Halt(), new Load(),
			new Neg(), new Out(), new Store(), new Pop(), new RBt(), new RBf(), new RJump() };

	// metodo que devuelve una instruccion inicializada si lo que se ha
	// introducido coincide con una instruccion.

	public static Instruction parse(String instruccion) {
		Instruction instruc = null;
		boolean parse = false;

		for (int i = 0; i < listainstrucciones.length; i++) {
			if (instruc != null) {
				if (listainstrucciones[i].parse(instruccion) != null) {
					parse = true;
					instruc = listainstrucciones[i].parse(instruccion);
				}
			}
		}
		return instruc;
	}
}
