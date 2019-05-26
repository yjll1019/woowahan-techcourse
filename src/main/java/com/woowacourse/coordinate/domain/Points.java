package com.woowacourse.coordinate.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Points {
	private List<Point> points;

	public Points(List<Point> points) {
		this.points = points;
	}

	public int getPointsSize() {
		return new HashSet<>(points).size();
	}

	public List<Point> getPoints() {
		return Collections.unmodifiableList(points);
	}

}
