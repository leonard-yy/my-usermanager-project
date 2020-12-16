package com.lgsoftware.supportbuild.myusermanagerproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan(basePackages = "com.lgsoftware.supportbuild.myusermanagerproject.*.dao")
public class MyUsermanagerProjectApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder){
        return springApplicationBuilder.sources(MyUsermanagerProjectApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MyUsermanagerProjectApplication.class, args);
    }

}
