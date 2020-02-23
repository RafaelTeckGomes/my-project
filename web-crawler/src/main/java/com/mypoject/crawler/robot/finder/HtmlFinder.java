package com.mypoject.crawler.robot.finder;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mypoject.crawler.robot.collect.RulePage;
import com.mypoject.crawler.robot.statistics.MyCrawlerCounter;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;


@Component
public class HtmlFinder extends WebCrawler {

	private MyCrawlerCounter counter;

	private final static Pattern EXCLUSIONS = Pattern.compile(".*(\\.(css|js|xml|gif|jpg|png|mp3|mp4|zip|gz|pdf))$");
	
	@Autowired
	private RulePage rulePage;

	public void init(MyCrawlerCounter counter) {
		this.counter = counter;
	}

	@Override
	public void visit(Page page) {
	    String url = page.getWebURL().getURL();
		System.out.println("Page: ".concat(url));
		rulePage.collectData(page);
		counter.incrementProcessedPageCount();
	}

	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		System.out.println("URL: ".concat(url.toString()));
		String urlString = url.getURL().toLowerCase();
		boolean isProcessed =!EXCLUSIONS.matcher(urlString).matches() && urlString.contains("recipe");
		System.out.println("isProcessed: "+isProcessed);
		return isProcessed;
	}

}
