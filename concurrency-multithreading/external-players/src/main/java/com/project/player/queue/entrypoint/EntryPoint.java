package com.project.player.queue.entrypoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.project.player.entity.MessagePlayer;
import com.project.player.queue.BasicConsumeQueue;

public class EntryPoint extends HttpServlet {

	/**
	 * The EntryPoint class provide a http endpoint to remote access to the queue of
	 * the player.
	 * 
	 * @author rafaelteckgomes
	 */

	private static final long serialVersionUID = 1L;

	private BasicConsumeQueue<MessagePlayer> messageQueue;

	public EntryPoint(BasicConsumeQueue<MessagePlayer> queue) {
		this.messageQueue = queue;
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = br.readLine();
		Gson gson = new Gson();
		MessagePlayer message = gson.fromJson(json, MessagePlayer.class);
		messageQueue.addValue(message);
		response.setStatus(HttpServletResponse.SC_OK);
	}

}
