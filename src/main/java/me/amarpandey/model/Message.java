package me.amarpandey.model;

public class Message {

	private String user;
	private String text;
	private Type type;
	private String time;

	public Message() {
	}

	public Message(Builder builder) {
		this.user = builder.user;
		this.text = builder.text;
		this.type = builder.type;
		this.time = builder.time;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public enum Type {
		CHAT, JOIN, LEAVE
	}

	public static class Builder {
		private String user;
		private String text;
		private Type type;
		private String time;

		public Builder(String user, String text) {
			this.user = user;
			this.text = text;
		}

		public Builder ofType(Type type) {
			this.type = type;
			return this;
		}

		public Builder atTime(String time) {
			this.time = time;
			return this;
		}

		public Message build() {
			return new Message(this);
		}
	}
}
