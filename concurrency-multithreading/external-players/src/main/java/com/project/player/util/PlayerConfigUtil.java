package com.project.player.util;

/**
 * The PlayerConfigUtil is auxiliary class just to create the initial
 * configuration to start container.
 * 
 * @author rafaelteckgomes
 *
 */
public class PlayerConfigUtil {

	private String queueUrlDestionation;

	private int portContainer;

	private String playerName;

	private boolean initiatorPlayer;

	private int messageNumberToSend;

	public PlayerConfigUtil(String playerName, String destinationUrl) {
		this.playerName = playerName;
		initialize(destinationUrl);
	}

	private void initialize(String destinationUrl) {
		if (playerName.equalsIgnoreCase("player1")) {
			queueUrlDestionation = destinationUrl.concat("/queue/add");
			portContainer = 8090;
			playerName = "Player-1";
			initiatorPlayer = true;

		} else {
			queueUrlDestionation = destinationUrl.concat("/queue/add");
			portContainer = 8091;
			playerName = "Player-2";
			initiatorPlayer = false;
		}
		messageNumberToSend = 10;
	}

	public String getQueueUrlDestionation() {
		return queueUrlDestionation;
	}

	public int getPortContainer() {
		return portContainer;
	}

	public String getPlayerName() {
		return playerName;
	}

	public boolean isInitiatorPlayer() {
		return initiatorPlayer;
	}

	public int getMessageNumberToSend() {
		return messageNumberToSend;
	}

}
