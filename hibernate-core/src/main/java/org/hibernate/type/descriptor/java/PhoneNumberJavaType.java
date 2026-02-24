/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.type.descriptor.java;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.raw.PhoneNumber;

/**
 * Descriptor for {@link org.hibernate.type.raw.PhoneNumber} handling.
 */
public class PhoneNumberJavaType extends AbstractClassJavaType<PhoneNumber> {

	public static final PhoneNumberJavaType INSTANCE = new PhoneNumberJavaType();

	public PhoneNumberJavaType(){super(PhoneNumber.class);}

	@Override
	public boolean isInstance(Object value) {
		return value instanceof PhoneNumber;
	}

	@Override
	public PhoneNumber cast(Object value) {
		return (PhoneNumber) value;
	}

	@Override
	public boolean useObjectEqualsHashCode() {return false;}

	@Override
	public String toString(PhoneNumber value) {
		return value.toString();
	}

	@Override
	public PhoneNumber fromString(CharSequence string) {
		return new PhoneNumber(string.toString());
	}

	public <X> X unwrap(PhoneNumber value, Class<X> type, WrapperOptions options) {
		if (value == null) {
			return null;
		}

		if (PhoneNumber.class.isAssignableFrom( type )) {
			return type.cast( value );
		}

		if (String.class.isAssignableFrom( type )) {
			return type.cast( value.toString() );
		}

		throw unknownUnwrap( type );
	}

	@Override
	public <X> PhoneNumber wrap(X value, WrapperOptions options) {
		if (value == null) {
			return null;
		}

		if (value instanceof PhoneNumber phoneNumber) {
			return phoneNumber;
		}

		if (value instanceof String string) {
			return new PhoneNumber( string );
		}

		throw unknownUnwrap( value.getClass() );
	}
}
