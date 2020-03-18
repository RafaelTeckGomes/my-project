package com.project.player.queue.producer;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.project.player.entity.MessagePlayer;
import com.project.player.queue.BufferTask;
import com.project.player.queue.MessageQueue;
import com.project.player.service.MessageQueueService;

/**
 * The Producer class is concrete bufferTask responsible for to produce messages
 * objects into the queue.
 * 
 * On the initialization of class, it will create a buffer of message for start
 * the message production process.
 * 
 * 
 * 
 * @author rafaelteckgomes
 *
 */
public class Producer implements BufferTask {

	private MessageQueue internalQueue = new MessageQueue();

	private final AtomicBoolean keepRunning = new AtomicBoolean(true);

	private final AtomicInteger counter = new AtomicInteger(0);

	private int sizeToProcess;

	private boolean initiator = false;

	private MessageQueueService messageQueueService;

	public Producer(MessageQueueService messageQueueService, boolean initiator, int sizeToProcess) {
		this.sizeToProcess = sizeToProcess;
		this.initiator = initiator;
		this.messageQueueService = messageQueueService;
		if (this.initiator) {
			this.internalQueue = messageQueueService.generateBufferMessage(this.sizeToProcess);
		}
	}

	public void run() {
		while (keepRunning.get()) {
			if (!internalQueue.isEmpty()) {
				MessagePlayer message = internalQueue.getValue();
				if (null != message) {
					messageQueueService.AddMessage(message);
					counter.incrementAndGet();
					System.out.println(Thread.currentThread().getName() + " Send message: " + message.toString());
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread was interrupted, Failed to complete operation");
			}
		}
	}

	public void addMessage(MessagePlayer messagePlayer) {
		internalQueue.addValue(messagePlayer);
	}

	public void terminate() {
		keepRunning.set(false);
	}

	public boolean hasFinished() {
		return counter.get() == sizeToProcess;
	}

}
