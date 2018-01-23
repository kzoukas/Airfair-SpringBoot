package com.thesis.tuc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;

@SpringBootApplication
public class MyApplication {
    public static void main(String[] args){
        SpringApplication myApplication = new SpringApplication(MyApplication.class);
        myApplication.addListeners(new ApplicationPidFileWriter("thesis.pid"));
        myApplication.run(args);
    }
}
