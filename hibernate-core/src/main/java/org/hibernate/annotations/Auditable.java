/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( ElementType.TYPE ) // restrict the annotation to classes only
@Retention( RetentionPolicy.RUNTIME ) // means it's still readable at runtime via reflection
public @interface Auditable {
}
