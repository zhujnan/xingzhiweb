package com.demo.test;
/**
 * �ɻ���
 * @author admin
 *
 */
public class Plane implements Fly{

	@Override
	public void up() {
		System.out.println("飞机飞了");
	}

	@Override
	public void down() {
		System.out.println("飞机降落了");
	}

}
