package es.usc.citius.utils.generator.generators;

import java.util.List;

public interface StringGenerator {
    String generate(int length, int minLength, int maxLength);
    List<String> generate(int length, int minLength, int maxLength, int count);
}
