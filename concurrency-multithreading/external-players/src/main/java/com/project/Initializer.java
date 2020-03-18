package com.project;

import com.project.player.BasicPlayer;
import com.project.player.Player;
import com.project.player.queue.ExternalMessageQueue;
import com.project.player.queue.MessageQueue;
import com.project.player.queue.QueueBundleConfig;
import com.project.player.util.PlayerConfigUtil;

/**
 * The Initializer class is the main class to start the process of message
 * between the players.
 * 
 * 
 * 
 * @author rafaelteckgomes
 *
 */
public class Initializer {

	public static void main(String[] args) throws Exception {

		if (null == args[0] || args[0].isEmpty()) {
			throw new Exception("The player name is missing!");
		}

		if (null == args[1] || args[1].isEmpty()) {
			throw new Exception("The url destination is missing!");
		}

		String player = args[0];
		String destinationUrl = args[1];
		startCommunication(player, destinationUrl);
	}

	private static void startCommunication(String player, String destinationUrl) throws Exception {
		PlayerConfigUtil config = new PlayerConfigUtil(player, destinationUrl);
		QueueBundleConfig queueBundleConfig = new QueueBundleConfig(
				new ExternalMessageQueue(config.getQueueUrlDestionation()), new MessageQueue());
		JettyServer jettyServer = new JettyServer();
		jettyServer.start(queueBundleConfig, config.getPortContainer());
		BasicPlayer player1 = new Player(config.getPlayerName(), config.isInitiatorPlayer(),
				config.getMessageNumberToSend(), queueBundleConfig);
		Thread tPlayer1 = new Thread(player1);
		tPlayer1.start();
		System.out.println(config.getPlayerName() + " - initialized!");
		try {
			tPlayer1.join();
			jettyServer.stop();
		} catch (InterruptedException e) {
			System.out.println("Thread was interrupted, Failed to complete operation");
		}

		System.out.println(config.getPlayerName() + " - All processes have been finished!");
	}

}
