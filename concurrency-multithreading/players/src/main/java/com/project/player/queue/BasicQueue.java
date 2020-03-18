package com.project.player.queue;

/**
 * The BasicQueue interface provide the basics methods to implementation a
 * concrete queue class.
 * 
 * 
 * @author rafaelteckgomes
 *
 */
public interface BasicQueue<T> {

	void addValue(T value);

	T getValue();

	int getSize();

	boolean isEmpty();

}
