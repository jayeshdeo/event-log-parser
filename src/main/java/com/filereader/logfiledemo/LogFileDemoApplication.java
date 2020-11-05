package com.filereader.logfiledemo;

import com.filereader.logfiledemo.service.LogEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogFileDemoApplication implements ApplicationRunner {

    @Autowired
    private LogEventService logEventService;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(LogFileDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments arguments) throws Exception {
        String[] a = arguments.getSourceArgs();
        String path = a[0];
        this.logEventService.processLogEvent(path);
    }


}
