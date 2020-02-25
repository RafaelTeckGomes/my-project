package com.mypoject.crawler.robot.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mypoject.crawler.robot.entity.CrawledPage;

@Service
public class UrlProcessor {

	@Value("${conf.processor-url}")
	private String processorURL;

	@Value("${conf.resource-url}")
	private String resourceUrl;

	private Invocation.Builder invocationBuilder;

	public void send(CrawledPage page) {
		Response response = getInstance().post(Entity.entity(page, MediaType.APPLICATION_JSON));
		if (response.getStatus() != 200) {
			System.err.println("Unable to connect to the server");
		}
	}

	public Invocation.Builder getInstance() {
		if (invocationBuilder == null) {
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target(processorURL);
			WebTarget pagesWebTarget = webTarget.path(resourceUrl);
			this.invocationBuilder = pagesWebTarget.request(MediaType.APPLICATION_JSON);
		}
		return invocationBuilder;
	}
}
