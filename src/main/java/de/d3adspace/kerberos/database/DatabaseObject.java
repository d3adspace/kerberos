/*
 * Copyright (c) 2017 D3adspace
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.d3adspace.kerberos.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Felix 'SasukeKawaii' Klauke
 */
public class DatabaseObject {
	
	private final Map<String, Object> columnValuesData;
	
	public DatabaseObject(Map<String, Object> columnValues) {
		this.columnValuesData = columnValues;
	}
	
	public DatabaseObject() {
		this(new HashMap<>());
	}
	
	static DatabaseObject fromResultSet(ResultSet resultSet) {
		DatabaseObject databaseObject = new DatabaseObject();
		
		try {
			ResultSetMetaData metaData = resultSet.getMetaData();
			
			for (int i = 0; i < metaData.getColumnCount(); i++) {
				String columnName = metaData.getColumnName(i + 1);
				Object value = resultSet.getObject(columnName);
				
				databaseObject.setColumnValue(columnName, value);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return databaseObject;
	}
	
	public Map<String, Object> getColumnValuesData() {
		return columnValuesData;
	}
	
	String getColumnValues() {
		return this.columnValuesData.values().stream().map(Object::toString).map(s -> "'" + s + "'")
			.collect(Collectors.joining(", "));
	}
	
	public void setColumnValue(String columnName, Object value) {
		this.columnValuesData.put(columnName, value);
	}
	
	public Object getColumnValue(String fieldName) {
		return columnValuesData.get(fieldName);
	}
	
	String getColumnNames() {
		return this.columnValuesData.keySet().stream().collect(Collectors.joining(", "));
	}
	
	String getColumnUpdates() {
		return this.columnValuesData.entrySet().stream().map(entry -> entry.getKey() + "=?")
			.collect(Collectors.joining(", "));
	}
	
	int getColumnCount() {
		return columnValuesData.size();
	}
	
	@Override
	public String toString() {
		return "DatabaseObject{" +
			"columnValuesData=" + columnValuesData +
			'}';
	}
}
