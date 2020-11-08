package com.shangcg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;
import com.shangcg.Greeting;
import com.shangcg.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	// http://localhost:8080/greeting?name=shangcg  访问测试路径  测试服务是否启动

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
		return new Greeting(counter.incrementAndGet(),
							String.format(template, name));
	}

	@RequestMapping("/heap")
	public void heap() {
		System.out.println("success into");

		//内存泄漏
		Vector v=new Vector(1000);
		for (int i=1;i<10000000; i++)
		{
			User o=new User();
			v.add(o);
			o=null;
		}
	}
}
