package com.project.player.service.impl;

import java.time.LocalDateTime;

import com.project.player.entity.MessagePlayer;
import com.project.player.queue.BasicQueue;
import com.project.player.queue.MessageQueue;
import com.project.player.service.MessageQueueService;

/**
 * The MessageQueueServiceImpl class is a concrete class responsible for create
 * a buffer messages and also putting objects on the consumer's queue.
 * 
 * 
 * @author rafaelteckgomes
 *
 */
public class MessageQueueServiceImpl implements MessageQueueService {

	private BasicQueue<MessagePlayer> queue;

	public MessageQueueServiceImpl(BasicQueue<MessagePlayer> queue) {
		this.queue = queue;
	}

	@Override
	public MessageQueue generateBufferMessage(Integer sizeBuffer) {
		MessageQueue bufferQueue = new MessageQueue();
		for (int i = 0; i < sizeBuffer; i++) {
			MessagePlayer msg = new MessagePlayer.Builder().id().create(LocalDateTime.now()).message("HELLO").counter(0)
					.build();
			bufferQueue.addValue(msg);
		}
		return bufferQueue;
	}

	@Override
	public void AddMessage(MessagePlayer message) {
		queue.addValue(message);
	}

}
