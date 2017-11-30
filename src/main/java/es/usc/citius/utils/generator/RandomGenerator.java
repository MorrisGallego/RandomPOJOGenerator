package es.usc.citius.utils.generator;

import es.usc.citius.utils.generator.annotations.*;
import es.usc.citius.utils.generator.exceptions.InvalidTypeForAnnotationException;
import es.usc.citius.utils.generator.generators.*;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator<T> {
    private Class<T> _type;

    private RandomGenerator(Class<T> type){
        this._type = type;
    }

    private boolean isTypeOrArrayOfType(Field field, Class type){
        return isType(field, type) || isArrayOfType(field, type);
    }
    private boolean isArrayOfType(Field field, Class type){
        return isArray(field) && getListType(field).isAssignableFrom(type);
    }
    private Class getListType(Field field){
        return ((Class)((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0]);
    }
    private boolean isType(Field field, Class type){
        return type.isAssignableFrom(field.getType());
    }
    private boolean isArray(Field field){
        return isType(field, List.class);
    }

    private T processAnnotations(T instance) throws InvalidTypeForAnnotationException {
        instance = processStringFields(instance);
        instance = processDoubleFields(instance);
        instance = processIntFields(instance);
        instance = processFloatFields(instance);
        instance = processLongFields(instance);
        instance = processBooleanFields(instance);
        instance = processObjectFields(instance);

        return instance;
    }
    private T processStringFields(T instance) throws InvalidTypeForAnnotationException {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        try {
            Class currentClass = this._type;

            while(currentClass.getSuperclass() != null){
                for(Field field: currentClass.getDeclaredFields()){
                    field.setAccessible(true);

                    if(!field.isAnnotationPresent(RandomString.class)){
                        continue;
                    }

                    if(!isTypeOrArrayOfType(field, String.class)){
                        throw new InvalidTypeForAnnotationException(field.getType(), RandomString.class);
                    } else {
                        RandomString annotation = field.getAnnotation(RandomString.class);

                        int count = annotation.count() >= 0 ? annotation.count() : rnd.nextInt(annotation.minCount(), annotation.maxCount()+1);
                        String [] from = annotation.from();
                        int length = annotation.length();
                        int maxLength = annotation.maxLength();
                        int minLength = annotation.minLength();

                        if(annotation.from().length == 0 && !isArray(field)) {
                            field.set(instance, StringGenerator.generate(length, minLength, maxLength));
                        } else if (annotation.from().length == 0 && isArray(field)){
                            field.set(instance, StringGenerator.generate(length, minLength, maxLength, count));
                        } else if (isArray(field)){
                            List <String> values = new ArrayList<>();

                            for(int i = 0; i<count; i++)
                                values.add(from[rnd.nextInt(0, from.length)]);

                            field.set(instance, values);
                        } else {
                            field.set(instance, from[rnd.nextInt(0, from.length)]);
                        }
                    }
                }

                currentClass = currentClass.getSuperclass();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return instance;
    }
    private T processDoubleFields(T instance) throws InvalidTypeForAnnotationException {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        try {
            Class currentClass = this._type;

            while(currentClass.getSuperclass() != null){
                for(Field field: currentClass.getDeclaredFields()){
                    field.setAccessible(true);

                    if(!field.isAnnotationPresent(RandomDouble.class)){
                        continue;
                    }

                    if(!isTypeOrArrayOfType(field, Double.class) && !isType(field, double.class)){
                        throw new InvalidTypeForAnnotationException(field.getType(), RandomDouble.class);
                    } else {
                        RandomDouble annotation = field.getAnnotation(RandomDouble.class);

                        int count = annotation.count() >= 0 ? annotation.count() : rnd.nextInt(annotation.minCount(), annotation.maxCount()+1);
                        double [] from = annotation.from();
                        double max = annotation.max();
                        double min = annotation.min();

                        if(annotation.from().length == 0 && !isArray(field)) {
                            field.set(instance, DoubleGenerator.generate(min, max));
                        } else if (annotation.from().length == 0 && isArray(field)){
                            field.set(instance, DoubleGenerator.generate(min, max, count));
                        } else if (isArray(field)){
                            List <Double> values = new ArrayList<>();

                            for(int i = 0; i<count; i++)
                                values.add(from[rnd.nextInt(0, from.length)]);

                            field.set(instance, values);
                        } else {
                            field.set(instance, from[rnd.nextInt(0, from.length)]);
                        }
                    }
                }

                currentClass = currentClass.getSuperclass();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return instance;
    }
    private T processIntFields(T instance) throws InvalidTypeForAnnotationException {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        try {
            Class currentClass = this._type;

            while(currentClass.getSuperclass() != null){
                for(Field field: currentClass.getDeclaredFields()){
                    field.setAccessible(true);

                    if(!field.isAnnotationPresent(RandomInt.class)){
                        continue;
                    }

                    if(!isTypeOrArrayOfType(field, Integer.class) && !isType(field, int.class)){
                        throw new InvalidTypeForAnnotationException(field.getType(), RandomInt.class);
                    } else {
                        RandomInt annotation = field.getAnnotation(RandomInt.class);

                        int count = annotation.count() >= 0 ? annotation.count() : rnd.nextInt(annotation.minCount(), annotation.maxCount()+1);
                        int [] from = annotation.from();
                        int max = annotation.max();
                        int min = annotation.min();

                        if(annotation.from().length == 0 && !isArray(field)) {
                            field.set(instance, IntegerGenerator.generate(min, max));
                        } else if (annotation.from().length == 0 && isArray(field)){
                            field.set(instance, IntegerGenerator.generate(min, max, count));
                        } else if (isArray(field)){
                            List <Integer> values = new ArrayList<>();

                            for(int i = 0; i<count; i++)
                                values.add(from[rnd.nextInt(0, from.length)]);

                            field.set(instance, values);
                        } else {
                            field.set(instance, from[rnd.nextInt(0, from.length)]);
                        }
                    }
                }

                currentClass = currentClass.getSuperclass();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return instance;
    }
    private T processFloatFields(T instance) throws InvalidTypeForAnnotationException {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        try {
            Class currentClass = this._type;

            while(currentClass.getSuperclass() != null){
                for(Field field: currentClass.getDeclaredFields()){
                    field.setAccessible(true);

                    if(!field.isAnnotationPresent(RandomFloat.class)){
                        continue;
                    }

                    if(!isTypeOrArrayOfType(field, Float.class) && !isType(field, float.class)){
                        throw new InvalidTypeForAnnotationException(field.getType(), RandomFloat.class);
                    } else {
                        RandomFloat annotation = field.getAnnotation(RandomFloat.class);

                        int count = annotation.count() >= 0 ? annotation.count() : rnd.nextInt(annotation.minCount(), annotation.maxCount()+1);
                        float [] from = annotation.from();
                        float max = annotation.max();
                        float min = annotation.min();

                        if(annotation.from().length == 0 && !isArray(field)) {
                            field.set(instance, FloatGenerator.generate(min, max));
                        } else if (annotation.from().length == 0 && isArray(field)){
                            field.set(instance, FloatGenerator.generate(min, max, count));
                        } else if (isArray(field)){
                            List <Float> values = new ArrayList<>();

                            for(int i = 0; i<count; i++)
                                values.add(from[rnd.nextInt(0, from.length)]);

                            field.set(instance, values);
                        } else {
                            field.set(instance, from[rnd.nextInt(0, from.length)]);
                        }
                    }
                }

                currentClass = currentClass.getSuperclass();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return instance;
    }
    private T processLongFields(T instance) throws InvalidTypeForAnnotationException {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        try {
            Class currentClass = this._type;

            while(currentClass.getSuperclass() != null){
                for(Field field: currentClass.getDeclaredFields()){
                    field.setAccessible(true);

                    if(!field.isAnnotationPresent(RandomLong.class)){
                        continue;
                    }

                    if((!isTypeOrArrayOfType(field, Long.class)) && !isType(field, long.class)){
                        throw new InvalidTypeForAnnotationException(field.getType(), RandomLong.class);
                    } else {
                        RandomLong annotation = field.getAnnotation(RandomLong.class);

                        int count = annotation.count() >= 0 ? annotation.count() : rnd.nextInt(annotation.minCount(), annotation.maxCount()+1);
                        long [] from = annotation.from();
                        long max = annotation.max();
                        long min = annotation.min();

                        if(annotation.from().length == 0 && !isArray(field)) {
                            field.set(instance, LongGenerator.generate(min, max));
                        } else if (annotation.from().length == 0 && isArray(field)){
                            field.set(instance, LongGenerator.generate(min, max, count));
                        } else if (isArray(field)){
                            List <Long> values = new ArrayList<>();

                            for(int i = 0; i<count; i++)
                                values.add(from[rnd.nextInt(0, from.length)]);

                            field.set(instance, values);
                        } else {
                            field.set(instance, from[rnd.nextInt(0, from.length)]);
                        }
                    }
                }

                currentClass = currentClass.getSuperclass();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return instance;
    }
    private T processBooleanFields(T instance) throws InvalidTypeForAnnotationException {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        try {
            Class currentClass = this._type;

            while(currentClass.getSuperclass() != null){
                for(Field field: currentClass.getDeclaredFields()){
                    field.setAccessible(true);

                    if(!field.isAnnotationPresent(RandomBoolean.class)){
                        continue;
                    }

                    if((!isTypeOrArrayOfType(field, Boolean.class)) && !isType(field, boolean.class)){
                        throw new InvalidTypeForAnnotationException(field.getType(), RandomBoolean.class);
                    } else {
                        RandomBoolean annotation = field.getAnnotation(RandomBoolean.class);

                        int count = annotation.count() >= 0 ? annotation.count() : rnd.nextInt(annotation.minCount(), annotation.maxCount()+1);

                        if (isArray(field)){
                            field.set(instance, BooleanGenerator.generate(count));
                        } else {
                            field.set(instance, BooleanGenerator.generate());
                        }
                    }
                }

                currentClass = currentClass.getSuperclass();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return instance;
    }
    private T processObjectFields(T instance) throws InvalidTypeForAnnotationException{
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        try {
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
                        field.set(instance, RandomGenerator.forType(getListType(field)).generate(count));
                    } else {
                        field.set(instance, RandomGenerator.forType(field.getType()).generateOne());
                    }
                }

                currentClass = currentClass.getSuperclass();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return instance;
    }

    private T random(){
        T instance = null;
        try {
            instance = _type.newInstance();

            instance = this.processAnnotations(instance);

        } catch (InstantiationException | IllegalAccessException | InvalidTypeForAnnotationException e) {
            e.printStackTrace();
        }

        return instance;
    }

    public List<T> generate(int count){
        List<T> objects = new ArrayList<>();

        for(int i=0; i<count; i++){
            objects.add(this.random());
        }

        return objects;
    }
    public T generateOne(){
        return this.generate(1).get(0);
    }
    public static RandomGenerator forType(Class type){
        return new RandomGenerator(type);
    }
}