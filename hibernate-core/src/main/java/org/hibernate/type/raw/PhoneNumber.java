/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.type.raw;

public class PhoneNumber {

	private final String value;

	public PhoneNumber(String value) {
		if (value == null || !value.matches("\\+?[0-9\\s\\-]{7,15}")) {
			throw new IllegalArgumentException("Invalid phone number: " + value);
		}

		this.value = value.replace("[\\s\\-]", "");
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (!(o instanceof PhoneNumber)) return false;

		return value.equals(((PhoneNumber) o).value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}
}
