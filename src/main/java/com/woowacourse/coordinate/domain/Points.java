package com.woowacourse.coordinate.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Points {
	private List<Point> points;

	public Points(List<Point> points) {
		this.points = points;
	}

	public int getSize() {
		return points.size();
	}

	public List<Point> getPoints() {
		return Collections.unmodifiableList(points);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Points)) {
			return false;
		}
		final Points points1 = (Points) o;
		return Objects.equals(getPoints(), points1.getPoints());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPoints());
	}
}
