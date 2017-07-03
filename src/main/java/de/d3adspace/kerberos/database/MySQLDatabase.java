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

import de.d3adspace.kerberos.annotation.Entity;
import de.d3adspace.kerberos.config.KerberosConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

/**
 * @author Felix 'SasukeKawaii' Klauke
 */
public class MySQLDatabase implements Database {
	
	private Connection connection;
	
	MySQLDatabase(KerberosConfig config) {
		try {
			String url = MessageFormat
				.format("jdbc:mysql://{0}:{1}/{2}?serverTimezone=UTC", config.getDatabaseHost(),
					config.getDatabasePort(), config.getDatabaseName());
			this.connection = DriverManager
				.getConnection(url, config.getDatabaseUser(), config.getDatabasePassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void saveObject(Entity entity, DatabaseObject databaseObject) {
		StringBuilder stringBuilder = new StringBuilder("INSERT INTO ").append(entity.table())
			.append(" (")
			.append(databaseObject.getColumnNames()).append(") VALUES (")
			.append(databaseObject.getColumnValues()).append(") ON DUPLICATE KEY UPDATE ")
			.append(databaseObject.getColumnUpdates());
		
		try {
			PreparedStatement statement = this.connection
				.prepareStatement(stringBuilder.toString());
			
			String[] columns = databaseObject.getColumnValuesData().keySet()
				.toArray(new String[databaseObject.getColumnCount()]);
			for (int i = 0; i < databaseObject.getColumnCount(); i++) {
				statement.setObject(i + 1, databaseObject.getColumnValue(columns[i]));
			}
			
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteObject(Entity entity, String entityId) {
		try {
			PreparedStatement statement = this.connection
				.prepareStatement("DELETE FROM " + entity.table() + " WHERE id=?");
			statement.setString(1, entityId);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public DatabaseObject getObject(Entity entity, String entityId) {
		try {
			PreparedStatement statement = this.connection
				.prepareStatement("SELECT * FROM " + entity.table() + " WHERE id=?");
			//statement.setString(1, "id");
			statement.setInt(1, Integer.parseInt(entityId));
			
			ResultSet resultSet = statement.executeQuery();
			
			if (!resultSet.next()) {
				return null;
			}
			
			return DatabaseObject.fromResultSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
