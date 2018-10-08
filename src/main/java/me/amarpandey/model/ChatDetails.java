package me.amarpandey.model;

public class ChatDetails {

	public static int count;

	private static ChatDetails chatDetails;

	private ChatDetails() {
	}

	public static ChatDetails getObject() {

		if (chatDetails == null) {
			chatDetails = new ChatDetails();
		}

		return chatDetails;
	}
}
