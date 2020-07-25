package me.amarpandey.model;

public class Message {

	private String from;
	private String text;
	private Type type;

	public Message(Builder builder) {
		this.from = builder.from;
		this.text = builder.text;
		this.type = builder.type;
	}

	public enum Type {
		CHAT, JOIN, LEAVE
	}

	public static class Builder {
		private String from;
		private String text;
		private Type type;

		public Builder(String from, String text) {
			this.from = from;
			this.text = text;
		}

		public Builder messageType(Type type) {
			this.type = type;
			return this;
		}

		public Message build() {
			return new Message(this);
		}
	}
}
