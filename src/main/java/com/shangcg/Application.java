package com.shangcg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan(value = "com.shangcg.dao")   //扫描mapper接口
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
}
