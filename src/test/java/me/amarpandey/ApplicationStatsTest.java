package me.amarpandey;

import me.amarpandey.model.ApplicationStats;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.SocketHandler;
import java.util.stream.IntStream;

import static java.util.Optional.of;


public class ApplicationStatsTest {

	@RepeatedTest(5)
	public void test_incrementUserCount() throws InterruptedException {
		IntStream stream1 = IntStream.range(0, 500);
		IntStream stream2 = IntStream.range(0, 100);

		ApplicationStats.userCount = new AtomicInteger(0);

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

		Assert.assertEquals(400, ApplicationStats.userCount.intValue());
	}

}