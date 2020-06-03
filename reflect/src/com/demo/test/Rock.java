package com.demo.test;

public class Rock implements Fly{

	@Override
	public void up() {
		System.out.println("火箭升空");
	}

	@Override
	public void down() {
		System.out.println("火箭降落");
	}

}
