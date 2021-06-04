package com.shangcg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.shangcg.dao")   //扫描mapper文件
public class Application {


	//jvm参数 配置 VM options:  -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./heapdump20.hprof
	//http://localhost:8080/showUser?userId=8     测试mybatis是否连接正常
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
}
