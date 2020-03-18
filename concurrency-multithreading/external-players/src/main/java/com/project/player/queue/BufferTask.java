package com.project.player.queue;

/**
 * The BufferTask interface provide the basics methods to implementation a
 * concrete consumer or producer classes.
 * 
 * 
 * @author rafaelteckgomes
 *
 */
public interface BufferTask extends Runnable {

	void terminate();

	boolean hasFinished();

}
