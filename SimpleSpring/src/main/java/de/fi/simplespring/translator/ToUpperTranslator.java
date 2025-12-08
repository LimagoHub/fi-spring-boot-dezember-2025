package de.fi.simplespring.translator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//@Primary
//@Qualifier("upper")
@Profile({"test","dev"})
public class ToUpperTranslator implements Translator {

    @Override
    public String translate(final String text) {
        return text.toUpperCase();
    }
}
