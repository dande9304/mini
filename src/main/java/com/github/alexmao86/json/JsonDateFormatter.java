package com.github.alexmao86.json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName: JSONDateFormatter <br/>
 * date: 2017年9月26日 上午9:18:46 <br/>
 * 
 * @author
 */
public interface JsonDateFormatter {
	default String format(Date date) {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date);
	}

	default Date parse(String string) {
		try {
			return new SimpleDateFormat("yyyyMMddHHmmssSSS").parse(string);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
