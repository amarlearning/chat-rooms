package me.amarpandey.model;

public class Message {

	private String from;
	private String text;
	private Type type;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
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

	public void setMessageType(Type type) {
		this.type = type;
	}

	public enum Type {
		CHAT, JOIN, LEAVE
	}
}
