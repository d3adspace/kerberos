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

import de.d3adspace.kerberos.Kerberos;
import de.d3adspace.kerberos.KerberosFactory;
import de.d3adspace.kerberos.config.KerberosConfig;
import de.d3adspace.kerberos.datastore.Datastore;

/**
 * @author Felix 'SasukeKawaii' Klauke
 */
public class KerberosExample {
	
	public static void main(String[] args) {
		KerberosConfig config = KerberosConfig.newBuilder()
			.setDatabaseHost("localhost")
			.setDatabasePort("3306")
			.setDatabaseName("kerberos")
			.setDatabaseUser("root")
			.setDatabasePassword("")
			.createKerberosConfig();
		
		Kerberos kerberos = KerberosFactory.createKerberos(config);
		
		Datastore<KerberosUser> datastore = kerberos.openDatastore(KerberosUser.class);
		
		KerberosUser user = new KerberosUser("1", "KerberosDerHund", 812);
		
		// Save
		datastore.save(user);
		
		// Get
		user = datastore.get("1");
		
		// Delete
		datastore.delete("1");
	}
}
