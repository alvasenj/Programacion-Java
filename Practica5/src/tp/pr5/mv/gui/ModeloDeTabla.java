package tp.pr5.mv.gui;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import tp.pr5.mv.cpu.CeldaMemory;
import tp.pr5.mv.exceptions.MVTrap;

//Clase que define nuestro propio modelo de funcionamiento de la tabla de memoria.
public class ModeloDeTabla extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "REGISTRO ", "VALOR " };
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
		boolean guardado = false;
		int j = 0;
		if (lista.size() == 0) {
			lista.add(i);
			j++;
			guardado = true;
		} else {
			while (!guardado && (j < lista.size())) {
				if (i.getPosicion() < lista.get(j).getPosicion()) {
					lista.add(j, i);
					guardado = true;
					j++;
				} else if (i.getPosicion() == lista.get(j).getPosicion()) {
					lista.remove(j);
					lista.add(j, i);
					guardado = true;
					j++;
				} else
					j++;
			}

			if (!guardado) {
				lista.add(i);
				if(i.getValor() == -999999){
					
				}
			}
		}

		this.fireTableDataChanged();
	}

	public void clear() {
		lista.clear();
	}

	public int delvoverNumero(int posicion){
		// TODO Auto-generated method stub
		int n = -9999;
		
		if(posicion >= 0){
			CeldaMemory celda = lista.get(posicion);
			n = celda.getValor();
		}			
		return n;	
	}

}
