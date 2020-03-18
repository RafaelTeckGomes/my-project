package com.project.player.queue;

/**
 * The BufferTaskEnum is a Enumerator class with types of BufferTask
 * 
 * @author rafaelteckgomes
 *
 */

public enum BufferTaskEnum {

	PRODUCER("Producer"), CONSUMER("Consumer");

	private String name;

	BufferTaskEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
