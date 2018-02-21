package es.usc.citius.utils.testing.generator;

import es.usc.citius.utils.generator.generators.StringGenerator;
import org.kohsuke.randname.RandomNameGenerator;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;

public class NameGenerator implements StringGenerator{
    private RandomNameGenerator rnd = new RandomNameGenerator(ThreadLocalRandom.current().nextInt());

    @Override
    public String generate(Field field, int length, int minLength, int maxLength) {
        String name = rnd.next().split("_")[0];
        return Character.toUpperCase(name.charAt(0)) + name.substring(1) ;
    }
}
