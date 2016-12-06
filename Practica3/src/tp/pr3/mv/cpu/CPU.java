package tp.pr3.mv.cpu;

import tp.pr3.mv.Constantes;
import tp.pr3.mv.exceptions.MVTrap;
import tp.pr3.mv.ins.Instruction;
import tp.pr3.mv.stategyOut.OutMethod;
import tp.pr3.mv.strategyIn.InMethod;

/**
 @author Javier Druet
 @author Álvaro Asenjo 
 * 
 */

/**
 * 
 * Es la unidad de procesamiento de nuestra máquina virtual. Contiene una
 * memoria, una pila de operandos y una varible booleana para determinar si la
 * ejecución ha terminado, es decir, si se ha ejecutado la instrucción HALT. En
 * esta clase se encuentra el método público boolean execute(Instruction instr),
 * que es el encargado de ejecutar la instrucción que le llega como parámetro
 * modicando convenientemente la memoria y/o la pila de operandos. Si la
 * ejecución genera un error el método devuelve false.
 */

public class CPU {

	private InMethod in;
	private OutMethod out;
	private OperandStack pila;
	private Memory memoria;
	private ProgramMV programa;
	private ExecutionManager ejecutor;

	// CONTRUCTORA DE LA CLASE CPU.
	public CPU() {
		this.pila = new OperandStack();
		this.memoria = new Memory();
		this.ejecutor = new ExecutionManager();
	}

	// CONSTRUCTORA DE LA CPU CON METODOS DE ENTRADA Y SALIDA.
	public CPU(InMethod in, OutMethod out) {
		this.in = in;
		this.out = out;
		this.pila = new OperandStack();
		this.memoria = new Memory();
		this.ejecutor = new ExecutionManager();
	}

	// METODO QUE AÑADE A LA CPU EL PROGRAMA
	public void loadProgram(ProgramMV programa) {
		this.programa = programa;
	}

	// METODO PARA EJECUTAR UNA INSTRUCCION DEL PROGRAMA.
	public boolean step() {
		boolean noError = false;
		int posicion = ejecutor.getPC();
		boolean existeInstruccion = this.programa.existInstruction(posicion);
		boolean terminado = this.ejecutor.isHalted();
		if (!terminado) {
			if (existeInstruccion) {

				// Escribimos la instruccion que se ve a empezar a ejecutar
				System.out.println(this.programa.escribirInstruction(ejecutor
						.getPC() + 1));

				try {
					// Intentamos ejecutar la instruccion.
					noError = programa.InstructionExecute(posicion).execute(
							this.memoria, this.pila, this.ejecutor, this.in,
							this.out);

					if (noError) {
						// Si no da error, se incrementa el pc, y se imprime el
						// estado actual de la MV
						ejecutor.incrementPc();
						posicion = ejecutor.getPC();
						System.out.println(this.toString());
					}

				} catch (MVTrap e) {
					// Si ha habido cualquier problema con las instrucciones, se
					// lanzara una excepcion que se trata aqui.
					System.err.println(e.getMessage());

					// Si hemos puesto el comando quit, el estado de la maquina
					// debe detenerse.
				} finally {
					terminado = ejecutor.isHalted();
				}

			} else {
				// Si no hay mas instrucciones en el programa, se detiene la
				// ejecucion.
				this.ejecutor.pararMaquina();
				noError = true;
			}

			// If para comprobar despues de la ejecucion de cada instruccion, si
			// quedan nuevas instrucciones por ejecutar.
			if (!(existeInstruccion = this.programa.existInstruction(posicion))) {
				this.ejecutor.pararMaquina();
			}
		}
		return noError;
	}

	// METODO PARA EJECUTAR LOS COMANDOS DE LA PARTE OPCIONAL DE LA PRACTICA 2
	public boolean step(Instruction instruction) {
		boolean error = false;

		try {
			error = instruction.execute(memoria, pila, this.ejecutor, this.in,
					this.out);
		} catch (MVTrap e) {
			// si introduces una instruccion invalida, se mostraran los errores aqui.
			System.err.println(e.getMessage());
		}

		return error;
	}

	// Devuelve un booleano si la maquina esta parada.
	public boolean isQuit() {
		return this.ejecutor.isHalted();
	}

	// metodo para detener la maquina.
	public void detenerMaquina() {
		this.ejecutor.pararMaquina();
	}

	public String toString() {
		String cadena = "";

		cadena = "El estado de la maquina tras ejecutar la instruccion es:"
				+ Constantes.LINE_SEPARATOR + memoria.toString()
				+ Constantes.LINE_SEPARATOR + pila.toString();
		return cadena;
	}

}
