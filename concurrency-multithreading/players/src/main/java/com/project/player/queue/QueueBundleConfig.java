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

	private BasicQueue<MessagePlayer> consumerQueue;

	public BasicQueue<MessagePlayer> getProducerQueue() {
		return producerQueue;
	}

	public BasicQueue<MessagePlayer> getConsumerQueue() {
		return consumerQueue;
	}

	public QueueBundleConfig(BasicQueue<MessagePlayer> producerQueue, BasicQueue<MessagePlayer> consumerQueue) {
		this.producerQueue = producerQueue;
		this.consumerQueue = consumerQueue;
	}

}
