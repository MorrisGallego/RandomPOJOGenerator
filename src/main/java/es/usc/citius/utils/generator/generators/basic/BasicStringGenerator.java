package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.StringGenerator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicStringGenerator implements StringGenerator{
    public String generate(int length, int minLength, int maxLength){
        BasicCharacterGenerator generator = new BasicCharacterGenerator();
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        StringBuilder sb = new StringBuilder();

        length = length < 0 ? Math.max(rnd.nextInt(minLength, maxLength), 0) : Math.max(length, 0);
        Stream.generate(generator::generate).limit(length).forEach(sb::append);

        return sb.toString();
    }
    public List<String> generate(int length, int minLength, int maxLength, int count){
        return Stream.generate(() -> generate(length, minLength, maxLength)).limit(count).collect(Collectors.toList());
    }
}
