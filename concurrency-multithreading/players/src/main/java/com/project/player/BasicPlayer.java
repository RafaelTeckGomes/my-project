package com.project.player;

import com.project.player.queue.BufferTask;
import com.project.player.queue.QueueBundleConfig;

/**
 * The BasicPlayer interface provide the basics methods to implementation a
 * concrete player class.
 * 
 * 
 * @author rafaelteckgomes
 *
 */

public interface BasicPlayer extends Runnable {

	void terminate();

	BufferTask getProducer(QueueBundleConfig queueConf);

	BufferTask getConsumer(QueueBundleConfig queueConf);

}
