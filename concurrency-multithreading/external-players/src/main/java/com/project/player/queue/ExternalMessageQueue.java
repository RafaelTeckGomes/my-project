package com.project.player.queue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.project.player.entity.MessagePlayer;

/**
 * The ExternalMessageQueue class provide method to call a player remote queue
 * to add message.
 * 
 * @author rafaelteckgomes
 *
 */

public class ExternalMessageQueue implements BasicQueue<MessagePlayer> {

	private WebTarget baseTarget;

	private Gson gson = new Gson();

	public ExternalMessageQueue(String serviceUrl) {
		Client client = ClientBuilder.newClient();
		baseTarget = client.target(serviceUrl);
	}

	@Override
	public void addValue(MessagePlayer value) {
		String json = gson.toJson(value);
		Invocation.Builder invocationBuilder = baseTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.put(Entity.json(json));

		if (response.getStatus() != Response.Status.OK.getStatusCode()) {
			throw new RuntimeException("Erro criando contato");
		}
	}

}
