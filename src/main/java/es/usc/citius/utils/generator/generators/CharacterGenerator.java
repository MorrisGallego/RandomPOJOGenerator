package es.usc.citius.utils.generator.generators;

import java.util.List;

public interface CharacterGenerator {
    char generate();
    List<Character> generate(int count);
    char[] generateArray(int count);
}
