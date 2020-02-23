package com.mypoject.crawler.robot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.mypoject.crawler.robot.finder.MyCrawlerConfiguration;
import com.mypoject.crawler.robot.statistics.MyCrawlerCounter;

import edu.uci.ics.crawler4j.crawler.CrawlController;

@Component
@PropertySource(value={"application.properties"})
public class MyCrawler {

	@Autowired
	private MyCrawlerFactory factory;

	@Value("${conf.path-source}")
	private String pathSource;

	@Value("${conf.initial-url}")
	private String initialURL;

	@Value("${conf.thread-number}")
	private Integer threadNumber;

	public void init() throws Exception {
		CrawlController controller = MyCrawlerConfiguration.setup(pathSource);
		controller.addSeed(initialURL);
		MyCrawlerCounter counterPages = new MyCrawlerCounter();
		factory.init(counterPages);
		controller.start(factory, threadNumber);
		System.out.println("Crawled pages: "+counterPages.getProcessedPageCount());
		System.out.println("Total links collected :"+ counterPages.getTotalLinksCount());
	}

}
