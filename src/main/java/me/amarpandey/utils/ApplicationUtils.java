package me.amarpandey.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApplicationUtils {

	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");

	public static String getTime() {
		return dateTimeFormatter.format(LocalDateTime.now()); //  13:46 26-07-2020
	}

}
