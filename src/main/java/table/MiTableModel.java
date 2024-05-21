package table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Equipo;
import model.Socio;


public class MiTableModel  extends AbstractTableModel {
	Object datos[][] = null;
	String titulos[] = null;

	/**
	 * 
	 */
	public MiTableModel(Equipo e, String filtro) {
		// Datos a presentar en la tabla
		datos = DatosDeTabla.getDatosDeTabla(e, filtro);
		titulos = DatosDeTabla.getTitulosColumnas();
	}
	
	/**
	 * Métodos getter y setter al object datos
	 */
	
	public Object getDatos(){
		return this.datos;
	}
	
	public void setDatos(Object datos[][]) {
		for (int i = 0; i < this.getRowCount(); i++) {
			for (int j = 0; j < this.getColumnCount(); j++) {
				this.datos[i][j] = datos[i][j];
			}
		}
	}
	
	// Los tres siguientes m�todos son los m�nimos necesarios para representar la tabla
	/**
	 * Permite que la tabla sepa cu�ntas columnas debe mostrar
	 */
	@Override
	public int getColumnCount() {
		return titulos.length;
	}

	/**
	 * Permite que la tabla sepa cu�ntas filas debe mostrar
	 */
	@Override
	public int getRowCount() {
		return datos.length;
	}

	/**
	 * Se conocer� el dato en cada celda, es uno de los m�todos fundamentales del abstractTableModel
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return datos[rowIndex][columnIndex];
	}

	/**
	 * Podemos indicar si la tabla ser� o no editable en cada una de sus celdas, incluso por separado
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	/**
	 * Este m�todo da nombre a las columnas de la tabla
	 */
	@Override
	public String getColumnName(int column) {
		return this.titulos[column];
	}

	/**
	 * Permite que la tabla sepa que tipo de dato est� mostrando en cada columna
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (this.datos.length > 0) {
			return this.datos[0][columnIndex].getClass();
		}
		return String.class;
	}

	/**
	 * Este m�todo s�lo debe implementarse si la tabla es editable y los datos pueden cambiar
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		this.datos[rowIndex][columnIndex] = aValue;
		fireTableCellUpdated(rowIndex, columnIndex);
	}	

}
