package es.usc.citius.utils.generator;

import es.usc.citius.utils.generator.annotations.*;
import es.usc.citius.utils.generator.exceptions.InvalidGeneratorException;
import es.usc.citius.utils.generator.exceptions.InvalidTypeForAnnotationException;
import es.usc.citius.utils.generator.generators.*;
import es.usc.citius.utils.generator.generators.basic.*;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomGenerator<T> {
    private Class<T> _type;

    private BooleanGenerator _defaultBooleanGenerator = new BasicBooleanGenerator();
    private DoubleGenerator _defaultDoubleGenerator = new BasicDoubleGenerator();
    private FloatGenerator _defaultFloatGenerator = new BasicFloatGenerator();
    private IntegerGenerator _defaultIntegerGenerator = new BasicIntegerGenerator();
    private LongGenerator _defaultLongGenerator = new BasicLongGenerator();
    private StringGenerator _defaultStringGenerator = new BasicStringGenerator();

    private RandomGenerator(Class<T> type){
        this._type = type;
    }

    private boolean isTypeOrArrayOfType(Field field, Class type) throws ClassNotFoundException {
        return isType(field, type) || isArrayOfType(field, type);
    }
    private boolean isArrayOfType(Field field, Class type) throws ClassNotFoundException {
        return isArray(field) && getListType(field).isAssignableFrom(type);
    }
    private Class<?> getListType(Field field) throws ClassNotFoundException {
        return Class.forName(((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0].getTypeName());
    }
    private boolean isType(Field field, Class<?> type){
        return type.isAssignableFrom(field.getType());
    }
    private boolean isArray(Field field){
        return isType(field, List.class);
    }

    private T processAnnotations(T instance) throws InvalidTypeForAnnotationException, InvalidGeneratorException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        instance = processStringFields(instance);
        instance = processDoubleFields(instance);
        instance = processIntegerFields(instance);
        instance = processFloatFields(instance);
        instance = processLongFields(instance);
        instance = processBooleanFields(instance);
        instance = processObjectFields(instance);

        return instance;
    }
    private T processStringFields(T instance) throws InvalidTypeForAnnotationException, InvalidGeneratorException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        Class currentClass = this._type;

        while(currentClass.getSuperclass() != null){
            for(Field field: currentClass.getDeclaredFields()){
                StringGenerator generator = _defaultStringGenerator;

                field.setAccessible(true);

                if(!field.isAnnotationPresent(RandomString.class)){
                    continue;
                }

                if(!isTypeOrArrayOfType(field, String.class)){
                    throw new InvalidTypeForAnnotationException(field.getType(), RandomString.class);
                } else {
                    RandomString annotation = field.getAnnotation(RandomString.class);

                    if(!annotation.generator().getCanonicalName().equals(StringGenerator.class.getCanonicalName())){
                        generator = (StringGenerator) createGeneratorForType(annotation.generator(), StringGenerator.class);
                    }

                    int count = annotation.count() >= 0 ? annotation.count() : rnd.nextInt(annotation.minCount(), annotation.maxCount()+1);
                    String [] from = annotation.from();
                    int length = annotation.length();
                    int maxLength = annotation.maxLength();
                    int minLength = annotation.minLength();

                    if(annotation.from().length == 0 && !isArray(field)) {
                        field.set(instance, generator.generate(length, minLength, maxLength));
                    } else if (annotation.from().length == 0 && isArray(field)){
                        field.set(instance, generator.generate(length, minLength, maxLength, count));
                    } else if (isArray(field)){
                        List <String> values = Stream.generate(() -> from[rnd.nextInt(0, from.length)]).limit(count).collect(Collectors.toList());
                        field.set(instance, values);
                    } else {
                        field.set(instance, from[rnd.nextInt(0, from.length)]);
                    }
                }
            }

            currentClass = currentClass.getSuperclass();
        }

        return instance;
    }
    private T processDoubleFields(T instance) throws InvalidTypeForAnnotationException, InvalidGeneratorException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        Class currentClass = this._type;

        while(currentClass.getSuperclass() != null){
            for(Field field: currentClass.getDeclaredFields()){
                DoubleGenerator generator = _defaultDoubleGenerator;
                field.setAccessible(true);

                if(!field.isAnnotationPresent(RandomDouble.class)){
                    continue;
                }

                if(!isTypeOrArrayOfType(field, Double.class) && !isType(field, double.class)){
                    throw new InvalidTypeForAnnotationException(field.getType(), RandomDouble.class);
                } else {
                    RandomDouble annotation = field.getAnnotation(RandomDouble.class);

                    if(!annotation.generator().getCanonicalName().equals(DoubleGenerator.class.getCanonicalName())){
                        generator = (DoubleGenerator) createGeneratorForType(annotation.generator(), DoubleGenerator.class);
                    }

                    int count = annotation.count() >= 0 ? annotation.count() : rnd.nextInt(annotation.minCount(), annotation.maxCount()+1);
                    double [] from = annotation.from();
                    double max = annotation.max();
                    double min = annotation.min();

                    if(annotation.from().length == 0 && !isArray(field)) {
                        field.set(instance, generator.generate(min, max));
                    } else if (annotation.from().length == 0 && isArray(field)){
                        field.set(instance, generator.generate(min, max, count));
                    } else if (isArray(field)){
                        List <Double> values = Stream.generate(() -> from[rnd.nextInt(0, from.length)]).limit(count).collect(Collectors.toList());
                        field.set(instance, values);
                    } else {
                        field.set(instance, from[rnd.nextInt(0, from.length)]);
                    }
                }
            }

            currentClass = currentClass.getSuperclass();
        }

        return instance;
    }
    private T processIntegerFields(T instance) throws InvalidTypeForAnnotationException, InvalidGeneratorException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        Class currentClass = this._type;

        while(currentClass.getSuperclass() != null){
            for(Field field: currentClass.getDeclaredFields()){
                IntegerGenerator generator = _defaultIntegerGenerator;

                field.setAccessible(true);

                if(!field.isAnnotationPresent(RandomInt.class)){
                    continue;
                }

                if(!isTypeOrArrayOfType(field, Integer.class) && !isType(field, int.class)){
                    throw new InvalidTypeForAnnotationException(field.getType(), RandomInt.class);
                } else {
                    RandomInt annotation = field.getAnnotation(RandomInt.class);

                    if(!annotation.generator().getCanonicalName().equals(IntegerGenerator.class.getCanonicalName())){
                        generator = (IntegerGenerator) createGeneratorForType(annotation.generator(), IntegerGenerator.class);
                    }

                    int count = annotation.count() >= 0 ? annotation.count() : rnd.nextInt(annotation.minCount(), annotation.maxCount()+1);
                    int [] from = annotation.from();
                    int max = annotation.max();
                    int min = annotation.min();

                    if(annotation.from().length == 0 && !isArray(field)) {
                        field.set(instance, generator.generate(min, max));
                    } else if (annotation.from().length == 0 && isArray(field)){
                        field.set(instance, generator.generate(min, max, count));
                    } else if (isArray(field)){
                        List <Integer> values = Stream.generate(() -> from[rnd.nextInt(0, from.length)]).limit(count).collect(Collectors.toList());
                        field.set(instance, values);
                    } else {
                        field.set(instance, from[rnd.nextInt(0, from.length)]);
                    }
                }
            }

            currentClass = currentClass.getSuperclass();
        }

        return instance;
    }
    private T processFloatFields(T instance) throws InvalidTypeForAnnotationException, InvalidGeneratorException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        Class currentClass = this._type;

        while(currentClass.getSuperclass() != null){
            for(Field field: currentClass.getDeclaredFields()){
                FloatGenerator generator = _defaultFloatGenerator;

                field.setAccessible(true);

                if(!field.isAnnotationPresent(RandomFloat.class)){
                    continue;
                }

                if(!isTypeOrArrayOfType(field, Float.class) && !isType(field, float.class)){
                    throw new InvalidTypeForAnnotationException(field.getType(), RandomFloat.class);
                } else {
                    RandomFloat annotation = field.getAnnotation(RandomFloat.class);

                    if(!annotation.generator().getCanonicalName().equals(FloatGenerator.class.getCanonicalName())){
                        generator = (FloatGenerator) createGeneratorForType(annotation.generator(), FloatGenerator.class);
                    }

                    int count = annotation.count() >= 0 ? annotation.count() : rnd.nextInt(annotation.minCount(), annotation.maxCount()+1);
                    float [] from = annotation.from();
                    float max = annotation.max();
                    float min = annotation.min();

                    if(annotation.from().length == 0 && !isArray(field)) {
                        field.set(instance, generator.generate(min, max));
                    } else if (annotation.from().length == 0 && isArray(field)){
                        field.set(instance, generator.generate(min, max, count));
                    } else if (isArray(field)){
                        List <Float> values = Stream.generate(() -> from[rnd.nextInt(0, from.length)]).limit(count).collect(Collectors.toList());
                        field.set(instance, values);
                    } else {
                        field.set(instance, from[rnd.nextInt(0, from.length)]);
                    }
                }
            }

            currentClass = currentClass.getSuperclass();
        }


        return instance;
    }
    private T processLongFields(T instance) throws InvalidTypeForAnnotationException, InvalidGeneratorException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        Class currentClass = this._type;

        while(currentClass.getSuperclass() != null){
            for(Field field: currentClass.getDeclaredFields()){
                LongGenerator generator = _defaultLongGenerator;

                field.setAccessible(true);

                if(!field.isAnnotationPresent(RandomLong.class)){
                    continue;
                }

                if((!isTypeOrArrayOfType(field, Long.class)) && !isType(field, long.class)){
                    throw new InvalidTypeForAnnotationException(field.getType(), RandomLong.class);
                } else {
                    RandomLong annotation = field.getAnnotation(RandomLong.class);

                    if(!annotation.generator().getCanonicalName().equals(LongGenerator.class.getCanonicalName())){
                        generator = (LongGenerator) createGeneratorForType(annotation.generator(), LongGenerator.class);
                    }

                    int count = annotation.count() >= 0 ? annotation.count() : rnd.nextInt(annotation.minCount(), annotation.maxCount()+1);
                    long [] from = annotation.from();
                    long max = annotation.max();
                    long min = annotation.min();

                    if(annotation.from().length == 0 && !isArray(field)) {
                        field.set(instance, generator.generate(min, max));
                    } else if (annotation.from().length == 0 && isArray(field)){
                        field.set(instance, generator.generate(min, max, count));
                    } else if (isArray(field)){
                        List <Long> values = Stream.generate(() -> from[rnd.nextInt(0, from.length)]).limit(count).collect(Collectors.toList());
                        field.set(instance, values);
                    } else {
                        field.set(instance, from[rnd.nextInt(0, from.length)]);
                    }
                }
            }

            currentClass = currentClass.getSuperclass();
        }

        return instance;
    }
    private T processBooleanFields(T instance) throws InvalidTypeForAnnotationException, InvalidGeneratorException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        Class currentClass = this._type;

        while(currentClass.getSuperclass() != null){
            for(Field field: currentClass.getDeclaredFields()){
                BooleanGenerator generator = _defaultBooleanGenerator;

                field.setAccessible(true);

                if(!field.isAnnotationPresent(RandomBoolean.class)){
                    continue;
                }

                if((!isTypeOrArrayOfType(field, Boolean.class)) && !isType(field, boolean.class)){
                    throw new InvalidTypeForAnnotationException(field.getType(), RandomBoolean.class);
                } else {
                    RandomBoolean annotation = field.getAnnotation(RandomBoolean.class);

                    if(!annotation.generator().getCanonicalName().equals(BooleanGenerator.class.getCanonicalName())){
                        generator = (BooleanGenerator) createGeneratorForType(annotation.generator(), BooleanGenerator.class);
                    }

                    int count = annotation.count() >= 0 ? annotation.count() : rnd.nextInt(annotation.minCount(), annotation.maxCount()+1);

                    if (isArray(field)){
                        field.set(instance, generator.generate(count));
                    } else {
                        field.set(instance, generator.generate());
                    }
                }
            }

            currentClass = currentClass.getSuperclass();
        }

        return instance;
    }
    private T processObjectFields(T instance) throws InvalidTypeForAnnotationException, InvalidGeneratorException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        Class currentClass = this._type;

        while(currentClass.getSuperclass() != null){
            for(Field field: currentClass.getDeclaredFields()){
                field.setAccessible(true);

                if(!field.isAnnotationPresent(RandomObject.class)){
                    continue;
                }

                RandomObject annotation = field.getAnnotation(RandomObject.class);

                int count = annotation.count() >= 0 ? annotation.count() : rnd.nextInt(annotation.minCount(), annotation.maxCount()+1);

                if (isArray(field)){
                    RandomGenerator generator = RandomGenerator
                            .forType(getListType(field))
                            .withDefaultBooleanGenerator(_defaultBooleanGenerator.getClass())
                            .withDefaultDoubleGenerator(_defaultDoubleGenerator.getClass())
                            .withDefaultFloatGenerator(_defaultFloatGenerator.getClass())
                            .withDefaultIntegerGenerator(_defaultIntegerGenerator.getClass())
                            .withDefaultLongGenerator(_defaultLongGenerator.getClass())
                            .withDefaultStringGenerator(_defaultStringGenerator.getClass());

                    field.set(instance, generator.generate(count));
                } else {
                    RandomGenerator generator = RandomGenerator
                            .forType(field.getType())
                            .withDefaultBooleanGenerator(_defaultBooleanGenerator.getClass())
                            .withDefaultDoubleGenerator(_defaultDoubleGenerator.getClass())
                            .withDefaultFloatGenerator(_defaultFloatGenerator.getClass())
                            .withDefaultIntegerGenerator(_defaultIntegerGenerator.getClass())
                            .withDefaultLongGenerator(_defaultLongGenerator.getClass())
                            .withDefaultStringGenerator(_defaultStringGenerator.getClass());

                    field.set(instance, generator.generate());
                }
            }

            currentClass = currentClass.getSuperclass();
        }


        return instance;
    }

    private Object createGeneratorForType(Class<?> implementation, Class<?> type) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        if(!type.isAssignableFrom(implementation)){
            throw new InvalidGeneratorException(type, implementation);
        }

        return implementation.newInstance();
    }

    public RandomGenerator withDefaultStringGenerator(Class c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        this._defaultStringGenerator = (StringGenerator) createGeneratorForType(c, StringGenerator.class);
        return this;
    }
    public RandomGenerator withDefaultDoubleGenerator(Class c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        this._defaultDoubleGenerator = (DoubleGenerator) createGeneratorForType(c, DoubleGenerator.class);
        return this;
    }
    public RandomGenerator withDefaultIntegerGenerator(Class c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        this._defaultIntegerGenerator = (IntegerGenerator) createGeneratorForType(c, IntegerGenerator.class);
        return this;
    }
    public RandomGenerator withDefaultFloatGenerator(Class c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        this._defaultFloatGenerator = (FloatGenerator) createGeneratorForType(c, FloatGenerator.class);
        return this;
    }
    public RandomGenerator withDefaultLongGenerator(Class c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        this._defaultLongGenerator = (LongGenerator) createGeneratorForType(c, LongGenerator.class);
        return this;
    }
    public RandomGenerator withDefaultBooleanGenerator(Class c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        this._defaultBooleanGenerator = (BooleanGenerator) createGeneratorForType(c, BooleanGenerator.class);
        return this;
    }
    public static RandomGenerator<?> forType(Class<?> type){
        return new RandomGenerator<>(type);
    }

    private T random(){
        T instance = null;
        try {
            instance = _type.newInstance();

            instance = this.processAnnotations(instance);

        } catch (InstantiationException | IllegalAccessException | InvalidTypeForAnnotationException | InvalidGeneratorException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return instance;
    }

    public T generate(){
        return this.random();
    }
    public List<T> generate(int count){
        return Stream.generate(this::generate).limit(count).collect(Collectors.toList());
    }
}