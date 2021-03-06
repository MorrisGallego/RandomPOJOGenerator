package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.StringGenerator;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class BasicStringGenerator implements StringGenerator{
    public String generate(Field field, int length, int minLength, int maxLength){
        BasicCharacterGenerator generator = new BasicCharacterGenerator();
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        StringBuilder sb = new StringBuilder();

        length = length < 0 ? Math.max(rnd.nextInt(minLength, maxLength), 0) : Math.max(length, 0);
        Stream.generate(() -> generator.generate(field)).limit(length).forEach(sb::append);

        return sb.toString();
    }
}
