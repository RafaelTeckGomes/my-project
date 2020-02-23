package com.mypoject.crawler.robot.statistics;

public class MyCrawlerCounter {

	private int processedPageCount = 0;
	private int totalLinksCount = 0;

	public void incrementProcessedPageCount() {
		processedPageCount++;
	}

	public void incrementTotalLinksCount(int linksCount) {
		totalLinksCount += linksCount;
	}

	public int getProcessedPageCount() {
		return processedPageCount;
	}

	public int getTotalLinksCount() {
		return totalLinksCount;
	}
}
