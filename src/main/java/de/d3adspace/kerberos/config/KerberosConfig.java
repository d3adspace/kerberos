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

package de.d3adspace.kerberos.config;

/**
 * Kerberos main config.
 *
 * @author Felix 'SasukeKawaii' Klauke
 */
public class KerberosConfig {
	
	/**
	 * The database host.
	 */
	private String databaseHost;
	
	/**
	 * The database port.
	 */
	private String databasePort;
	
	/**
	 * The database name.
	 */
	private String databaseName;
	
	/**
	 * The database user.
	 */
	private String databaseUser;
	
	/**
	 * The database password.
	 */
	private String databasePassword;
	
	/**
	 * Create a new config.
	 *
	 * @param databaseHost The database host.
	 * @param databasePort The database port.
	 * @param databaseName The database name.
	 * @param databaseUser The database user.
	 * @param databasePassword The database password.
	 */
	KerberosConfig(String databaseHost, String databasePort, String databaseName,
		String databaseUser, String databasePassword) {
		this.databaseHost = databaseHost;
		this.databasePort = databasePort;
		this.databaseName = databaseName;
		this.databaseUser = databaseUser;
		this.databasePassword = databasePassword;
	}
	
	/**
	 * Create a new builder.
	 *
	 * @return The builder.
	 */
	public static KerberosConfigBuilder newBuilder() {
		return new KerberosConfigBuilder();
	}
	
	/**
	 * Get the database host.
	 *
	 * @return The database host.
	 */
	public String getDatabaseHost() {
		return databaseHost;
	}
	
	/**
	 * Get the database name.
	 *
	 * @return The database name.
	 */
	public String getDatabaseName() {
		return databaseName;
	}
	
	/**
	 * Get the database password.
	 *
	 * @return The database password.
	 */
	public String getDatabasePassword() {
		return databasePassword;
	}
	
	/**
	 * Get the database port.
	 *
	 * @return The database port.
	 */
	public String getDatabasePort() {
		return databasePort;
	}
	
	/**
	 * Get the database user.
	 *
	 * @return The database user.
	 */
	public String getDatabaseUser() {
		return databaseUser;
	}
}
