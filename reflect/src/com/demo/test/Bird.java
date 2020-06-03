package com.demo.test;

public class Bird implements Fly{

	@Override
	public void up() {
		System.out.println("向上飞起");
	}

	@Override
	public void down() {
		System.out.println("向下飞");
	}

}
