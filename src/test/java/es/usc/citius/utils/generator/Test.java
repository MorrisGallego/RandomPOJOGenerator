package es.usc.citius.utils.generator;

import es.usc.citius.utils.generator.exceptions.InvalidGeneratorException;
import es.usc.citius.utils.generator.model.Person;

import java.util.List;

public class Test {
    public static void main(String[] args) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        List<?> gen = (List<?>) RandomGenerator
                .forType(Person.class)
                .withDefaultStringGenerator(es.usc.citius.utils.generator.generator.CustomStringGenerator.class)
                .generate(1000);

        System.out.println(gen.size());
    }
}
