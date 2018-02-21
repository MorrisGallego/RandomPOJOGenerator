package es.usc.citius.utils.generator;

import es.usc.citius.utils.generator.exceptions.InvalidGeneratorException;

public class GeneratorBuilder{
    private Generator g = null;
    private MultiTypeGenerator parentGenerator = null;

    private GeneratorBuilder(){}
    private GeneratorBuilder(MultiTypeGenerator parent){
        parentGenerator = parent;
    }

    public GeneratorBuilder withDefaultStringGenerator(Class c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        g = g.withDefaultStringGenerator(c);
        return this;
    }
    public GeneratorBuilder withDefaultDoubleGenerator(Class c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        g = g.withDefaultDoubleGenerator(c);
        return this;
    }
    public GeneratorBuilder withDefaultIntegerGenerator(Class c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        g = g.withDefaultIntegerGenerator(c);
        return this;
    }
    public GeneratorBuilder withDefaultFloatGenerator(Class c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        g = g.withDefaultFloatGenerator(c);
        return this;
    }
    public GeneratorBuilder withDefaultLongGenerator(Class c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        g = g.withDefaultLongGenerator(c);
        return this;
    }
    public GeneratorBuilder withDefaultBooleanGenerator(Class c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        g = g.withDefaultBooleanGenerator(c);
        return this;
    }
    public GeneratorBuilder withDefaultShortGenerator(Class c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        g = g.withDefaultShortGenerator(c);
        return this;
    }
    public GeneratorBuilder withDefaultCharacterGenerator(Class c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        g = g.withDefaultCharacterGenerator(c);
        return this;
    }
    public GeneratorBuilder withDefaultByteGenerator(Class c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        g = g.withDefaultByteGenerator(c);
        return this;
    }

    public static GeneratorBuilder forType(Class type) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        GeneratorBuilder builder = new GeneratorBuilder();
        builder.g = Generator.forType(type);
        return builder;
    }
    public GeneratorBuilder toMultiTypeGenerator(MultiTypeGenerator parent){
        this.parentGenerator = parent;
        return this;
    }

    public Generator build() {
        return g;
    }

    public MultiTypeGenerator add() {
        if(this.parentGenerator == null)
            this.parentGenerator = MultiTypeGenerator.create();

        this.parentGenerator.addGenerator(this.build());

        return this.parentGenerator;
    }
}