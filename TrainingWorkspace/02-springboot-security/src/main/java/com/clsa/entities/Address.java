package com.clsa.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("address")
@Scope(scopeName="prototype")
public class Address {
	private String city, state, pin;
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
		//System.out.println("setCity: "+city);
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		//System.out.println("setState: "+state);
		this.state = state;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		//System.out.println("setPin: "+pin);
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "Address [city=" + city + ", state=" + state + ", pin=" + pin + "]";
	}
	
}
