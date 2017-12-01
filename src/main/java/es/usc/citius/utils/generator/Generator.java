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

public class Generator<T> {
    private Class<T> _type;

    private BooleanGenerator _defaultBooleanGenerator = new BasicBooleanGenerator();
    private DoubleGenerator _defaultDoubleGenerator = new BasicDoubleGenerator();
    private FloatGenerator _defaultFloatGenerator = new BasicFloatGenerator();
    private IntegerGenerator _defaultIntegerGenerator = new BasicIntegerGenerator();
    private LongGenerator _defaultLongGenerator = new BasicLongGenerator();
    private StringGenerator _defaultStringGenerator = new BasicStringGenerator();
    private ShortGenerator _defaultShortGenerator = new BasicShortGenerator();
    private CharacterGenerator _defaultCharacterGenerator = new BasicCharacterGenerator();
    private ByteGenerator _defaultByteGenerator = new BasicByteGenerator();

    Generator<T> setType(Class<T> type){
        this._type = type;
        return this;
    }

    private boolean isTypeOrArrayOfTypeOrListOfType(Field field, Class type) throws ClassNotFoundException {
        return isType(field, type) || isListOfType(field, type) || isArrayOfType(field, type);
    }
    private boolean isListOfType(Field field, Class type) throws ClassNotFoundException {
        return isList(field) && getListType(field).isAssignableFrom(type);
    }
    private boolean isType(Field field, Class<?> type){
        return type.isAssignableFrom(field.getType());
    }
    private boolean isList(Field field){
        return isType(field, List.class);
    }
    private boolean isArray(Field field){
        return field.getType().isArray();
    }
    private boolean isArrayOfType(Field field, Class<?> type){
        return field.getType().isArray() && type.isAssignableFrom(field.getType().getComponentType());
    }
    private Class<?> getListType(Field field) throws ClassNotFoundException {
        return Class.forName(((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0].getTypeName());
    }
    private Class<?> getArrayType(Field field){
        return field.getType().getComponentType();
    }

    private T processAnnotations(T instance) throws InvalidTypeForAnnotationException, InvalidGeneratorException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        instance = processStringFields(instance);
        instance = processDoubleFields(instance);
        instance = processIntegerFields(instance);
        instance = processFloatFields(instance);
        instance = processLongFields(instance);
        instance = processShortFields(instance);
        instance = processBooleanFields(instance);
        instance = processObjectFields(instance);
        instance = processCharacterFields(instance);
        instance = processByteFields(instance);

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

                if(!isTypeOrArrayOfTypeOrListOfType(field, String.class)){
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

                    if(annotation.from().length == 0 && !isList(field) && !isArray(field)) {
                        field.set(instance, generator.generate(length, minLength, maxLength));
                    } else if (annotation.from().length == 0 && isList(field)){
                        field.set(instance, generator.generate(length, minLength, maxLength, count));
                    } else if (annotation.from().length == 0 && isArray(field)){
                        field.set(instance, generator.generate(length, minLength, maxLength, count).toArray());
                    } else if (isList(field)){
                        List <String> values = Stream.generate(() -> from[rnd.nextInt(0, from.length)]).limit(count).collect(Collectors.toList());
                        field.set(instance, values);
                    } else if (isArray(field)){
                        List <String> values = Stream.generate(() -> from[rnd.nextInt(0, from.length)]).limit(count).collect(Collectors.toList());
                        field.set(instance, values.toArray());
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

                if(!isTypeOrArrayOfTypeOrListOfType(field, Double.class) && !isTypeOrArrayOfTypeOrListOfType(field, double.class)){
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

                    if(annotation.from().length == 0 && !isList(field) && !isArray(field)) {
                        field.set(instance, generator.generate(min, max));
                    } else if (annotation.from().length == 0 && isList(field)){
                        field.set(instance, generator.generate(min, max, count));
                    } else if (annotation.from().length == 0 && isArray(field)){
                        field.set(instance, generator.generateArray(min, max, count));
                    } else if (isList(field)){
                        List <Double> values = Stream.generate(() -> from[rnd.nextInt(0, from.length)]).limit(count).collect(Collectors.toList());
                        field.set(instance, values);
                    } else if (isArray(field)){
                        double[] values = new double[count];

                        for(int i=0; i<count; i++)
                            values[i] = from[rnd.nextInt(0, from.length)];

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

                if(!field.isAnnotationPresent(RandomInteger.class)){
                    continue;
                }

                if(!isTypeOrArrayOfTypeOrListOfType(field, Integer.class) && !isTypeOrArrayOfTypeOrListOfType(field, int.class)){
                    throw new InvalidTypeForAnnotationException(field.getType(), RandomInteger.class);
                } else {
                    RandomInteger annotation = field.getAnnotation(RandomInteger.class);

                    if(!annotation.generator().getCanonicalName().equals(IntegerGenerator.class.getCanonicalName())){
                        generator = (IntegerGenerator) createGeneratorForType(annotation.generator(), IntegerGenerator.class);
                    }

                    int count = annotation.count() >= 0 ? annotation.count() : rnd.nextInt(annotation.minCount(), annotation.maxCount()+1);
                    int [] from = annotation.from();
                    int max = annotation.max();
                    int min = annotation.min();

                    if(annotation.from().length == 0 && !isList(field) && !isArray(field)) {
                        field.set(instance, generator.generate(min, max));
                    } else if (annotation.from().length == 0 && isList(field)){
                        field.set(instance, generator.generate(min, max, count));
                    } else if (annotation.from().length == 0 && isArray(field)){
                        field.set(instance, generator.generateArray(min, max, count));
                    } else if (isList(field)){
                        List <Integer> values = Stream.generate(() -> from[rnd.nextInt(0, from.length)]).limit(count).collect(Collectors.toList());
                        field.set(instance, values);
                    } else if (isArray(field)){
                        int[] values = new int[count];

                        for(int i=0; i<count; i++)
                            values[i] = from[rnd.nextInt(0, from.length)];

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

                if(!isTypeOrArrayOfTypeOrListOfType(field, Float.class) && !isTypeOrArrayOfTypeOrListOfType(field, float.class)){
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

                    if(annotation.from().length == 0 && !isList(field) && !isArray(field)) {
                        field.set(instance, generator.generate(min, max));
                    } else if (annotation.from().length == 0 && isList(field)){
                        field.set(instance, generator.generate(min, max, count));
                    } else if (annotation.from().length == 0 && isArray(field)){
                        field.set(instance, generator.generateArray(min, max, count));
                    } else if (isList(field)){
                        List <Float> values = Stream.generate(() -> from[rnd.nextInt(0, from.length)]).limit(count).collect(Collectors.toList());
                        field.set(instance, values);
                    } else if (isArray(field)){
                        float[] values = new float[count];

                        for(int i=0; i<count; i++)
                            values[i] = from[rnd.nextInt(0, from.length)];

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

                if((!isTypeOrArrayOfTypeOrListOfType(field, Long.class)) && !isTypeOrArrayOfTypeOrListOfType(field, long.class)){
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

                    if(annotation.from().length == 0 && !isList(field) && !isArray(field)) {
                        field.set(instance, generator.generate(min, max));
                    } else if (annotation.from().length == 0 && isList(field)){
                        field.set(instance, generator.generate(min, max, count));
                    } else if (annotation.from().length == 0 && isArray(field)){
                        field.set(instance, generator.generateArray(min, max, count));
                    } else if (isList(field)){
                        List <Long> values = Stream.generate(() -> from[rnd.nextInt(0, from.length)]).limit(count).collect(Collectors.toList());
                        field.set(instance, values);
                    } else if (isArray(field)){
                        long[] values = new long[count];

                        for(int i=0; i<count; i++)
                            values[i] = from[rnd.nextInt(0, from.length)];

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
    private T processShortFields(T instance) throws InvalidTypeForAnnotationException, InvalidGeneratorException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        Class currentClass = this._type;

        while(currentClass.getSuperclass() != null){
            for(Field field: currentClass.getDeclaredFields()){
                ShortGenerator generator = _defaultShortGenerator;

                field.setAccessible(true);

                if(!field.isAnnotationPresent(RandomShort.class)){
                    continue;
                }

                if((!isTypeOrArrayOfTypeOrListOfType(field, Short.class)) && !isTypeOrArrayOfTypeOrListOfType(field, short.class)){
                    throw new InvalidTypeForAnnotationException(field.getType(), RandomShort.class);
                } else {
                    RandomShort annotation = field.getAnnotation(RandomShort.class);

                    if(!annotation.generator().getCanonicalName().equals(LongGenerator.class.getCanonicalName())){
                        generator = (ShortGenerator) createGeneratorForType(annotation.generator(), ShortGenerator.class);
                    }

                    int count = annotation.count() >= 0 ? annotation.count() : rnd.nextInt(annotation.minCount(), annotation.maxCount()+1);
                    short [] from = annotation.from();
                    short max = annotation.max();
                    short min = annotation.min();

                    if(annotation.from().length == 0 && !isList(field) && !isArray(field)) {
                        field.set(instance, generator.generate(min, max));
                    } else if (annotation.from().length == 0 && isList(field)){
                        field.set(instance, generator.generate(min, max, count));
                    } else if (annotation.from().length == 0 && isArray(field)){
                        field.set(instance, generator.generateArray(min, max, count));
                    } else if (isList(field)){
                        List <Short> values = Stream.generate(() -> from[rnd.nextInt(0, from.length)]).limit(count).collect(Collectors.toList());
                        field.set(instance, values);
                    } else if (isArray(field)){
                        short[] values = new short[count];

                        for(int i=0; i<count; i++)
                            values[i] = from[rnd.nextInt(0, from.length)];

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
    private T processByteFields(T instance) throws InvalidTypeForAnnotationException, InvalidGeneratorException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        Class currentClass = this._type;

        while(currentClass.getSuperclass() != null){
            for(Field field: currentClass.getDeclaredFields()){
                ByteGenerator generator = _defaultByteGenerator;

                field.setAccessible(true);

                if(!field.isAnnotationPresent(RandomByte.class)){
                    continue;
                }

                if((!isTypeOrArrayOfTypeOrListOfType(field, Byte.class)) && !isTypeOrArrayOfTypeOrListOfType(field, byte.class)){
                    throw new InvalidTypeForAnnotationException(field.getType(), RandomByte.class);
                } else {
                    RandomByte annotation = field.getAnnotation(RandomByte.class);

                    if(!annotation.generator().getCanonicalName().equals(LongGenerator.class.getCanonicalName())){
                        generator = (ByteGenerator) createGeneratorForType(annotation.generator(), ByteGenerator.class);
                    }

                    int count = annotation.count() >= 0 ? annotation.count() : rnd.nextInt(annotation.minCount(), annotation.maxCount()+1);
                    byte [] from = annotation.from();
                    byte max = annotation.max();
                    byte min = annotation.min();

                    if(annotation.from().length == 0 && !isList(field) && !isArray(field)) {
                        field.set(instance, generator.generate(min, max));
                    } else if (annotation.from().length == 0 && isList(field)){
                        field.set(instance, generator.generate(min, max, count));
                    } else if (annotation.from().length == 0 && isArray(field)){
                        field.set(instance, generator.generateArray(min, max, count));
                    } else if (isList(field)){
                        List <Byte> values = Stream.generate(() -> from[rnd.nextInt(0, from.length)]).limit(count).collect(Collectors.toList());
                        field.set(instance, values);
                    } else if (isArray(field)){
                        byte[] values = new byte[count];

                        for(int i=0; i<count; i++)
                            values[i] = from[rnd.nextInt(0, from.length)];

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
    private T processCharacterFields(T instance) throws InvalidTypeForAnnotationException, InvalidGeneratorException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        Class currentClass = this._type;

        while(currentClass.getSuperclass() != null){
            for(Field field: currentClass.getDeclaredFields()){
                CharacterGenerator generator = _defaultCharacterGenerator;

                field.setAccessible(true);

                if(!field.isAnnotationPresent(RandomCharacter.class)){
                    continue;
                }

                if((!isTypeOrArrayOfTypeOrListOfType(field, Character.class)) && !isTypeOrArrayOfTypeOrListOfType(field, char.class)){
                    throw new InvalidTypeForAnnotationException(field.getType(), RandomCharacter.class);
                } else {
                    RandomCharacter annotation = field.getAnnotation(RandomCharacter.class);

                    if(!annotation.generator().getCanonicalName().equals(LongGenerator.class.getCanonicalName())){
                        generator = (CharacterGenerator) createGeneratorForType(annotation.generator(), CharacterGenerator.class);
                    }

                    int count = annotation.count() >= 0 ? annotation.count() : rnd.nextInt(annotation.minCount(), annotation.maxCount()+1);
                    char [] from = annotation.from();

                    if(annotation.from().length == 0 && !isList(field) && !isArray(field)) {
                        field.set(instance, generator.generate());
                    } else if (annotation.from().length == 0 && isList(field)){
                        field.set(instance, generator.generate(count));
                    } else if (annotation.from().length == 0 && isArray(field)){
                        field.set(instance, generator.generateArray(count));
                    } else if (isList(field)){
                        List <Character> values = Stream.generate(() -> from[rnd.nextInt(0, from.length)]).limit(count).collect(Collectors.toList());
                        field.set(instance, values);
                    } else if (isArray(field)){
                        char[] values = new char[count];

                        for(int i=0; i<count; i++)
                            values[i] = from[rnd.nextInt(0, from.length)];

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

                if((!isTypeOrArrayOfTypeOrListOfType(field, Boolean.class)) && !isTypeOrArrayOfTypeOrListOfType(field, boolean.class)){
                    throw new InvalidTypeForAnnotationException(field.getType(), RandomBoolean.class);
                } else {
                    RandomBoolean annotation = field.getAnnotation(RandomBoolean.class);

                    if(!annotation.generator().getCanonicalName().equals(BooleanGenerator.class.getCanonicalName())){
                        generator = (BooleanGenerator) createGeneratorForType(annotation.generator(), BooleanGenerator.class);
                    }

                    int count = annotation.count() >= 0 ? annotation.count() : rnd.nextInt(annotation.minCount(), annotation.maxCount()+1);

                    if (isList(field)){
                        field.set(instance, generator.generate(count));
                    } else if (isArray(field)){
                        field.set(instance, generator.generateArray(count));
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

                Class type = field.getType();

                if (isList(field))
                    type = getListType(field);
                else if(isArray(field))
                    type = getArrayType(field);

                Generator generator = Generator
                        .forType(type)
                        .withDefaultBooleanGenerator(_defaultBooleanGenerator.getClass())
                        .withDefaultDoubleGenerator(_defaultDoubleGenerator.getClass())
                        .withDefaultFloatGenerator(_defaultFloatGenerator.getClass())
                        .withDefaultIntegerGenerator(_defaultIntegerGenerator.getClass())
                        .withDefaultLongGenerator(_defaultLongGenerator.getClass())
                        .withDefaultStringGenerator(_defaultStringGenerator.getClass())
                        .withDefaultShortGenerator(_defaultShortGenerator.getClass())
                        .withDefaultCharacterGenerator(_defaultCharacterGenerator.getClass())
                        .withDefaultByteGenerator(_defaultByteGenerator.getClass());

                if(isList(field))
                    field.set(instance, generator.generate(count));
                else if(isArray(field))
                    field.set(instance, generator.generate(count).toArray());
                else
                    field.set(instance, generator.generate());
            }

            currentClass = currentClass.getSuperclass();
        }


        return instance;
    }

    private static Object createGeneratorForType(Class<?> implementation, Class<?> type) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        if(!type.isAssignableFrom(implementation)){
            throw new InvalidGeneratorException(type, implementation);
        }

        return implementation.newInstance();
    }
    public Generator withDefaultStringGenerator(Class<?> c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        this._defaultStringGenerator = (StringGenerator) createGeneratorForType(c, StringGenerator.class);
        return this;
    }
    public Generator withDefaultDoubleGenerator(Class<?> c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        this._defaultDoubleGenerator = (DoubleGenerator) createGeneratorForType(c, DoubleGenerator.class);
        return this;
    }
    public Generator withDefaultIntegerGenerator(Class<?> c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        this._defaultIntegerGenerator = (IntegerGenerator) createGeneratorForType(c, IntegerGenerator.class);
        return this;
    }
    public Generator withDefaultFloatGenerator(Class<?> c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        this._defaultFloatGenerator = (FloatGenerator) createGeneratorForType(c, FloatGenerator.class);
        return this;
    }
    public Generator withDefaultLongGenerator(Class<?> c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        this._defaultLongGenerator = (LongGenerator) createGeneratorForType(c, LongGenerator.class);
        return this;
    }
    public Generator withDefaultBooleanGenerator(Class<?> c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        this._defaultBooleanGenerator = (BooleanGenerator) createGeneratorForType(c, BooleanGenerator.class);
        return this;
    }
    public Generator withDefaultShortGenerator(Class<?> c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        this._defaultShortGenerator = (ShortGenerator) createGeneratorForType(c, ShortGenerator.class);
        return this;
    }
    public Generator withDefaultCharacterGenerator(Class<?> c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        this._defaultCharacterGenerator = (CharacterGenerator) createGeneratorForType(c, CharacterGenerator.class);
        return this;
    }
    public Generator withDefaultByteGenerator(Class<?> c) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        this._defaultByteGenerator = (ByteGenerator) createGeneratorForType(c, ByteGenerator.class);
        return this;
    }

    public static Generator<?> forType(Class type) throws InvalidGeneratorException, IllegalAccessException, InstantiationException {
        return ((Generator<?>)createGeneratorForType(Generator.class, Generator.class)).setType(type);
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
        return this.stream().limit(count).collect(Collectors.toList());
    }
    public Stream<T> stream(){
        return Stream.generate(this::generate);
    }
}