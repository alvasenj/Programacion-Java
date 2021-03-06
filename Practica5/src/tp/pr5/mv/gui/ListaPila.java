package tp.pr5.mv.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

//Clase que define nuestra Lista grafica que contendra la pila.
public class ListaPila extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static DefaultListModel modelo = new DefaultListModel();

	public ListaPila() {

		// Definimos el texto para que salga alineado a la izq.
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.LEFT);

		// Creamos una lista con el modelo de tipo DefaultListModel.
		JList lista = new JList(modelo);
		JScrollPane tablaPane = new JScrollPane(lista);

		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		this.add(tablaPane);
	}

	public void addElement(String numero) {
		// modelo.addElement(numero);
		modelo.add(0, numero);
	}

	public void deleteElement() {
		modelo.remove(0);
	}

	public void reset() {
		
		modelo.clear();
		this.removeAll();
		// TODO Auto-generated method stub
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.LEFT);

		// Creamos una lista con el modelo de tipo DefaultListModel.
		JList lista = new JList(modelo);
		JScrollPane tablaPane = new JScrollPane(lista);

		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		this.add(tablaPane);
	}

}
