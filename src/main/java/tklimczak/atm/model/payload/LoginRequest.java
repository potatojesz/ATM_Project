package tklimczak.atm.model.payload;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
	private String cardNumber;

	@NotBlank
	private String pin;

	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
}
