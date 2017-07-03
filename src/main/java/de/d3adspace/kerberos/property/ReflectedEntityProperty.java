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

package de.d3adspace.kerberos.property;

import java.lang.reflect.Field;

/**
 * Reflection based property.
 *
 * @author Felix 'SasukeKawaii' Klauke
 */
public class ReflectedEntityProperty implements EntityProperty {
	
	/**
	 * The underlying field reference.
	 */
	private final Field field;
	
	/**
	 * The name of the field.
	 */
	private final String fieldName;
	
	/**
	 * Create a new property based on a field.
	 *
	 * @param field The field.
	 */
	ReflectedEntityProperty(Field field) {
		this.field = field;
		this.field.setAccessible(true);
		this.fieldName = field.getName();
	}
	
	@Override
	public String getFieldName() {
		return this.fieldName;
	}
	
	@Override
	public <EntityType> void set(Object object, EntityType entity) {
		try {
			this.field.set(entity, object);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public <EntityType> Object get(EntityType entity) {
		try {
			return field.get(entity);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return "ReflectedEntityProperty{" +
			"field=" + field +
			", fieldName='" + fieldName + '\'' +
			'}';
	}
}
