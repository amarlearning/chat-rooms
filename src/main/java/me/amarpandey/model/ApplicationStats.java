package me.amarpandey.model;

import java.util.concurrent.atomic.AtomicInteger;

public class ApplicationStats {

	public static AtomicInteger userCount = new AtomicInteger(0);

	private ApplicationStats() {
		throw new RuntimeException("No instance of this class");
	}

	public static void incrementUserCount() {
			userCount.incrementAndGet();
	}

	public static void decrementUserCount() {
			userCount.decrementAndGet();
	}
}
