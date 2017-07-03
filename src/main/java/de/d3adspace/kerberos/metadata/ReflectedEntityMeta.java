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

package de.d3adspace.kerberos.metadata;

import de.d3adspace.kerberos.annotation.Id;
import de.d3adspace.kerberos.property.EntityProperty;
import de.d3adspace.kerberos.property.EntityPropertyFactory;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * EntityMeta implementation based on reflections.
 *
 * @author Felix 'SasukeKawaii' Klauke
 */
public class ReflectedEntityMeta implements EntityMeta {
	
	/**
	 * List of all known properties.
	 */
	private final List<EntityProperty> entityProperties;
	
	private EntityProperty entityIdProperty;
	
	/**
	 * Create meta by a class.
	 *
	 * @param entityClazz The class.
	 */
	ReflectedEntityMeta(Class<?> entityClazz) {
		this.entityProperties = new ArrayList<>();
		
		for (Field field : entityClazz.getDeclaredFields()) {
			EntityProperty property = EntityPropertyFactory.createEntityProperty(field);
			this.entityProperties.add(property);
			
			if (field.isAnnotationPresent(Id.class)) {
				this.entityIdProperty = property;
			}
		}
	}
	
	@Override
	public List<EntityProperty> getEntityProperties() {
		return entityProperties;
	}
	
	@Override
	public EntityProperty getEntityIdProperty() {
		return this.entityIdProperty;
	}
	
	@Override
	public String toString() {
		return "ReflectedEntityMeta{" +
			"entityProperties=" + entityProperties +
			", entityIdProperty=" + entityIdProperty +
			'}';
	}
}
