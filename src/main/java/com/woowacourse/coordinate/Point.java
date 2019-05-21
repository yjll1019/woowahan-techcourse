package com.woowacourse.coordinate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Point implements Comparable<Point> {
	private static final int MIN = 0;
	private static final int MAX = 24;

	private int x;
	private int y;

	public Point(int x, int y) {
		if (x <= MIN || x > MAX || y <= MIN || y > MAX) {
			throw new IllegalArgumentException(String.format("Invalid range: %d, %d", x, y));
		}
		this.x = x;
		this.y = y;
	}

	public Point(String str) {
		str = str.trim();
		if (!isSurroundedWithParenthesis(str)) {
			throw new IllegalArgumentException("Invalid string for create point: " + str);
		}

		String[] segments = str.split("[,()]");
		x = Integer.valueOf(segments[1].trim());
		y = Integer.valueOf(segments[2].trim());
	}

	public static List<Point> createWithPair(String s) {
		String[] segments = s.split("\\)[\\s]*-[\\s]*\\(");
		List<Integer> coords1 = Arrays.asList(segments[0].substring(1).split(","))
				.stream()
				.map(String::trim)
				.map(Integer::valueOf)
				.collect(Collectors.toList());

		List<Integer> coords2 = Arrays.asList(segments[1].substring(0, segments[1].length() - 1).split(","))
				.stream()
				.map(String::trim)
				.map(Integer::valueOf)
				.collect(Collectors.toList());
		return Arrays.asList(new Point(coords1.get(0), coords1.get(1)), new Point(coords2.get(0), coords2.get(1)));
	}


	private boolean isSurroundedWithParenthesis(String s) {
		return s.startsWith("(") && s.endsWith(")");
	}

	public boolean matchX(int x) {
		return this.x == x;
	}

	public boolean matchY(int y) {
		return this.y == y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Point)) {
			return false;
		}
		Point point = (Point) o;
		return x == point.x &&
				y == point.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	public double calculateDistance(Point point) {
		double dx = Math.abs(point.x - x);
		double dy = Math.abs(point.y - y);
		return Math.sqrt(dx * dx + dy * dy);
	}

	@Override
	public int compareTo(Point point) {
		if (this.y == point.y) {
			return Integer.compare(this.x, point.x);
		}
		return Integer.compare(this.y, point.y);
	}
}
