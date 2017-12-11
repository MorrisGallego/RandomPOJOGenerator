package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.CharacterGenerator;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;

public class BasicCharacterGenerator implements CharacterGenerator {
    public char generate(Field field){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        String characters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "1234567890";
        String symbols = "+-*_{}[]!|@·#$~%&¬/()=?¿";

        char[] dict = characters.concat(characters.toUpperCase()).concat(numbers).concat(symbols).toCharArray();

        return dict[rnd.nextInt(0, dict.length)];
    }
}
