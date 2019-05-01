package tklimczak.atm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tklimczak.atm.exception.AccountNotFoundException;
import tklimczak.atm.model.Account;
import tklimczak.atm.repository.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String number)
            throws AccountNotFoundException {
        Account account = accountRepository.findByNumber(number)
                .orElseThrow(() -> 
                        new AccountNotFoundException("Account not found with number: " + number)
        );

        return AccountPrincipal.create(account);
    }
}
