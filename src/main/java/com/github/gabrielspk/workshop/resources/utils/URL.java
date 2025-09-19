package com.github.gabrielspk.workshop.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class URL {
	
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static LocalDate convertDate(String textDate, LocalDate defaultDate) {
	    if (textDate == null || textDate.isBlank()) {
	        return defaultDate;
	    }
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		try {
			return LocalDate.parse(textDate, formatter);
		} catch (DateTimeParseException e){
			return defaultDate;
		}
	}
}
