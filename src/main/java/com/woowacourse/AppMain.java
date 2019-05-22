package com.woowacourse;

import java.util.Arrays;
import java.util.List;

import com.woowacourse.coordinate.*;

public class AppMain {
	public static void main(String[] args) {
		String input = InputView.inputCoordinate();
		List<Point> points = handlePoints(tryCreatePoints(input));

		while (points == null) {
			OutputView.printError("잘못된 좌표 입력입니다.");
			input = InputView.inputCoordinate();
			points = tryCreatePoints(input);
			points = handlePoints(points);
		}
	}

	private static List<Point> tryCreatePoints(String s) {
		try {
			return Point.createWithPair(s);
		} catch (IllegalArgumentException e) {
			return Arrays.asList(tryCreateSinglePoint(s));
		}
	}

	private static Point tryCreateSinglePoint(String s) {
		try {
			return new Point(s);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	private static List<Point> handlePoints(List<Point> points) {
		try {

			if (points.size() == Square.NUM_OF_POINTS) {
				int area = new Square(points).calculateArea();
				OutputView.printCoordinate(points);
				OutputView.printSquarArea(area);
				return points;
			}
			if (points.size() == Line.NUM_OF_POINTS) {
				double distance = new Line(points).calculateLength();
				OutputView.printDistance(distance);
				return points;
			}
		} catch(IllegalArgumentException e) { }
		return null;
	}
}
