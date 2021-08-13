package org.vaultage.demo.game.monetisation.contract;

import java.math.BigInteger;

import org.web3j.tx.gas.ContractGasProvider;

public class CustomGasProvider implements ContractGasProvider {

	private static final int GAS_LIMIT = 6721975;
	private static final int GAS_PRICE = 20000000;

	@Override
	public BigInteger getGasPrice(String contractFunc) {
		return BigInteger.valueOf(GAS_PRICE);
	}

	@Override
	public BigInteger getGasPrice() {
		return BigInteger.valueOf(GAS_PRICE);
	}

	@Override
	public BigInteger getGasLimit(String contractFunc) {
		return BigInteger.valueOf(GAS_LIMIT);
	}

	@Override
	public BigInteger getGasLimit() {
		return BigInteger.valueOf(GAS_LIMIT);
	}
};
