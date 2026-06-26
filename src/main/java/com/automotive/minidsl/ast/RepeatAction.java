package com.automotive.minidsl.ast;

public final class RepeatAction {
	private final int times;

	public RepeatAction(int times) {
		this.times = times;
	}

	public int times() {
		return times;
	}
}

