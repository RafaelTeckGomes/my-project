package com.mypoject.crawler.robot.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CrawledPage {

	private int id;
	private String url;
	private Date dateUrlFound;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getDateUrlFound() {
		return dateUrlFound;
	}

	public void setDateUrlFound(Date dateUrlFound) {
		this.dateUrlFound = dateUrlFound;
	}

	@Override
	public String toString() {
		return "Page [id=" + id + ", url=" + url + ", dateUrlFound=" + dateUrlFound + "]";
	}

	public static class Builder {
		private int id;
		private String url;
		private Date dateUrlFound;

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder url(String url) {
			this.url = url;
			return this;
		}

		public Builder dateUrlFound(Date dateUrlFound) {
			this.dateUrlFound = dateUrlFound;
			return this;
		}

		public CrawledPage build() {
			return new CrawledPage(this);
		}

	}

	CrawledPage(Builder b) {
		this.id = b.id;
		this.url = b.url;
		this.dateUrlFound = b.dateUrlFound;
	}

}
