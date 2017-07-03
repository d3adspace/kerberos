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

/**
 * Basic property interface for all entity properties.
 *
 * @author Felix 'SasukeKawaii' Klauke
 */
public interface EntityProperty {
	
	/**
	 * Get name of the field reference.
	 *
	 * @return The field name.
	 */
	String getFieldName();
	
	/**
	 * Set the property to a given entity.
	 *
	 * @param object The value.
	 * @param entity The entity.
	 * @param <EntityType> The type of the entity.
	 */
	<EntityType> void set(Object object, EntityType entity);
	
	/**
	 * Get the property from an entity.
	 *
	 * @param entity The entity.
	 * @param <EntityType> The type of the entity.
	 *
	 * @return The value.
	 */
	<EntityType> Object get(EntityType entity);
}
