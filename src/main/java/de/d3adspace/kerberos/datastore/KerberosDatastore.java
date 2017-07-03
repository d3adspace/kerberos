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

import de.d3adspace.kerberos.annotation.Entity;
import de.d3adspace.kerberos.converter.EntityConverter;
import de.d3adspace.kerberos.converter.EntityConverterFactory;
import de.d3adspace.kerberos.database.Database;
import de.d3adspace.kerberos.database.DatabaseObject;

/**
 * Basic datastore implementation.
 *
 * @author Felix 'SasukeKawaii' Klauke
 */
public class KerberosDatastore<EntityType> implements Datastore<EntityType> {
	
	/**
	 * The converter.
	 */
	private final EntityConverter<EntityType> converter;
	
	/**
	 * The database.
	 */
	private final Database database;
	
	/**
	 * The class of the entity.
	 */
	private final Class<? extends EntityType> entityClass;
	
	/**
	 * Entity meta data.
	 */
	private final Entity entityMetaData;
	
	/**
	 * Create a new datastore.
	 *
	 * @param database The database.
	 * @param entityClass The entity class.
	 */
	KerberosDatastore(Database database, Class<? extends EntityType> entityClass) {
		this.database = database;
		this.entityClass = entityClass;
		this.converter = EntityConverterFactory.createEntityConverter();
		this.entityMetaData = entityClass.getAnnotation(Entity.class);
	}
	
	/**
	 * Persist an entity.
	 *
	 * @param entity The entity.
	 */
	public void save(EntityType entity) {
		DatabaseObject databaseObject = this.converter.toDatabaseObject(entity);
		
		this.database.saveObject(this.entityMetaData, databaseObject);
	}
	
	/**
	 * Delete an entity by its id.
	 *
	 * @param entityId The entity id.
	 */
	public void delete(String entityId) {
		this.database.deleteObject(this.entityMetaData, entityId);
	}
	
	/**
	 * Retrieve an entity by id.
	 *
	 * @param entityId The entity id.
	 * @return The entity.
	 */
	public EntityType get(String entityId) {
		DatabaseObject databaseObject = this.database.getObject(this.entityMetaData, entityId);
		
		return this.converter.fromDatabaseObject(databaseObject, this.entityClass);
	}
}
