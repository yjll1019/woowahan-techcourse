package com.woowacourse;

import java.util.Arrays;
import java.util.List;

import com.woowacourse.coordinate.*;

public class AppMain {
	public static void main(String[] args) {
		String input = InputView.inputCoordinate();
		List<Point> points = tryHandlePoints(tryCreatePoints(input));

		while (points == null) {
			OutputView.printError("잘못된 좌표 입력입니다.");
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
		if (handleSquare(points)) {
			return points;
		}
		if (handleTriangle(points)) {
			return points;
		}
		if (handleLine(points)) {
			return points;
		}
		return null;
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

	private static boolean handleTriangle(List<Point> points) {
		if (points.size() == Triangle.NUM_OF_POINTS) {
			double area = new Triangle(points).calculateArea();
			OutputView.printCoordinate(points);
			OutputView.printTriangleArea(area);
			return true;
		}
		return false;
	}

	private static boolean handleSquare(List<Point> points) {
		if (points.size() == Square.NUM_OF_POINTS) {
			int area = new Square(points).calculateArea();
			OutputView.printCoordinate(points);
			OutputView.printSquareArea(area);
			return true;
		}
		return false;
	}
}
