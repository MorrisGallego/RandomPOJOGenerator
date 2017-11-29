package es.usc.citius.utils.generator;

import es.usc.citius.utils.generator.annotations.AllowedValues;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class POJORandomGenerator<T> implements RandomGenerator{
    private Class<T> _type;

    public POJORandomGenerator(Class<T> type){
        this._type = type;
    }

    public T random(){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        T instance = null;
        try {
            instance = _type.newInstance();

            Class currentClass = this._type;

            while(currentClass.getSuperclass() != null){
                for(Field field: currentClass.getDeclaredFields()){
                    field.setAccessible(true);

                    AllowedValues annotation = field.getAnnotation(AllowedValues.class);

                    if(annotation != null){
                        if(annotation.value().equals(GenerationStrategy.GENERATE)){
                            if(List.class.isAssignableFrom(field.getType())){
                                int count = annotation.length() > 0 ? annotation.length() : rnd.nextInt(annotation.minLength(), annotation.maxLength()+1);

                                List values = new ArrayList();
                                Class type = ((Class)((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0]);

                                RandomGenerator generator = new POJORandomGenerator(type);

                                for(int i=0; i<count; i++)
                                    values.add(generator.random());

                                field.set(instance, values);
                            }

                            else if(field.getType().isAssignableFrom(String.class))
                                field.set(instance, UUID.randomUUID().toString());

                            else if(field.getType().isAssignableFrom(Integer.class))
                                field.set(instance, rnd.nextInt(new Double(annotation.min()).intValue(), new Double(annotation.max()).intValue() + 1));

                            else if(field.getType().isAssignableFrom(Long.class))
                                field.set(instance, rnd.nextLong(new Double(annotation.min()).longValue(), new Double(annotation.max()).longValue() + 1));

                            else if(field.getType().isAssignableFrom(Double.class))
                                field.set(instance, rnd.nextDouble(annotation.min(), annotation.max() + 1));

                            else if(field.getType().isAssignableFrom(Float.class))
                                field.set(instance, new Double(rnd.nextDouble(annotation.min(), annotation.max() + 1)).floatValue());

                            else
                                field.set(instance, new POJORandomGenerator<>(field.getType()).random());
                        }
                        else if(field.getType().isAssignableFrom(String.class)){
                            String [] allowedValues = annotation.strings();
                            field.set(instance, allowedValues[rnd.nextInt(allowedValues.length)]);
                        }
                        else if(field.getType().isAssignableFrom(Integer.class)){
                            int [] allowedValues = annotation.ints();
                            field.set(instance, allowedValues[rnd.nextInt(allowedValues.length)]);
                        }
                        else if(field.getType().isAssignableFrom(Long.class)){
                            long [] allowedValues = annotation.longs();
                            field.set(instance, allowedValues[rnd.nextInt(allowedValues.length)]);
                        }
                        else if(field.getType().isAssignableFrom(Double.class)) {
                            double [] allowedValues = annotation.doubles();
                            field.set(instance, allowedValues[rnd.nextInt(allowedValues.length)]);
                        }
                        else if(field.getType().isAssignableFrom(Float.class)) {
                            float [] allowedValues = annotation.floats();
                            field.set(instance, allowedValues[rnd.nextInt(allowedValues.length)]);
                        }
                    }

                    field.setAccessible(false);
                }

                currentClass = currentClass.getSuperclass();
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return instance;
    }
}