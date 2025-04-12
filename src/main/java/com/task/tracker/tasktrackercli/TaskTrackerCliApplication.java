package com.task.tracker.tasktrackercli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import  org.slf4j.LoggerFactory.*;
@SpringBootApplication
public class TaskTrackerCliApplication {

    public static void main(String[] args) {
           SpringApplication.run(TaskTrackerCliApplication.class, args);
           Logger logger = LoggerFactory.getLogger(TaskTrackerCliApplication.class);
           logger.info("Application Started successfully.......... ");

    }

}
