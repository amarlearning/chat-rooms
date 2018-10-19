package me.amarpandey.model;

public class UserResponse {

	String name;
	String content;
	MessageType mtype;
	GroupType gtype;
	String time;

	public enum MessageType {
		CHAT, JOIN, LEAVE
	}

	public enum GroupType {
		PUBLIC, PRIVATE
	}

	public UserResponse() {
	}

	public UserResponse(String name, MessageType mtype, GroupType gtype) {
		this.name = name;
		this.mtype = mtype;
		this.gtype = gtype;
	}

	public UserResponse(String name, String content) {
		this.name = name;
		this.content = content;
	}

	public UserResponse(String name, String content, MessageType mtype, GroupType gtype) {
		this.name = name;
		this.content = content;
		this.mtype = mtype;
		this.gtype = gtype;
	}

	public UserResponse(String name, String content, MessageType mtype, GroupType gtype, String time) {
		this.name = name;
		this.content = content;
		this.mtype = mtype;
		this.gtype = gtype;
		this.time = time;
	}

	public MessageType getMtype() {
		return mtype;
	}

	public void setMtype(MessageType mtype) {
		this.mtype = mtype;
	}

	public GroupType getGtype() {
		return gtype;
	}

	public void setGtype(GroupType gtype) {
		this.gtype = gtype;
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