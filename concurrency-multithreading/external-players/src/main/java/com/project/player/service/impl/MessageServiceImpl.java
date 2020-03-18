package com.project.player.service.impl;

import java.util.concurrent.atomic.AtomicInteger;

import com.project.player.entity.MessagePlayer;
import com.project.player.queue.producer.Producer;
import com.project.player.service.MessageService;

/**
 * The MessageServiceImpl is a specific class responsible for applying business
 * rules, increasing the counter and updating the original message, putting it
 * on the producer's queue.
 * 
 * 
 * @author rafaelteckgomes
 *
 */
public class MessageServiceImpl implements MessageService {

	private Producer producer;

	public MessageServiceImpl(Producer producer) {
		this.producer = producer;
	}

	public void processMessage(MessagePlayer msg, AtomicInteger counter, boolean initiator) {
		msg.setCounter(counter.incrementAndGet());
		if (!initiator) {
			producer.addMessage(msg);
		}
	}

}
