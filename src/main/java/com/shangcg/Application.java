package com.shangcg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.shangcg.dao")   //扫描mapper文件
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
}
