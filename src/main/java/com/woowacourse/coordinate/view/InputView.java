package com.woowacourse.coordinate.view;

import java.util.Scanner;

public class InputView {
	public static String inputCoordinate() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("좌표를 입력하세요.");
		return scanner.nextLine();
	}
}
