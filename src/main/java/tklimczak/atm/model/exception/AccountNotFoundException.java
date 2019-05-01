package tklimczak.atm.model.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AccountNotFoundException extends UsernameNotFoundException {

	private static final long serialVersionUID = 1L;

    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
