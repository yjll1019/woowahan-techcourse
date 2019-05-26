package com.woowacourse.coordinate;

import java.util.ArrayList;
import java.util.List;

import com.woowacourse.coordinate.domain.*;
import com.woowacourse.coordinate.utils.StringSeparator;
import com.woowacourse.coordinate.view.InputView;
import com.woowacourse.coordinate.view.OutputView;

public class AppMain {
	public static void main(String[] args) {
		calculateCoordinates();
	}

	public static void calculateCoordinates() {
		try {
			Points points = createPoints();
			Figure figure = createFigure(points);

			OutputView.printCoordinate(points);
			printFigure(figure);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			calculateCoordinates();
		}
	}

	public static Points createPoints() {
		List<String> coordinates = StringSeparator.separateString(InputView.inputCoordinate());
		List<Point> points = new ArrayList<>();

		for(String s : coordinates) {
			List<Integer> coordinate = StringSeparator.separateCoordinate(s);
			points.add(new Point(coordinate.get(0), coordinate.get(1)));
		}

		return new Points(points);
	}

	public static void printFigure(Figure figure) {
		if (figure == null) {
			return;
		}
		OutputView.printShape(figure);
	}

	public static Figure createFigure(Points points) {
		FigureCreator figureCreator = new FigureFactory();

		if (points.getPoints().size() != 1) {
			return figureCreator.create(points);
		}
		return null;
	}
}
