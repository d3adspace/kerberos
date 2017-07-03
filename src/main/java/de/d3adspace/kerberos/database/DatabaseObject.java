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
 * Representing a database object.
 *
 * @author Felix 'SasukeKawaii' Klauke
 */
public class DatabaseObject {
	
	/**
	 * The underlying map containing the data.
	 */
	private final Map<String, Object> columnValuesData;
	
	/**
	 * Create a database object by column data.
	 *
	 * @param columnValues The data.
	 */
	public DatabaseObject(Map<String, Object> columnValues) {
		this.columnValuesData = columnValues;
	}
	
	/**
	 * Create an empty database object.
	 */
	public DatabaseObject() {
		this(new HashMap<>());
	}
	
	/**
	 * Create a database object by a mysql ResultSet.
	 *
	 * @param resultSet The result set.
	 * @return The database object.
	 */
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
	
	/**
	 * Get the underlying data.
	 *
	 * @return The data.
	 */
	public Map<String, Object> getColumnValuesData() {
		return columnValuesData;
	}
	
	/**
	 * Get string joined column names ready for sql query.
	 *
	 * @return The list.
	 */
	String getColumnValues() {
		return this.columnValuesData.values().stream().map(Object::toString).map(s -> "'" + s + "'")
			.collect(Collectors.joining(", "));
	}
	
	/**
	 * Set column data.
	 *
	 * @param columnName The name of the column.
	 * @param value The value.
	 */
	public void setColumnValue(String columnName, Object value) {
		this.columnValuesData.put(columnName, value);
	}
	
	/**
	 * Get the column data by a field name.
	 *
	 * @param fieldName The field name.
	 * @return The value.
	 */
	public Object getColumnValue(String fieldName) {
		return columnValuesData.get(fieldName);
	}
	
	/**
	 * Get the string joined column names.
	 *
	 * @return The list.
	 */
	String getColumnNames() {
		return this.columnValuesData.keySet().stream().collect(Collectors.joining(", "));
	}
	
	/**
	 * Generate a string joined list of all column ready for update queries.
	 *
	 * @return The list.
	 */
	String getColumnUpdates() {
		return this.columnValuesData.entrySet().stream().map(entry -> entry.getKey() + "=?")
			.collect(Collectors.joining(", "));
	}
	
	/**
	 * Get the column count.
	 *
	 * @return The column count.
	 */
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
