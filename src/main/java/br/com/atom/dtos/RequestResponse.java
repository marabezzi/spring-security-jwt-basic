package br.com.atom.dtos;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.atom.entities.OurUsers;
import br.com.atom.entities.Product;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestResponse {

	private int statusCode;
	private String error;
	private String message;
	private String token;
	private String refreshToken;
	private String expirationTime;
	private String name;
	private String email;
	private String role;
	private String password;
	private List<Product> product;
	private OurUsers ourUsers;
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
	public OurUsers getOurUsers() {
		return ourUsers;
	}
	public void setOurUsers(OurUsers ourUsers) {
		this.ourUsers = ourUsers;
	}
	
	public RequestResponse() {
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, error, expirationTime, message, name, ourUsers, password, product, refreshToken,
				role, statusCode, token);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestResponse other = (RequestResponse) obj;
		return Objects.equals(email, other.email) && Objects.equals(error, other.error)
				&& Objects.equals(expirationTime, other.expirationTime) && Objects.equals(message, other.message)
				&& Objects.equals(name, other.name) && Objects.equals(ourUsers, other.ourUsers)
				&& Objects.equals(password, other.password) && Objects.equals(product, other.product)
				&& Objects.equals(refreshToken, other.refreshToken) && Objects.equals(role, other.role)
				&& statusCode == other.statusCode && Objects.equals(token, other.token);
	}
	
}
