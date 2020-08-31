package me.amarpandey;

import me.amarpandey.model.ApplicationStats;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class ApplicationStatsTest {

	@Test
	public void test_incrementUserCount() throws InterruptedException {
		IntStream stream1 = IntStream.range(0, 5000);
		IntStream stream2 = IntStream.range(0, 5000);

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				stream1.forEach(i -> {
					ApplicationStats.incrementUserCount();
				});
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				stream2.forEach(i -> {
					ApplicationStats.decrementUserCount();
				});
			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		Assert.assertEquals(0, ApplicationStats.getUserCount());
	}

}