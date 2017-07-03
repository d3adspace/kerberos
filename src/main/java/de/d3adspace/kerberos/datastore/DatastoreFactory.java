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

package de.d3adspace.kerberos.datastore;

import de.d3adspace.kerberos.annotation.Watcher;
import de.d3adspace.kerberos.database.Database;
import de.d3adspace.kerberos.watcher.EmptyEntityWatcher;
import de.d3adspace.kerberos.watcher.EntityWatcher;

/**
 * Factory for all datastores.
 *
 * @author Felix 'SasukeKawaii' Klauke
 */
public class DatastoreFactory {
	
	/**
	 * Create a new data store.
	 *
	 * @param database The database.
	 * @param entityClass The entity class.
	 * @param <EntityType> The entity type.
	 *
	 * @return The entity.
	 */
	public static <EntityType> Datastore<EntityType> createDatastore(Database database,
		Class<? extends EntityType> entityClass) {
		
		EntityWatcher<EntityType> entityWatcher = new EmptyEntityWatcher<>();
		
		if (entityClass.isAnnotationPresent(Watcher.class)) {
			try {
				entityWatcher = entityClass.getAnnotation(Watcher.class).watcher().newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return createDatastore(database, entityClass, entityWatcher);
	}
	
	/**
	 * Create a new data store.
	 *
	 * @param database The database.
	 * @param entityClass The entity class.
	 * @param <EntityType> The entity type.
	 * @param entityWatcher The entity watcher.
	 *
	 * @return The entity.
	 */
	public static <EntityType> Datastore<EntityType> createDatastore(Database database,
		Class<? extends EntityType> entityClass,
		EntityWatcher<EntityType> entityWatcher) {
		
		return new KerberosDatastore<>(database, entityClass, entityWatcher);
	}
}
