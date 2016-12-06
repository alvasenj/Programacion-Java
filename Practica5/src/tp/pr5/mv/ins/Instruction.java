package tp.pr5.mv.ins;

import tp.pr5.mv.cpu.ExecutionManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exceptions.MVTrap;
import tp.pr5.mv.stategyOut.OutMethod;
import tp.pr5.mv.strategyIn.InMethod;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

/**
 * 
 * Implementa las distintas instrucciones que puede manejar nuestra máquina
 * virtual. Para representar las distintas instrucciones utiliza un tipo
 * enumerado.
 */

// Clase abstracta que define el funcionamiento de todas las instrucciones.
// No podemos convertirla en interfaz porque necesitamos la constructora.
public abstract class Instruction {

	public Instruction() {
	}

	// Metodo que ejecutara cada instruccion individualmente, le pasas la
	// memoria, la pila, el ejecutor, un metodo de entrada y un metodo de
	// salida.
	abstract public void execute(Memory memoria, OperandStack pila,
			ExecutionManager ejecucion, InMethod mEntrada, OutMethod mSalida)
			throws MVTrap;

	// Metodo para parsear individualmente cada instruccion.
	abstract public Instruction parse(String instruccion) throws MVTrap;

}