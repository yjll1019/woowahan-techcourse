package com.woowacourse.coordinate.domain;

import java.util.ArrayList;
import java.util.List;

import com.woowacourse.coordinate.utils.StringSeparator;

public class PointsFactory {
	public static Points createPoints(String s) {
		List<String> coordinates = StringSeparator.separateString(s);
		List<Point> points = new ArrayList<>();

		for (String t : coordinates) {
			List<Integer> coordinate = StringSeparator.separateCoordinate(t);
			points.add(new Point(coordinate.get(0), coordinate.get(1)));
		}

		return new Points(points);
	}
}
