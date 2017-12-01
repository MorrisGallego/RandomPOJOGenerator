package es.usc.citius.utils.generator;

import es.usc.citius.utils.generator.exceptions.InvalidGeneratorException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultiTypeGenerator {
    private List<Generator> generators = new ArrayList<>();
    private MultiTypeGenerator(){}

    private MultiTypeGenerator addDefaultGeneratorsForTypes(Class ... types) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        MultiTypeGenerator generator = new MultiTypeGenerator();

        for(Class c: types){
            generator.addGenerator(Generator.forType(c));
        }

        return generator;
    }

    public MultiTypeGenerator withDefaultStringGenerator(Class<?> c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        for(Generator generator: generators)
            generator.withDefaultStringGenerator(c);
        return this;
    }
    public MultiTypeGenerator withDefaultDoubleGenerator(Class<?> c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        for(Generator generator: generators)
            generator.withDefaultDoubleGenerator(c);
        return this;
    }
    public MultiTypeGenerator withDefaultIntegerGenerator(Class<?> c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        for(Generator generator: generators)
            generator.withDefaultIntegerGenerator(c);
        return this;
    }
    public MultiTypeGenerator withDefaultFloatGenerator(Class<?> c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        for(Generator generator: generators)
            generator.withDefaultFloatGenerator(c);
        return this;
    }
    public MultiTypeGenerator withDefaultLongGenerator(Class<?> c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        for(Generator generator: generators)
            generator.withDefaultLongGenerator(c);
        return this;
    }
    public MultiTypeGenerator withDefaultBooleanGenerator(Class<?> c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        for(Generator generator: generators)
            generator.withDefaultBooleanGenerator(c);
        return this;
    }
    public MultiTypeGenerator withDefaultShortGenerator(Class<?> c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        for(Generator generator: generators)
            generator.withDefaultShortGenerator(c);
        return this;
    }
    public MultiTypeGenerator withDefaultCharacterGenerator(Class<?> c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        for(Generator generator: generators)
            generator.withDefaultCharacterGenerator(c);
        return this;
    }
    public MultiTypeGenerator withDefaultByteGenerator(Class<?> c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        for(Generator generator: generators)
            generator.withDefaultByteGenerator(c);
        return this;
    }

    public static MultiTypeGenerator create(){ return new MultiTypeGenerator(); }
    public static MultiTypeGenerator forTypes(Class ... types){
        try {
            return MultiTypeGenerator.create().addDefaultGeneratorsForTypes(types);
        } catch (InvalidGeneratorException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
    public MultiTypeGenerator addGenerator(Generator generator){
        generators.add(generator);
        return this;
    }

    public Object generate(){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        return generators.get(rnd.nextInt(0, generators.size())).generate();
    }
    public List<?> generate(int count){
        return this.stream().limit(count).collect(Collectors.toList());
    }
    public Stream<?> stream(){
        return Stream.generate(this::generate);
    }
}
