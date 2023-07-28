package edu.poly.domain;

public class ChangePassF {
	private String username, password, confirmPassword, currentPassword;

	public ChangePassF() {

	}

	public ChangePassF(String username, String password, String confirmPassword, String currentPassword) {
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.currentPassword = currentPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPasswrod() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getCurrentPassowrd() {
		return currentPassword;
	}

	public void setCurrentPassowrd(String currentPassword) {
		this.currentPassword = currentPassword;
	}

}
