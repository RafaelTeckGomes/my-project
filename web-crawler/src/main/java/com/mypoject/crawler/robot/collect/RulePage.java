package com.mypoject.crawler.robot.collect;

import java.util.Date;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mypoject.crawler.robot.entity.CrawledPage;
import com.mypoject.crawler.robot.service.UrlProcessor;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

@Component
public class RulePage {
	
	@Autowired
	private UrlProcessor urlProcessor;

	public void collectData(Page page) {
		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			showValues(htmlParseData);
			CrawledPage crawledPage = createEntity(page);
			callPageService(crawledPage);
		}
	}

	private void showValues(HtmlParseData htmlParseData) {
		String title = htmlParseData.getTitle();
		System.out.println(title);
		System.out.println("--------------------------------------");
		String text = htmlParseData.getText();
		System.out.println(text);
		System.out.println("--------------------------------------");
		String html = htmlParseData.getHtml();
		System.out.println(html);
		System.out.println("--------------------------------------");
		Set<WebURL> links = htmlParseData.getOutgoingUrls();
		System.out.println(links.toString());
		System.out.println("--------------------------------------");
	}

	private CrawledPage createEntity(Page page) {
		CrawledPage crawledPage = new CrawledPage.Builder().id(new Random().nextInt(10)).url(page.getWebURL().getURL()).dateUrlFound(new Date()).build();
		return crawledPage;
	}
	
	private void callPageService(CrawledPage page) {
		urlProcessor.send(page);
	}

}
