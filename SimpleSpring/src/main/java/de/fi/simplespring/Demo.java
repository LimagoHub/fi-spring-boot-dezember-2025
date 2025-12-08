package de.fi.simplespring;


import jakarta.inject.Named;
import org.springframework.stereotype.Component;

@Component
//@Named
public class Demo {

    public Demo() {
        System.out.println("Ctor von Demo");
    }
}
