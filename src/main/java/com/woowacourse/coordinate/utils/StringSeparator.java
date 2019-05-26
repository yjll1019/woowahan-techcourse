package com.woowacourse.coordinate.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringSeparator {
	private static final String REGEX_COORDINATE_FORMAT = "\\(([0-9]+),([0-9]+)\\)";
	private static final Pattern REGEX_PATTERN = Pattern.compile(REGEX_COORDINATE_FORMAT);

	public static List<String> separateString(String s) {
		return Arrays.asList(replaceWhiteSpace(s).split("-"));
	}

	private static String replaceWhiteSpace(String s) {
		return s.replaceAll("\\s", "");
	}

	public static List<Integer> separateCoordinate(String s) {
		Matcher m = REGEX_PATTERN.matcher(s);
		List<Integer> coordinate = new ArrayList<>();

		if (m.find()) {
			coordinate = Arrays.asList(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
		}

		if (coordinate.size() == 0) {
			throw new IllegalArgumentException("유효하지 않은 좌표 입력입니다.");
		}

		return coordinate;
	}
}
