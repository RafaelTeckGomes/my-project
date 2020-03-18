package com.project.player;

import java.util.concurrent.atomic.AtomicBoolean;

import com.project.player.queue.BufferTask;
import com.project.player.queue.BufferTaskEnum;
import com.project.player.queue.QueueBundleConfig;
import com.project.player.queue.consumer.Consumer;
import com.project.player.queue.producer.Producer;
import com.project.player.service.MessageQueueService;
import com.project.player.service.impl.MessageQueueServiceImpl;
import com.project.player.service.impl.MessageServiceImpl;

/**
 * The player class is responsible for creating and initializing subtasks of
 * producers and consumers, in addition to controlling their processing and
 * completion. After completing your subtasks, the task itself is finished.
 * 
 * It is expected to provide the implementation of a producer and consumer of
 * the type BufferTask
 * 
 * @author rafaelteckgomes
 *
 */

public class Player implements BasicPlayer {

	private String playerName;

	private Producer producer;

	private Consumer consumer;

	private final AtomicBoolean keepRunning = new AtomicBoolean(true);

	private boolean initiator = false;

	private int sizeToProcess = 0;

	private MessageQueueService messageQueueService;

	public void run() {

		startTasks();

		while (keepRunning.get()) {
			verifyProcessing();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread was interrupted, Failed to complete operation");
			}

		}

		System.out.println(playerName.concat(" has been completed!"));
	}

	private void startTasks() {
		Thread tConsumer = createThread(BufferTaskEnum.CONSUMER, consumer);
		tConsumer.start();

		Thread tProducer = createThread(BufferTaskEnum.PRODUCER, producer);
		tProducer.start();
	}

	private void verifyProcessing() {
		if (producer.hasFinished()) {
			producer.terminate();
		}
		if (consumer.hasFinished()) {
			consumer.terminate();
		}
		if (producer.hasFinished() && consumer.hasFinished()) {
			this.terminate();
		}
	}

	public void terminate() {
		keepRunning.set(false);
	}

	public Player(String playerName, boolean initiator, int sizeToProcess, QueueBundleConfig queueConf) {
		this.sizeToProcess = sizeToProcess;
		this.playerName = playerName;
		this.initiator = initiator;
		this.messageQueueService = new MessageQueueServiceImpl(queueConf.getProducerQueue());

		producer = (Producer) getProducer(queueConf);
		consumer = (Consumer) getConsumer(queueConf);
	}

	public BufferTask getProducer(QueueBundleConfig queueConf) {
		producer = new Producer(messageQueueService, this.initiator, this.sizeToProcess);
		return producer;
	}

	public BufferTask getConsumer(QueueBundleConfig queueConf) {
		consumer = new Consumer(queueConf.getConsumerQueue(), new MessageServiceImpl(producer), this.sizeToProcess,
				this.initiator);
		return consumer;
	}

	private Thread createThread(BufferTaskEnum boundedBuffer, Runnable task) {
		Thread thread = new Thread(task);
		thread.setName(playerName.concat(" - ").concat(boundedBuffer.getName()));
		thread.setDaemon(true);
		return thread;
	}

}
