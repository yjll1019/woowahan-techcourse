package com.woowacourse.coordinate.domain;

import java.util.HashSet;
import java.util.List;

public abstract class Figure implements Shape {
	Figure(List<Point> points, int numOfPoints) {
		if(points.size() != numOfPoints) {
			throw new IllegalArgumentException("Invalid size of points");
		}
		if (new HashSet<>(points).size() != numOfPoints) {
			throw new PointDuplicateException();
		}
	}
}
