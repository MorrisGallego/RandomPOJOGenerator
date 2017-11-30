package es.usc.citius.utils.generator;

import es.usc.citius.utils.generator.model.Person;

import java.util.List;

public class Test {
    public static void main(String[] args){
        List<Person> gen = (List<Person>) RandomGenerator.forType(Person.class).generate(5);
        System.out.println(gen);
    }
}
