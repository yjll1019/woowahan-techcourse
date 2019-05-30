package com.woowacourse.fuel;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RentCarTest {
	@Test
	void generateCar() {
		RentCar rentCar = new Sonata(150);
		assertThat(rentCar).isEqualTo(new Sonata(150));
	}
}
