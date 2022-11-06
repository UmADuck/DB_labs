package com.iot;

import com.iot.view.MyView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private MyView view;

    public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        }

    @Override
    public void run(String... args) throws Exception {
        view.show();
        }

}
