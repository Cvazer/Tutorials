package by.zti.main;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class PersonModel implements TableModel {
	private ArrayList<TableModelListener> listeners;

	public PersonModel() {
		listeners = new ArrayList<TableModelListener>();
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		listeners.add(l);
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public String getColumnName(int index) {
		String return_string = "";
		switch (index) {
		case 0:
			return_string = "Name";
			break;
		case 1:
			return_string = "Surname";
			break;
		}
		return return_string;
	}

	@Override
	public int getRowCount() {
		return Main.persons.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object return_object = null;
		switch (col) {
		case 0:
			return_object = Main.persons.get(row).getName();
			break;
		case 1:
			return_object = Main.persons.get(row).getSurname();
			break;
		}
		return return_object;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return true;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		listeners.remove(l);
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		switch (col){
		case 0:
			Main.persons.get(row).setName((String) value);
			break;
		case 1:
			Main.persons.get(row).setSurname((String) value);
			break;
		}
	}

}
