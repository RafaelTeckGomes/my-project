package com.project;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import com.project.player.queue.QueueBundleConfig;
import com.project.player.queue.entrypoint.EntryPoint;

/**
 * The JettyServer class provide configuration to start the container.
 * 
 * @author rafaelteckgomes
 *
 */
public class JettyServer {

	private Server server;

	void start(QueueBundleConfig queueBundleConfig, int port) throws Exception {

		int maxThreads = 100;
		int minThreads = 10;
		int idleTimeout = 120;

		QueuedThreadPool threadPool = new QueuedThreadPool(maxThreads, minThreads, idleTimeout);

		server = new Server(threadPool);
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(port);
		server.setConnectors(new Connector[] { connector });

		ServletContextHandler servletHandler = new ServletContextHandler();
		EntryPoint entryPoint = new EntryPoint(queueBundleConfig.getConsumerQueue());
		ServletHolder servletHolder = new ServletHolder(entryPoint);
		servletHandler.setContextPath("/");
		servletHandler.addServlet(servletHolder, "/queue/*");
		server.setHandler(servletHandler);

		server.start();

	}

	void stop() throws Exception {
		server.stop();
	}

}
