package tp.pr4.mv.gui;

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

		//Definimos el texto para que salga alineado a la izq.
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.LEFT);

		//Creamos una lista con el modelo de tipo DefaultListModel.
		JList lista = new JList(modelo);
		JScrollPane tablaPane = new JScrollPane(lista);

		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		this.add(tablaPane);
	}

	//Metodo que agrega un String al modelo, el cual se limpia, y vuelve a guardar sus datos.
	public void agregar(String[] elementos) {
		modelo.clear();
		for (int i = 0; i < elementos.length; i++) {
			modelo.addElement(elementos[i]);
		}
	}

}
