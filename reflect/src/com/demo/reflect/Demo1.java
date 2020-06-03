package com.demo.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Demo1 {
	public static void main(String[] args) throws Exception {
		Person p = new Person();
		p.setName("zhangsan");
		System.out.println(p.getName());
		//1.通过反射来创建对象
		Class c = p.getClass();//获取Class对象
		Person p2 = (Person) c.newInstance();
		p2.setName("马云");
		System.out.println(p2.getName());
		
		//获取类中的私有属性
		Field f = c.getDeclaredField("name");
		f.setAccessible(true);
		f.set(p2, "你好");
		System.out.println(f);
		//调用类中的方法
		Method m = c.getMethod("show", null);
		m.invoke(p2, null);
		
	}
}
