package es.usc.citius.utils.testing;

import es.usc.citius.utils.generator.Generator;
import es.usc.citius.utils.generator.MultiTypeGenerator;
import es.usc.citius.utils.generator.exceptions.InvalidGeneratorException;
import es.usc.citius.utils.testing.generator.CustomIntegerGenerator;
import es.usc.citius.utils.testing.generator.CustomStringGenerator;
import es.usc.citius.utils.testing.model.Address;
import es.usc.citius.utils.testing.model.Name;
import es.usc.citius.utils.testing.model.Person;

import java.util.List;

public class Test {
    public static void main(String[] args) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        List<?> gen = (List<?>) Generator
                .forType(Person.class)
                .withDefaultStringGenerator(CustomStringGenerator.class)
                .generate(10);

        System.out.println(gen);

        gen = MultiTypeGenerator
                .forTypes(Name.class, Address.class)
                .withDefaultIntegerGenerator(CustomIntegerGenerator.class)
                .generate(10);
        System.out.println(gen);

    }
}
