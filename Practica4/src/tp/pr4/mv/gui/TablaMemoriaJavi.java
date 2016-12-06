package tp.pr4.mv.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import tp.pr4.mv.cpu.CPU;
import tp.pr4.mv.cpu.CeldaMemory;

//Clase que define nuestra tabla para la memoria.
public class TablaMemoria extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static ModeloDeTabla modelo;
	private JTable tabla;

	public TablaMemoria() {

		//creamos un nuevo modelo de Tabla.
		modelo = new ModeloDeTabla();

		//Centramos los datos de la tabla.
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);

		//Creamos una tabla con nuestro nuevo modelo.
		tabla = new JTable(modelo);
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
		JScrollPane tablaPane = new JScrollPane(tabla);
		tabla.setBackground(null);

		this.setLayout(new BorderLayout());
		this.add(tablaPane);

	}

	//Metodo que se encarga de actualizar el modelo de la tabla, y con el, la tabla tambien.
	public JTable actualizarTabla(CPU maquina) {
		modelo.clear();
		int[] posiciones = maquina.devolverPosiciones();
		int[] valores = maquina.devolverValores();
		int i = 0;

		//Vamos agregando las diferentes celdas de memoria al modelo.
		while (i < posiciones.length) {
			modelo.add(new CeldaMemory(valores[i], posiciones[i]));
			i++;
		}

		//Creamos la nueva tabla con el modelo nuevo modificado.
		tabla = new JTable(modelo);
		return tabla;
	}

}
