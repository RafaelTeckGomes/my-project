package com.project.player.entity;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * The MessagePlayer is entity class that represents the message values,this
 * class is responsible for hold the values sent between players. The class
 * provides a builder to create a structured message with dynamic id.
 * 
 * @author rafaelteckgomes
 *
 */
public class MessagePlayer {

	private Integer id;

	private LocalDateTime create;

	private String message;

	private Integer counter;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getCreate() {
		return create;
	}

	public void setCreate(LocalDateTime create) {
		this.create = create;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	public static class Builder {

		private Integer id;

		private LocalDateTime create;

		private String message;

		private Integer counter;

		public Builder id() {
			this.id = new Random().nextInt(100);
			return this;
		}

		public Builder create(LocalDateTime create) {
			this.create = create;
			return this;
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public Builder counter(Integer counter) {
			this.counter = counter;
			return this;
		}

		public MessagePlayer build() {
			return new MessagePlayer(this);
		}

	}

	MessagePlayer(Builder b) {
		this.id = b.id;
		this.create = b.create;
		this.message = b.message;
		this.counter = b.counter;
	}

	@Override
	public String toString() {
		return "MessagePlayer [id=" + id + ", create=" + create + ", message=" + message + ", counter=" + counter + "]";
	}

}
