package com.project.player.queue;

import com.project.player.entity.MessagePlayer;

/**
 * QueueBundleConfig is an auxiliary class to keep the producer and the consumer
 * from the queue.
 * 
 * 
 * @author rafaelteckgomes
 *
 */
public class QueueBundleConfig {

	private BasicQueue<MessagePlayer> producerQueue;

	private BasicConsumeQueue<MessagePlayer> consumerQueue;

	public BasicQueue<MessagePlayer> getProducerQueue() {
		return producerQueue;
	}

	public BasicConsumeQueue<MessagePlayer> getConsumerQueue() {
		return consumerQueue;
	}

	public QueueBundleConfig(BasicQueue<MessagePlayer> queue1, BasicConsumeQueue<MessagePlayer> queue2) {
		this.producerQueue = queue1;
		this.consumerQueue = queue2;
	}

}
