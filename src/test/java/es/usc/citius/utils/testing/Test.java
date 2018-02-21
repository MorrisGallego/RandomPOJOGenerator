package es.usc.citius.utils.testing;

import es.usc.citius.utils.generator.Generator;
import es.usc.citius.utils.generator.MultiTypeGenerator;
import es.usc.citius.utils.generator.exceptions.InvalidGeneratorException;
import es.usc.citius.utils.generator.rotationStrategies.RoundRobinGeneratorRotationStrategy;
import es.usc.citius.utils.testing.model.Address;
import es.usc.citius.utils.testing.model.Name;
import es.usc.citius.utils.testing.model.Person;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<?> gen = null;
        List<?> gen2 = null;
        try {
            gen = Generator
                    .forType(Person.class)
                    .generate(10);

            gen2 = MultiTypeGenerator
                    .forTypes(Address.class, Name.class)
                    .withGeneratorRotationStrategy(RoundRobinGeneratorRotationStrategy.getInstance())
                    .generate(10);
        } catch (InvalidGeneratorException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        System.out.println(gen);
        System.out.println(gen2);
    }
}
