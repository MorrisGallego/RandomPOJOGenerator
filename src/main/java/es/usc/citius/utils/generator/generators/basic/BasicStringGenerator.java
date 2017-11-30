package es.usc.citius.utils.generator.generators.basic;

import es.usc.citius.utils.generator.generators.StringGenerator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicStringGenerator implements StringGenerator{
    public String generate(int length, int minLength, int maxLength){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        String characters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "1234567890";
        String symbols = "+-*_{}[]!|@·#$~%&¬/()=?¿";

        char[] dict = characters.concat(characters.toUpperCase()).concat(numbers).concat(symbols).toCharArray();

        StringBuilder sb = new StringBuilder();

        if(length < 0)
            length = rnd.nextInt(minLength, maxLength);

        length = Math.max(length, 0);

        for (int i = 0; i < length; i++)
            sb.append(dict[rnd.nextInt(0, dict.length)]);

        return sb.toString();
    }
    public List<String> generate(int length, int minLength, int maxLength, int count){
        return Stream.generate(() -> generate(length, minLength, maxLength)).limit(count).collect(Collectors.toList());
    }
}
