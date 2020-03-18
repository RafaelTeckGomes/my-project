package com.project.player;

import java.util.concurrent.atomic.AtomicBoolean;

import com.project.player.queue.BufferTask;
import com.project.player.queue.BufferTaskEnum;
import com.project.player.queue.QueueBundleConfig;
import com.project.player.queue.consumer.Consumer;
import com.project.player.queue.producer.Producer;
import com.project.player.service.MessageQueueService;
import com.project.player.service.MessageService;
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

	public Player(String playerName, boolean initiator, int sizeToProcess, QueueBundleConfig queueConf) {
		this.sizeToProcess = sizeToProcess;
		this.playerName = playerName;
		this.initiator = initiator;
		producer = (Producer) getProducer(queueConf);
		consumer = (Consumer) getConsumer(queueConf);
	}

	private Thread createThread(BufferTaskEnum boundedBuffer, Runnable task) {
		Thread thread = new Thread(task);
		thread.setName(playerName.concat(" - ").concat(boundedBuffer.getName()));
		thread.setDaemon(true);
		return thread;
	}

	@Override
	public void terminate() {
		keepRunning.set(false);
	}

	@Override
	public BufferTask getProducer(QueueBundleConfig queueConf) {
		MessageQueueService messageQueueService = new MessageQueueServiceImpl(queueConf.getProducerQueue());
		producer = new Producer(messageQueueService, this.initiator, this.sizeToProcess);
		return producer;
	}

	@Override
	public BufferTask getConsumer(QueueBundleConfig queueConf) {
		MessageService messageService = new MessageServiceImpl(producer);
		consumer = new Consumer(queueConf.getConsumerQueue(), messageService, this.sizeToProcess, this.initiator);
		return consumer;
	}

}
