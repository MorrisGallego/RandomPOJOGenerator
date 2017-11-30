package es.usc.citius.utils.generator.exceptions;

public class InvalidGeneratorException extends Exception{
    public InvalidGeneratorException(){
        super();
    }

    public InvalidGeneratorException(Class type, Class impl){
        super(String.format("Class %s must implement interface %s", impl.getCanonicalName(), type.getCanonicalName()));
    }

    public InvalidGeneratorException(String s){
        super(s);
    }
}