package es.usc.citius.utils.generator.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StringGenerator {
    public static String generate(int length, int minLength, int maxLength){
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
    public static List<String> generate(int length, int minLength, int maxLength, int count){
        List<String> values = new ArrayList<>();

        for(int i = 0; i<count; i++){
            values.add(generate(length, minLength, maxLength));
        }

        return values;
    }
}
