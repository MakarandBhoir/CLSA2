package com.clsa.model;

import javax.persistence.Embeddable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName="prototype")
@Embeddable
public class Address {
	private String city;
	private String state;
	private String pin;
	public Address() {
		
	}
	public Address(String city, String state, String pin) {
		super();
		this.city = city;
		this.state = state;
		this.pin = pin;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	@Override
	public String toString() {
		return "Address [city=" + city + ", state=" + state + ", pin=" + pin + "]";
	}
}
