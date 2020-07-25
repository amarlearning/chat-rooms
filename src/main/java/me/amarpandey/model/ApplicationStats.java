package me.amarpandey.model;

public class ApplicationStats {

	public static volatile int userCount = 0;

	private ApplicationStats() {
		throw new RuntimeException("No instance of this class");
	}

	public static void incrementUserCount() {
		synchronized (ApplicationStats.class) {
			userCount = userCount + 1;
		}
	}

	public static void decrementUserCount() {
		synchronized (ApplicationStats.class) {
			userCount = userCount - 1;
		}
	}
}
