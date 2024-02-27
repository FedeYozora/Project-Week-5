package it.epicode.U5W4BW;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class App {

    public static void main(String[] args) throws FileNotFoundException {
        SpringApplication.run(App.class, args);

    }

}
