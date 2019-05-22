package com.woowacourse.coordinate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
		return Arrays.stream(s.split("-"))
				.map(Point::new)
				.collect(Collectors.toList());
	}


	private boolean isSurroundedWithParenthesis(String s) {
		return s.startsWith("(") && s.endsWith(")");
	}

	public boolean matchX(int x) {
		return this.x == x;
	}

	public boolean matchX(Point p) {
		return this.x == p.x;
	}

	public boolean matchY(int y) {
		return this.y == y;
	}

	public boolean matchY(Point p) {
		return this.y == p.y;
	}

	public double calculateDistance(Point point) {
		double dx = Math.abs(point.x - x);
		double dy = Math.abs(point.y - y);
		return Math.sqrt(dx * dx + dy * dy);
	}

	public Optional<Double> calculateSlope(Point p) {
		if (p.x == this.x) {
			return Optional.ofNullable(null);
		}
		return Optional.of(Math.abs((p.y - this.y) / (double)(p.x - this.x)));
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

	@Override
	public String toString() {
		return String.format("Point { x: %d, y: %d }", x, y);
	}

	@Override
	public int compareTo(Point point) {
		if (this.y == point.y) {
			return Integer.compare(this.x, point.x);
		}
		return Integer.compare(this.y, point.y);
	}
}
