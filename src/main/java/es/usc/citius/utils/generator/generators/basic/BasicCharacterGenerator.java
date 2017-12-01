package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.CharacterGenerator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicCharacterGenerator implements CharacterGenerator {
    public char generate(){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        String characters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "1234567890";
        String symbols = "+-*_{}[]!|@·#$~%&¬/()=?¿";

        char[] dict = characters.concat(characters.toUpperCase()).concat(numbers).concat(symbols).toCharArray();

        return dict[rnd.nextInt(0, dict.length)];
    }

    public List<Character> generate(int count){
        return Stream.generate(this::generate).limit(count).collect(Collectors.toList());
    }

    public char[] generateArray(int count){
        char[] array = new char[count];
        for(int i=0; i<count; i++)
            array[i] =  generate();

        return array;
    }
}
