package tp.pr4.mv.cpu;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import tp.pr4.mv.Constantes;
import tp.pr4.mv.exceptions.MVTrap;
import tp.pr4.mv.ins.Instruction;
import tp.pr4.mv.stategyOut.OutMethod;
import tp.pr4.mv.strategyIn.InMethod;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
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
	private boolean esVentana = false;

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
		boolean noError = true;
		int posicion = ejecutor.getPC();
		boolean existeInstruccion;
		boolean terminado = this.ejecutor.isHalted();

		if (programa != null) {
			existeInstruccion = this.programa.existInstruction(posicion);
		} else
			existeInstruccion = false;

		if (!terminado) {
			if (existeInstruccion) {

				// Escribimos la instruccion que se ve a empezar a ejecutar
				if(!this.esVentana){
				System.out.println(this.programa.escribirInstruction(ejecutor
						.getPC() + 1));
				}

				try {
					// Intentamos ejecutar la instruccion.
					programa.InstructionExecute(posicion).execute(this.memoria,
							this.pila, this.ejecutor, this.in, this.out);

					// Si no da error, se incrementa el pc, y se imprime el
					// estado actual de la MV
					ejecutor.incrementPc();
					posicion = ejecutor.getPC();
					if(!this.esVentana){
					System.out.println(this.toString());
					}

				} catch (MVTrap e) {
					// Si ha habido cualquier problema con las instrucciones, se
					// lanzara una excepcion que se trata aqui.
					if(!this.esVentana){
					noError = false;
					System.err.println(e.getMessage());
					} else {
						noError = false;
						JOptionPane
						.showMessageDialog(null, e.getMessage());
					}

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
			if ((this.programa != null)
					&& (!(existeInstruccion = this.programa
							.existInstruction(posicion)))) {
				this.ejecutor.pararMaquina();
			}
		}
		return noError;
	}

	// METODO PARA EJECUTAR LOS COMANDOS DE LA PARTE OPCIONAL DE LA PRACTICA 2
	public boolean step(Instruction instruction) {
		boolean error = false;

		try {
			instruction
					.execute(memoria, pila, this.ejecutor, this.in, this.out);

		} catch (MVTrap e) {
			// si introduces una instruccion invalida, se mostraran los errores
			// aqui.
			error = true;
			if(!this.esVentana){
			System.err.println(e.getMessage());
			} else {
				JOptionPane
				.showMessageDialog(null, e.getMessage());
			}
		}

		return error;
	}

	// Devuelve un booleano si la maquina esta parada.
	public boolean isQuit() {
		return this.ejecutor.isHalted();
	}
	
	public void definirTexto(JTextArea j){
		this.in.setTextoVentana(j);
	}
	
	public void definirTextoSalida(JTextArea j){
		this.out.setTextoVentana(j);
	}

	// metodo para detener la maquina.
	public void detenerMaquina() {
		this.ejecutor.pararMaquina();
	}

	//Devuelve en un array de Strings las instrucciones del programa.
	public String[] devolverPrograma() {
		String[] programa = null;

		if ((this.programa != null) && (this.programa.existInstruction(0))) {
			programa = new String[this.programa.getContador()];
			//Recorremos todo el programa y añadimos el numero de instruccion.
			for (int i = 0; i < this.programa.getContador(); i++) {
				programa[i] = "     " + i + ":   "
						+ this.programa.getInstruction(i)
						+ Constantes.LINE_SEPARATOR;
			}
			calcularAsterisco(programa);
		}

		return programa;
	}

	//Devuelve en un array de enteros las posiciones de los datos guardados en memoria.
	public int[] devolverPosiciones() {
		int[] memorias = new int[this.memoria.getContador()];
		//Recorremos toda la memoria y la vamos convirtiendo a Strings.
		for (int i = 0; i < this.memoria.getContador(); i++) {
			memorias[i] = this.memoria.devolverPosicion(i);
		}
		return memorias;
	}

	//Devuelve en un array de enteros lo valores de los datos guardados en memoria.
	public int[] devolverValores() {
		int[] memorias = new int[this.memoria.getContador()];
		//Recorremos toda la memoria y la vamos convirtiendo a Strings.
		for (int i = 0; i < this.memoria.getContador(); i++) {
			memorias[i] = this.memoria.devolverValor(i);
		}
		return memorias;
	}

	//Metodo que calcula en que posicion debe ir el asterisco de la interfaz, y lo modifica para su muestra.
	public void calcularAsterisco(String[] programa) {
		int posicion = this.ejecutor.getPC();

		if (posicion == 0) {
			// Si es la primera instruccion, solo ponemos el asterisco.
			String cadena = programa[posicion];
			programa[posicion] = cadena.replaceFirst(" ", "*");
		} else if (posicion == this.programa.getContador()){
			// Si hemos terminado la ejecucion borramos el asterisco.
			String cadenaAnterior = programa[posicion - 1];
			programa[posicion - 1] = cadenaAnterior.replace("*", " ");
		}else if ((posicion > 0) && (posicion < this.programa.getContador())) {
			// Si estamos en una instruccion que no es la primera ponemos
			// asterisco y lo quitamos de la anterior.
			String cadenaAnterior = programa[posicion - 1];
			programa[posicion - 1] = cadenaAnterior.replace("*", " ");
			String cadena = programa[posicion];
			programa[posicion] = cadena.replaceFirst(" ", "*");
		}
	}
	
	//Metodo que avisa de que el modo ventana esta activo.
	public void setModoVentana(){
		this.esVentana = true;
	}

	//Metodo que devuelve los datos de la pila en un Array de Strings.
	public String[] devolverPila() {
		String[] pilaEnString = new String[this.pila.getContador()];
		OperandStack pilaux = new OperandStack();
		int contador = this.pila.getContador();

		//Recorremos toda la pila (convirtiendola en String) y la vamos vaciando en una pila auxiliar.
		for (int i = 0; i < contador; i++) {
			pilaEnString[i] = this.pila.devolverCima();
			pilaux.guardarEntero(this.pila.getCima());
			this.pila.eliminarCima();
		}

		//Recomponemos la pila anterior para que mantenga su estado inicial.
		while (pilaux.getContador() != 0) {
			this.pila.guardarEntero(pilaux.getCima());
			pilaux.eliminarCima();
		}

		return pilaEnString;
	}
	
	//Llamamos al metodo cerrarArchivo del metodo de Salida.
	public void cerrarArchivo(){
		this.out.cerrarArchivo();
	}

	public String toString() {
		String cadena = "";

		cadena = "El estado de la maquina tras ejecutar la instruccion es:"
				+ Constantes.LINE_SEPARATOR + memoria.toString()
				+ Constantes.LINE_SEPARATOR + pila.toString();
		return cadena;
	}

}
