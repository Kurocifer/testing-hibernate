/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.event.internal;

import org.hibernate.annotations.Auditable;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;

import java.time.LocalDateTime;

public class AuditEventListener implements PreInsertEventListener, PreUpdateEventListener {

	public static final AuditEventListener INSTANCE = new AuditEventListener();

	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		// get entity from pre insert even object
		final Object entity = event.getEntity();

		System.out.println("blah blah blah in pre insert listener" + entity.getClass().getName());

		// check if entity has the @Audtiable annotation
		if (!entity.getClass().isAnnotationPresent( Auditable.class )) {
			System.out.println("no auditable @ found in pre insert");
			return false;
		}

		// get properties (I guess those are the fields) on the entity
		// The propertyName is an array holding the names of the different fields on the object
		final String[] propertyNames = event.getPersister().getPropertyNames();

		// the state array is an array holding the values of the different variables on the object
		final Object[] state = event.getState();
		final LocalDateTime now = LocalDateTime.now();

		setValue( state, propertyNames,  "createdAt", now );
		setValue( state, propertyNames, "updatedAt", now );

		return false;
	}

	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		final Object entity = event.getEntity();

		System.out.println("blah blah blah in pre update listener" + entity.getClass().getName());

		if (!entity.getClass().isAnnotationPresent( Auditable.class )) {
			System.out.println("no auditable @ found in pre update");
			return false;
		}

		final String[] propertyNames = event.getPersister().getPropertyNames();
		final Object[] state = event.getState();

		setValue( state, propertyNames, "updatedAt", LocalDateTime.now() );
		return false;
	}

	protected void setValue(Object[] state, String[] propertyNames, String propertyName, Object value) {
		System.out.println("Finding value...");
		for (int i = 0; i < propertyNames.length; i++) {
			System.out.println("property name: " + propertyNames[i]);
			if (propertyNames[i].equals( propertyName )) {
				state[i] = value;
			}
		}
	}
}
