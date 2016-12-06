package tp.pr2.mv.cpu;



import tp.pr2.mv.Constantes;
import tp.pr2.mv.ExecutionManager;
import tp.pr2.mv.Memory;
import tp.pr2.mv.OperandStack;
import tp.pr2.mv.instructions.Instruction;

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

	private OperandStack pila;
	// NO SIRVE DE NADA EL BOOLEANO SI EL BUCLE DEL PROGRAMA NO ESTA EN CPU.
	private boolean terminado = false;
	private Memory memoria;
	private ProgramMV programa;
	private ExecutionManager ejecutor;

	// CONTRUCTORA DE LA CLASE CPU.
	public CPU() {
		this.pila = new OperandStack();
		this.memoria = new Memory();
		this.ejecutor = new ExecutionManager();
	}

	public void loadProgram(ProgramMV programa) {
		this.programa = programa;
	}

	public boolean step() {
		boolean noError = false;
		if (this.programa.existInstruction(ejecutor.getPC())) {
			noError = programa.InstructionExecute(ejecutor.getPC()).execute(
					this.memoria, this.pila, this.ejecutor);
			ejecutor.incrementPc();
			this.terminado = ejecutor.parada();
		} else {
			this.terminado = true;
			noError = true;
		}
		return noError;
	}

	public boolean step(Instruction instruction) {
		boolean error = false;
		error = instruction.execute(this.memoria, this.pila, this.ejecutor);
		return error;
	}

	public void setTerminado() {
		this.terminado = true;
	}

	public boolean programaTerminado() {
		return this.terminado;
	}

	public String escribirInstruccion() {
		return this.programa.escribirInstruction(ejecutor.getPC() + 1);
	}

	public String toString() {
		String cadena = "";

		cadena = "El estado de la maquina tras ejecutar la instruccion es:"
				+ Constantes.LINE_SEPARATOR + memoria.toString()
				+ Constantes.LINE_SEPARATOR + pila.toString();
		return cadena;
	}

}
