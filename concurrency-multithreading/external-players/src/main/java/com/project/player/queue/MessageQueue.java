package com.project.player.queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.project.player.entity.MessagePlayer;

/**
 * The MessageQueue class provide a concrete thread-safe queue based where
 * elements are FIFO(first-in-first-out) to use in producer and consumer
 * process.
 * 
 * 
 * @author rafaelteckgomes
 *
 */

public class MessageQueue implements BasicConsumeQueue<MessagePlayer> {

	private Queue<MessagePlayer> messageQueue = new ConcurrentLinkedQueue<MessagePlayer>();

	@Override
	public MessagePlayer getValue() {
		return messageQueue.poll();
	}

	@Override
	public void addValue(MessagePlayer value) {
		messageQueue.add(value);

	}

	@Override
	public boolean isEmpty() {
		return messageQueue.isEmpty();
	}

	@Override
	public int getSize() {
		return messageQueue.size();
	}

}
