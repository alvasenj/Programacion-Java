package tp.pr5.mv.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import tp.pr5.mv.cpu.CeldaMemory;

//Clase que define nuestra tabla para la memoria.
public class TablaMemoria extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static ModeloDeTabla modelo;
	private JTable tabla;

	public TablaMemoria() {

		// creamos un nuevo modelo de Tabla.
		modelo = new ModeloDeTabla();

		// Centramos los datos de la tabla.
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);

		// Creamos una tabla con nuestro nuevo modelo.
		tabla = new JTable(modelo);
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
		JScrollPane tablaPane = new JScrollPane(tabla);
		tabla.setBackground(null);

		this.setLayout(new BorderLayout());
		this.add(tablaPane);

	}

	public void addCelda(int posicion, int valor) {
		modelo.add(new CeldaMemory(posicion, valor));
	}

	public int devolverNumero() {
		// TODO Auto-generated method stub
		return modelo.delvoverNumero(tabla.getSelectedRow());
	}

	public void reset() {
		// TODO Auto-generated method stub
		
		modelo.clear();
		this.removeAll();

		// Centramos los datos de la tabla.
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);

		// Creamos una tabla con nuestro nuevo modelo.
		tabla = new JTable(modelo);
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
		JScrollPane tablaPane = new JScrollPane(tabla);
		tabla.setBackground(null);

		this.setLayout(new BorderLayout());
		this.add(tablaPane);
	}
}
