package com.shangcg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

@SpringBootApplication(scanBasePackages = {"com.shangcg"})
@MapperScan(value = "com.shangcg.dao")   //扫描mapper文件
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		ThreadGroup currentGroup =
				Thread.currentThread().getThreadGroup();

		int noThreads = currentGroup.activeCount();
		Thread[] lstThreads = new Thread[noThreads];
		currentGroup.enumerate(lstThreads);
		for (int i = 0; i < noThreads; i++)
			System.out.println("线程号：" + i + " = " + lstThreads[i].getName());

	}


	private static void s2() throws IllegalAccessException, NoSuchFieldException {
		String s = new String("abc");
		final Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
		theUnsafe.setAccessible(true);
		final Object o = theUnsafe.get(Unsafe.class);
		final Unsafe unsafe = (Unsafe) o;
		unsafe.getAndSetObject(s,unsafe.objectFieldOffset(String.class.getDeclaredField("value")), new char[]{'a','b','c','d'});
		System.out.println("s = " + s);
	}

	private static void s1() throws NoSuchFieldException, IllegalAccessException {
		String s = new String("abc");
		System.out.println("s.hashCode() = " + s.hashCode());
		final Field value = String.class.getDeclaredField("value");
		value.setAccessible(true);
		value.set(s,new char[]{'a','b','c','d'});
		System.out.println("s.hashCode() = " + s.hashCode());
		System.out.println("s = " + s);
	}
}
