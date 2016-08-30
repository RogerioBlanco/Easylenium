package org.easylenium.core.util;

import java.util.HashMap;
import java.util.Map;

public class Table<ROW, COLUMN, VALUE> {

	private Map<Key<ROW, COLUMN>, VALUE> table;

	public Table() {
		this.table = new HashMap<Key<ROW, COLUMN>, VALUE>();
	}

	public void put(ROW row, COLUMN column, VALUE value) {
		table.put(new Key<ROW, COLUMN>(row, column), value);
	}

	public VALUE get(ROW row, COLUMN column) {
		return table.get(new Key<ROW, COLUMN>(row, column));
	}

	public void put(Key<ROW, COLUMN> key, VALUE value) {
		table.put(key, value);
	}
}
