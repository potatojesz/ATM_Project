package tklimczak.atm.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tklimczak.atm.exception.AccountNotFoundException;
import tklimczak.atm.exception.NotEnoughBalanceException;
import tklimczak.atm.model.Account;
import tklimczak.atm.repository.AccountRepository;
import tklimczak.atm.security.AccountPrincipal;
import tklimczak.atm.service.ATMService;

@Service("atmService")
public class ATMServiceLocal implements ATMService {

	@Autowired
	private transient AccountRepository accountRepository;

	@Override
	@Transactional(readOnly = false)
	public void addMoney(BigDecimal amount) {
		Account account = getAccount();
		account.setBalance(account.getBalance().add(amount));
		accountRepository.save(account);
	}

	@Override
	@Transactional(readOnly = false)
	public void withdrawMoney(BigDecimal amount) throws NotEnoughBalanceException {
		Account account = getAccount();
		if(amount.compareTo(account.getBalance()) < 0) {
			account.setBalance(account.getBalance().subtract(amount));
        } else {
            throw new NotEnoughBalanceException("Not enough balance on your account!");
        }
		accountRepository.save(account);
	}

	@Override
	@Transactional(readOnly = true)
	public BigDecimal getBalance() {
		Account account = getAccount();
		return account.getBalance();
	}

	private Account getAccount() {
		AccountPrincipal accountPrincipal= (AccountPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String cardNumber = accountPrincipal.getNumber();
		return accountRepository.findByNumber(cardNumber)
				.orElseThrow(() -> 
					new AccountNotFoundException("Account not found with number: " + cardNumber)
				);
	}
}
