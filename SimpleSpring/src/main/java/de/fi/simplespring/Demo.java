package de.fi.simplespring;


import de.fi.simplespring.translator.Translator;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON) // Default
//@Scope(BeanDefinition.SCOPE_PROTOTYPE) // Default

//@Lazy

//@Named
public class Demo {
    // 2. Danach der Inject


    private final String gruss ;

    private final Translator translator;



    // 1. Aufruf Konstruktor

    //@Autowired
    public Demo( final Translator translator, @Value("${Demo.gruss}") final String gruss) {
        this.translator = translator;
        this.gruss = gruss;
        System.out.println(translator.translate("Ctor von Demo"));
        System.out.println(translator.translate(gruss));
    }

    // 3. Nach Inject
    @PostConstruct
    public void play() {
        System.out.println(translator.translate(gruss));
    }

    @PreDestroy // ACHTUNG nur bei scope singleton
    public void close() {
        System.out.println(translator.translate("Close Demo"));
    }

}
