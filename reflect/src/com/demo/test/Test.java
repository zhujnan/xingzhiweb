package com.demo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Test {
	public void show(Fly fly){
		if(fly !=null){
			fly.up();
			fly.down();
		}
	}
	public static void main(String[] args) throws IOException, Exception {
		Test t = new Test();
		//t.show(new Bird());
		File f = new File("pb.properties");
		FileInputStream fis = new FileInputStream(f);
		Properties p = new Properties();
		
		p.load(fis);
		for(int i=0;i<p.size();i++){
			String name = p.getProperty("d"+(i+1));
			Class c = Class.forName(name);
			Fly fly = (Fly) c.newInstance();
			t.show(fly);
		}
		fis.close();
	}
}
