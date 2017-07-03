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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple container for stored meta data.
 *
 * @author Felix 'SasukeKawaii' Klauke
 */
public class EntityMetaContainer {
	
	/**
	 * The underlying Map.
	 */
	private final Map<Class<?>, EntityMeta> metadata;
	
	/**
	 * Create a new container based on a list of metadata.
	 *
	 * @param metadata The metadata.
	 */
	public EntityMetaContainer(Map<Class<?>, EntityMeta> metadata) {
		this.metadata = metadata;
	}
	
	/**
	 * Create an empty container.
	 */
	public EntityMetaContainer() {
		this(new ConcurrentHashMap<>());
	}
	
	/**
	 * Retrieve meta of a class
	 *
	 * @param entityClazz The class.
	 *
	 * @return The meta.
	 */
	public EntityMeta getEntityMeta(Class<?> entityClazz) {
		EntityMeta entityMeta = this.metadata.get(entityClazz);
		
		if (entityMeta != null) {
			return entityMeta;
		}
		
		entityMeta = EntityMetaFactory.createEntityMeta(entityClazz);
		this.addEntityMeta(entityClazz, entityMeta);
		return entityMeta;
	}
	
	/**
	 * Register new meta data.
	 *
	 * @param entityClazz The class.
	 * @param entityMeta The meta.
	 */
	public void addEntityMeta(Class<?> entityClazz, EntityMeta entityMeta) {
		this.metadata.put(entityClazz, entityMeta);
	}
}
