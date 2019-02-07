package com.roma.librarymanagment;

import com.roma.librarymanagment.bootstrap.DevBootStrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibrarymanagmentsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevBootStrap.class, args);
    }

}

