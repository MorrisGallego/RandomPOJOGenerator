package es.usc.citius.utils.generator.exceptions;

public class InvalidTypeForAnnotationException extends Exception{
    public InvalidTypeForAnnotationException(){
        super();
    }

    public InvalidTypeForAnnotationException(Class c, Class a){
        super(String.format("Annotation %s not applicable to field of type %s", a.getCanonicalName(), c.getCanonicalName()));
    }

    public InvalidTypeForAnnotationException(String s){
        super(s);
    }
}
