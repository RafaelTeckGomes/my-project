package com.project.player.queue;

/**
 * The BasicConsumeQueue interface provide methods to implementation a concrete
 * queue class.
 * 
 * 
 * @author rafaelteckgomes
 *
 */

public interface BasicConsumeQueue<T> extends BasicQueue<T> {

	T getValue();

	int getSize();

	boolean isEmpty();

}
