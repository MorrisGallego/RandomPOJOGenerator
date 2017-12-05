package es.usc.citius.utils.testing.generator;

import es.usc.citius.utils.generator.generators.StringGenerator;
import org.kohsuke.randname.RandomNameGenerator;

import java.util.concurrent.ThreadLocalRandom;

public class CustomIdGenerator implements StringGenerator{
    private RandomNameGenerator rnd = new RandomNameGenerator(ThreadLocalRandom.current().nextInt());

    @Override
    public String generate(int length, int minLength, int maxLength) {
        return rnd.next();
    }
}
