package com.woowacourse.coordinate.domain.figure;

import java.util.Collections;
import java.util.List;

public class Points {
	private List<Point> points;

	public Points(List<Point> points) {
		this.points = points;
	}

	public List<Point> getPoints() {
		return Collections.unmodifiableList(points);
	}
}
