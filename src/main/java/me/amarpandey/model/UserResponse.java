package me.amarpandey.model;

public class UserResponse {

	String name;
	String content;
	String time;

	public UserResponse() {
	}

	public UserResponse(String name, String content) {
		this.name = name;
		this.content = content;
	}

	public UserResponse(String name, String content, String time) {
		this.name = name;
		this.content = content;
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