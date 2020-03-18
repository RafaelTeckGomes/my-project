package com.project.player.service;

import java.util.concurrent.atomic.AtomicInteger;

import com.project.player.entity.MessagePlayer;

/**
 * The MessageService interface provide the basics methods to implementation a
 * concreteMessageServiceImpl class.
 * 
 * 
 * @author rafaelteckgomes
 *
 */
public interface MessageService {

	void processMessage(MessagePlayer msg, AtomicInteger counter, boolean initiator);

}
