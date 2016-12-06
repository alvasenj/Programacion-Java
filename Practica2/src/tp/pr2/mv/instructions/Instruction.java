package tp.pr2.mv.instructions;

import tp.pr2.mv.ExecutionManager;
import tp.pr2.mv.Memory;
import tp.pr2.mv.OperandStack;

/**
 @author Javier Druet
 @author Álvaro Asenjo 
 * 
 */

/**
 * 
 * Implementa las distintas instrucciones que puede manejar nuestra máquina
 * virtual. Para representar las distintas instrucciones utiliza un tipo
 * enumerado.
 */

public abstract class Instruction {

	public Instruction() {
		// TODO Auto-generated constructor stub
	}

	abstract public boolean execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion);

	abstract public Instruction parse(String instruccion);

}