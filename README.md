# Kerberos

A very simple and lightweight framework to store entities in mySQL databases. This is
absolutely experimental and not really ready to use. 

# Installation / Usage

- Install [Maven](http://maven.apache.org/download.cgi)
- Clone this repo
- Install: ```mvn clean install```

**Maven dependencies**

```xml
<dependency>
    <groupId>de.d3adspace</groupId>
    <artifactId>kerberos</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

#Example

_Model:_
```java
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

import de.d3adspace.kerberos.annotation.Entity;
import de.d3adspace.kerberos.annotation.Id;

/**
 * @author Felix 'SasukeKawaii' Klauke
 */
@Entity(table = "kerberos_users")
public class KerberosUser {
	
	@Id private String id;
	private String name;
	private int age;
	
	public KerberosUser(String id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public KerberosUser() {
	}
	
	public int getAge() {
		return age;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "KerberosUser{" +
			"id='" + id + '\'' +
			", name='" + name + '\'' +
			", age=" + age +
			'}';
	}
}
```

_CRUD:_
```java
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
```