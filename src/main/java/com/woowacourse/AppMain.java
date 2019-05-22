package com.woowacourse;

import java.util.Arrays;
import java.util.List;

import com.woowacourse.coordinate.*;

public class AppMain {
	public static void main(String[] args) {
		String input = InputView.inputCoordinate();
		List<Point> points = tryHandlePoints(tryCreatePoints(input));

		while (points == null) {
			input = InputView.inputCoordinate();
			points = tryCreatePoints(input);
			points = tryHandlePoints(points);
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

	private static List<Point> tryHandlePoints(List<Point> points) {
		try {
			return handlePoints(points);
		} catch(IllegalArgumentException e) { }
		return null;
	}

	private static List<Point> handlePoints(List<Point> points) {
		if (tryHandleSquare(points)) {
			return points;
		}
		if (tryHandleTriangle(points)) {
			return points;
		}
		if (tryHandleLine(points)) {
			return points;
		}
		if (points.size() == 1) {
			OutputView.printCoordinate(points);
			return points;
		}
		return null;
	}

	private static boolean tryHandleLine(List<Point> points) {
		try {
			return handleLine(points);
		} catch (PointDuplicateException e) {
			OutputView.printError("위치가 같은 점(point)이 존재합니다. 두 점의 위치는 달라야 합니다.");
			return false;
		}
	}

	private static boolean handleLine(List<Point> points) {
		if (points.size() == Line.NUM_OF_POINTS) {
			double distance = new Line(points).calculateLength();
			OutputView.printCoordinate(points);
			OutputView.printDistance(distance);
			return true;
		}
		return false;
	}

	private static boolean tryHandleTriangle(List<Point> points) {
		try {
			return handleTriangle(points);
		} catch (PointDuplicateException e) {
			OutputView.printError("위치가 같은 점(point)이 존재합니다. 세 점의 위치는 달라야 합니다.");
			return false;
		}
	}

	private static boolean handleTriangle(List<Point> points) {
		if (points.size() == Triangle.NUM_OF_POINTS) {
			Shape s = new Triangle(points);
			OutputView.printCoordinate(points);
			OutputView.printShape(s);
			return true;
		}
		return false;
	}

	private static boolean tryHandleSquare(List<Point> points) {
		try {
			return handleSquare(points);
		} catch (PointDuplicateException e) {
			OutputView.printError("위치가 같은 점(point)이 존재합니다. 네 점의 위치는 달라야 합니다.");
			return false;
		}
	}

	private static boolean handleSquare(List<Point> points) {
		if (points.size() == Square.NUM_OF_POINTS) {
			Shape s = new Square(points);
			OutputView.printCoordinate(points);
			OutputView.printShape(s);
			return true;
		}
		return false;
	}
}
