package tp.pr2.mv;

/**
 @author Javier Druet
 @author Álvaro Asenjo 
 */

/**
 * Es la clase que se encarga de la ejecucion del ProgramMV
 */

public class ExecutionManager {
	private int currentPC;
	private int nextPC;
	boolean halt = false;

	public ExecutionManager() {
		this.currentPC = 0;
		this.nextPC = 0;
	}

	public void pararMaquina() {
		this.halt = true;
	}

	public void setnextPC(int pc1) {
		this.nextPC = pc1 - 1;
		this.currentPC = nextPC;
		//incrementPc();
	}

	public int getPC() {
		return this.currentPC;
	}

	public void incrementPc() {
		this.currentPC++;
		this.nextPC++;
	}

	public boolean parada() {
		return this.halt;
	}
}
