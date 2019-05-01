package tklimczak.atm.payload;

public class JwtAuthenticationResponse {
	private String token;
	private String tokenType = "Bearer";
	private String number;

	public JwtAuthenticationResponse(String accessToken, String number) {
		this.token = accessToken;
		this.number = number;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String username) {
		this.number = username;
	}
}
