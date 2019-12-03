package com.woowacourse.lotto.domain;

import java.util.Objects;

import com.woowacourse.lotto.exception.InvalidMoneyException;

public class LottoMoney {
	private static final int PERCENT = 100;
	public static final int MINIMUM_MONEY_FOR_PURCHASE = 1000;
	private final int money;

	public LottoMoney(int money) {
		validateMoney(money);
		this.money = money;
	}

	private void validateMoney(int money) {
		if (money < MINIMUM_MONEY_FOR_PURCHASE || (money % MINIMUM_MONEY_FOR_PURCHASE != 0)) {
			throw new InvalidMoneyException();
		}
	}

	public int getCountOfLotto() {
		return money / MINIMUM_MONEY_FOR_PURCHASE;
	}

	public long calculateEarningsRate(double winningAmount) {
		return (long) ((winningAmount / money) * PERCENT);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof LottoMoney)) {
			return false;
		}
		final LottoMoney lottoMoney1 = (LottoMoney) o;
		return money == lottoMoney1.money;
	}

	@Override
	public int hashCode() {
		return Objects.hash(money);
	}

	@Override
	public String toString() {
		return String.format("%d", money);
	}
}
