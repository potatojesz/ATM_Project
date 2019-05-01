package tklimczak.atm.payload;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
	private String accountNumber;

	@NotBlank
	private String pin;

	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
}
