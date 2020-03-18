package com.project.player.service;

import com.project.player.entity.MessagePlayer;
import com.project.player.queue.MessageQueue;

/**
 * The MessageQueueService interface provide the basics methods to
 * implementation a concrete MessageQueueServiceImpl class.
 * 
 * 
 * @author rafaelteckgomes
 *
 */
public interface MessageQueueService {

	MessageQueue generateBufferMessage(Integer sizeBuffer);
	
	void AddMessage(MessagePlayer message);
}
