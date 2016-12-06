package tp.pr5.mv.controllers;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import tp.pr5.mv.cpu.CPU;
import tp.pr5.mv.exceptions.MVTrap;
import tp.pr5.mv.ins.memory.Pop;
import tp.pr5.mv.ins.memory.Push;
import tp.pr5.mv.ins.memory.Store;
import tp.pr5.mv.views.WindowMain;

public class WindowController {

	private CPU computador;

	public WindowController(CPU computador) {
		this.computador = computador;
	}

	public void addObs(WindowMain ventana) {
		computador.addObs(ventana);
	}

	public void runButton() {

	}

	public void stepButton() {
		computador.step();
	}

	public void deleteCima() {
		this.computador.step(new Pop());
	}

	public void quit() {
		this.computador.detenerMaquina();
	}

	public void addCima(String numero) throws MVTrap {
		this.computador.step(new Push(numero));
	}

	public void addCelda(String posicion, String valor) {
		// this.computador.step(new Push(valor));
		this.computador.step(new Store(posicion, valor));
	}

	public void definirTexto(JTextArea j) {
		j.setText("");
		this.computador.definirTexto(j);
	}

	public void definirTextoSalida(JTextArea j) {
		j.setText("");
		this.computador.definirTextoSalida(j);
	}

	public void actualizarPrograma(JTextArea j) {
		j.setText("");
		String[] programaEnString = this.computador.devolverPrograma();

		if (programaEnString != null) {
			for (int i = 0; i < programaEnString.length; i++) {
				j.append(programaEnString[i]);
			}
		}
	}

	public void quitButton() {
		String[] botones = { "Si", "No" };
		int seleccion = JOptionPane.showOptionDialog(null,
				"¿Seguro que quieres salir?", "Atención",
				JOptionPane.YES_NO_OPTION, 0, null, botones, botones);
		// SI == SALIR, CC == CANCELAR SALIDA
		if (seleccion == 0) {
			computador.cerrarArchivo();
			computador.detenerMaquina();
			System.exit(0);
		}
		// actualizarInterfaz();
	}

	public boolean isQuit() {
		return this.computador.isQuit();
	}

	public void reset() {
		// TODO Auto-generated method stub
		this.computador.reset();
	}

	public void breakPoint(int numero) {
		// TODO Auto-generated method stub
		this.computador.breakPoint(numero);
	}

	

	
	
}
