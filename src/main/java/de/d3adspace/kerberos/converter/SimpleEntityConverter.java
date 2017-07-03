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

package de.d3adspace.kerberos.converter;

import de.d3adspace.kerberos.database.DatabaseObject;
import de.d3adspace.kerberos.metadata.EntityMeta;
import de.d3adspace.kerberos.metadata.EntityMetaContainer;
import de.d3adspace.kerberos.property.EntityProperty;

/**
 * Basic converter implementation.
 *
 * @author Felix 'SasukeKawaii' Klauke
 */
public class SimpleEntityConverter<EntityType> implements EntityConverter<EntityType> {
	
	/**
	 * Container for entity metadata.
	 */
	private final EntityMetaContainer metaContainer;
	
	/**
	 * Create a new entity converter.
	 *
	 * @param metaContainer The metadata container.
	 */
	SimpleEntityConverter(EntityMetaContainer metaContainer) {
		this.metaContainer = metaContainer;
	}
	
	@Override
	public DatabaseObject toDatabaseObject(EntityType entity) {
		EntityMeta entityMeta = this.metaContainer.getEntityMeta(entity.getClass());
		
		DatabaseObject databaseObject = new DatabaseObject();
		for (EntityProperty property : entityMeta.getEntityProperties()) {
			databaseObject.setColumnValue(property.getFieldName(), property.get(entity));
		}
		
		return databaseObject;
	}
	
	@Override
	public EntityType fromDatabaseObject(DatabaseObject databaseObject,
		Class<? extends EntityType> entityClass) {
		if (databaseObject == null) {
			return null;
		}
		
		EntityMeta entityMeta = this.metaContainer.getEntityMeta(entityClass);
		
		try {
			EntityType entity = entityClass.newInstance();
			
			for (EntityProperty property : entityMeta.getEntityProperties()) {
				property.set(databaseObject.getColumnValue(property.getFieldName()), entity);
			}
			
			return entity;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
