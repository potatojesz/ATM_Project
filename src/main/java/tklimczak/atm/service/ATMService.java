package tklimczak.atm.service;

import java.math.BigDecimal;

import tklimczak.atm.model.exception.NotEnoughBalanceException;

public interface ATMService {
	public void addMoney(BigDecimal amount);
	public void withdrawMoney(BigDecimal amount) throws NotEnoughBalanceException;
	public BigDecimal getBalance();
}
