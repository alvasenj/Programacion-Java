package tp.pr3.mv.cpu;

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
	private boolean halt = false;

	public ExecutionManager() {
		this.currentPC = 0;
		this.nextPC = 0;
	}

	// Pone el atributo halt a true cuando se detiene la maquina.
	public void pararMaquina() {
		this.halt = true;
	}

	// modifica el next pc al valor que se le indique.
	public void setnextPC(int pc1) {
		this.nextPC = pc1 - 1;
		this.currentPC = nextPC;
	}

	// Devuelve el pc actual.
	public int getPC() {
		return this.currentPC;
	}

	// incrementa en 1 los valores de los pcs.
	public void incrementPc() {
		this.currentPC++;
		this.nextPC++;
	}

	// metodo que devuelve si la maquina esta parada o no.
	public boolean isHalted() {
		return this.halt;
	}
}
