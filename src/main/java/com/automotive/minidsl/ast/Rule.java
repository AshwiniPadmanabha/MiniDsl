package com.automotive.minidsl.ast;

public final class Rule {
	private final Condition condition;
	private final RepeatAction thenAction;
	private final RepeatAction elseAction;

	public Rule(Condition condition, RepeatAction thenAction, RepeatAction elseAction) {
		this.condition = condition;
		this.thenAction = thenAction;
		this.elseAction = elseAction;
	}

	public Condition condition() {
		return condition;
	}

	public RepeatAction thenAction() {
		return thenAction;
	}

	public RepeatAction elseAction() {
		return elseAction;
	}
}

