package me.amarpandey.model;

public class UserResponse {

	String name;
	String content;
	MessageType type;
	String time;

	public enum MessageType {
		CHAT, JOIN, LEAVE
	}

	public UserResponse() {
	}

	public UserResponse(String name, MessageType type) {
		this.name = name;
		this.type = type;
	}

	public UserResponse(String name, String content) {
		this.name = name;
		this.content = content;
	}

	public UserResponse(String name, String content, MessageType type) {
		this.name = name;
		this.content = content;
		this.type = type;
	}

	public UserResponse(String name, String content, MessageType type, String time) {
		this.name = name;
		this.content = content;
		this.time = time;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public String getContent() {
		return content;
	}

	public String getTime() {
		return time;
	}
}