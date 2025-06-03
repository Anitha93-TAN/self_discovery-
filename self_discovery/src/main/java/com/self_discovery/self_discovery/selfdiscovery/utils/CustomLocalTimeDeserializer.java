package com.self_discovery.self_discovery.selfdiscovery.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;

public class CustomLocalTimeDeserializer extends JsonDeserializer<LocalTime> {

	private static final Logger logger = Logger.getLogger(CustomLocalTimeDeserializer.class.getName());
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("hh:mm a");

	@Override
	public LocalTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		String time = p.getText();
		logger.info("Attempting to deserialize time: " + time);
		try {
			return LocalTime.parse(time, FORMATTER);
		} catch (DateTimeParseException e) {
			logger.severe("Failed to deserialize LocalTime: " + e.getMessage());
			throw new IOException("Failed to deserialize LocalTime: " + e.getMessage(), e);
		}
	}
}
