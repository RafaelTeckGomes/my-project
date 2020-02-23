package com.mypoject.crawler.robot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mypoject.crawler.robot.finder.HtmlFinder;
import com.mypoject.crawler.robot.statistics.MyCrawlerCounter;

import edu.uci.ics.crawler4j.crawler.CrawlController.WebCrawlerFactory;

@Component
public class MyCrawlerFactory implements WebCrawlerFactory<HtmlFinder> {

	private MyCrawlerCounter counterPages;

	@Autowired
	private HtmlFinder htmlFinder;

	public void init(MyCrawlerCounter counterPages) {
		this.counterPages = counterPages;
	}

	@Override
	public HtmlFinder newInstance() throws Exception {
		htmlFinder.init(counterPages);
		return htmlFinder;
	}

}
