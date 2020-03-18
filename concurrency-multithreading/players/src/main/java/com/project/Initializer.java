package com.project;

import com.project.player.BasicPlayer;
import com.project.player.Player;
import com.project.player.entity.MessagePlayer;
import com.project.player.queue.QueueBundleConfig;
import com.project.player.queue.BasicQueue;
import com.project.player.queue.MessageQueue;

/**
 * The Initializer class is the main class to start the process of message between
 * the players.
 * 
 * 
 * 
 * @author rafaelteckgomes
 *
 */
public class Initializer {

	public static void main(String[] args) {
		startCommunication();
	}

	private static void startCommunication() {

		BasicQueue<MessagePlayer> queue1 = new MessageQueue();
		BasicQueue<MessagePlayer> queue2 = new MessageQueue();

		BasicPlayer player1 = new Player("Player1", true, 10, new QueueBundleConfig(queue1, queue2));
		Thread tPlayer1 = new Thread(player1);
		BasicPlayer player2 = new Player("Player2", false, 10, new QueueBundleConfig(queue2, queue1));
		Thread tPlayer2 = new Thread(player2);

		tPlayer2.start();
		tPlayer1.start();

		try {
			tPlayer2.join();
			tPlayer1.join();
		} catch (InterruptedException e) {
			System.out.println("Thread was interrupted, Failed to complete operation");
		}

		System.out.println("All processes have been finished!");
	}

}
