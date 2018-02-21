package es.usc.citius.utils.testing.generator;

import es.usc.citius.utils.generator.generators.StringGenerator;
import org.kohsuke.randname.RandomNameGenerator;

import java.lang.reflect.Field;

public class IdGenerator implements StringGenerator{
    private RandomNameGenerator rnd = new RandomNameGenerator();

    @Override
    public String generate(Field field, int length, int minLength, int maxLength) {
        return String.format("%s@example.mail", rnd.next());
    }
}
