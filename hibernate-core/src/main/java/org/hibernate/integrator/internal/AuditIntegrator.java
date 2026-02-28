/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.integrator.internal;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.spi.BootstrapContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.internal.AuditEventListener;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;

public class AuditIntegrator implements Integrator {

	@Override
	public void integrate(
			Metadata metadata,
			BootstrapContext bootstrapContext,
			SessionFactoryImplementor sessionFactory) {
		final EventListenerRegistry listenerRegistry = sessionFactory.getServiceRegistry().getService( EventListenerRegistry.class );

		System.out.println("Integrator... registering audit interceptor");

		assert listenerRegistry != null;
		listenerRegistry.appendListeners( EventType.PRE_INSERT, AuditEventListener.INSTANCE );
		listenerRegistry.appendListeners( EventType.PRE_UPDATE, AuditEventListener.INSTANCE );
	}
}
