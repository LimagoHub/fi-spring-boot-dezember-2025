package de.fi.simplespring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(10)
public class MyRunner implements CommandLineRunner {


    @Override
    public void run(final String... args) throws Exception {
        System.out.println("Hello World");
    }
}
