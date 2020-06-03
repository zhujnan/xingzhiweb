package com.bean;

public class User {
	private String name;
	private int age;
	private String[] interest;
	private IDCard card;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String[] getInterest() {
		return interest;
	}
	public void setInterest(String[] interest) {
		this.interest = interest;
	}
	public IDCard getCard() {
		return card;
	}
	public void setCard(IDCard card) {
		this.card = card;
	}
	
}
