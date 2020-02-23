package com.mypoject.crawler.robot.collect;

import java.util.Set;

import org.springframework.stereotype.Component;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

@Component
public class RulePage {

	public void collectData(Page page) {
		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
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

			// @TODO
			// Call service to put in the queue.
			// After put in the queue classify the content.
		}
	}

}
