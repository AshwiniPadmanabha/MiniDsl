package com.automotive.minidsl.ast;

public final class Condition {
	private final Field field;
	private final Operator operator;
	private final String rawValue;

	public Condition(Field field, Operator operator, String rawValue) {
		this.field = field;
		this.operator = operator;
		this.rawValue = rawValue;
	}

	public Field field() {
		return field;
	}

	public Operator operator() {
		return operator;
	}

	public String rawValue() {
		return rawValue;
	}
}

