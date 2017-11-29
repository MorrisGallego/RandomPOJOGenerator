package es.usc.citius.utils.generator;

import es.usc.citius.utils.generator.model.Person;

import java.util.List;

public class Test {
    public static void main(String[] args){
        List<Person> generated = RandomGenerator.generate(10, Person.class);

        System.out.println(generated);
    }
}
