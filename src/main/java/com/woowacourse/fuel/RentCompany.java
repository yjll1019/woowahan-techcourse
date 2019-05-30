package com.woowacourse.fuel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RentCompany {
	private static final String NEWLINE = System.getProperty("line.separator");

	private final List<Car> rentCars;

	private RentCompany() {
		rentCars = new ArrayList<>();
	}

	public static RentCompany create() {
		return new RentCompany();
	}

	public void addCar(RentCar rentCar) {
		rentCars.add(rentCar);
	}

	public String generateReport() {
		StringBuilder sb = new StringBuilder();
		sb.append(rentCars.stream()
				.map(c -> String.format("%s : %d리터",
							c.getName(), (int)c.getChargeQuantity()))
				.collect(Collectors.joining(NEWLINE)))
				.append(NEWLINE);
		return sb.toString();
	}
}
