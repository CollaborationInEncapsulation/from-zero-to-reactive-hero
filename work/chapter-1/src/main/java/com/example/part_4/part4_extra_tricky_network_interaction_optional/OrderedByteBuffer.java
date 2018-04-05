package com.example.part_4.part4_extra_tricky_network_interaction_optional;

import java.nio.ByteBuffer;

public class OrderedByteBuffer {

	private final int writePosition;
	private final ByteBuffer data;

	public OrderedByteBuffer(int position, ByteBuffer data) {
		writePosition = position;
		this.data = data;
	}

	public ByteBuffer getData() {
		return data;
	}

	public int getWritePosition() {
		return writePosition;
	}
}
