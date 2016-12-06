package tp.pr4.mv.gui;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import tp.pr4.mv.cpu.CeldaMemory;

//Clase que define nuestro propio modelo de funcionamiento de la tabla de memoria.
public class ModeloDeTabla extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "DIRECCIÓN ", "VALOR " };
	private Vector<CeldaMemory> lista = new Vector<CeldaMemory>();

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex >= lista.size())
			return null;
		else {
			CeldaMemory i = lista.get(rowIndex);
			if (columnIndex == 0)
				return i.getPosicion();
			else if (columnIndex == 1)
				return i.getValor();
			else
				return null;
		}
	}

	@Override
	public String getColumnName(int col) {
		return columnas[col];
	}

	public void add(CeldaMemory i) {
		lista.add(i);
		this.fireTableDataChanged();
	}

	public void clear() {
		lista.clear();
	}

}
