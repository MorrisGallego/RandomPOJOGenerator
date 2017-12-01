package es.usc.citius.utils.generator.generators;

import java.util.List;

public interface ShortGenerator {
    short generate(short min, short max);
    List<Short> generate(short min, short max, int count);
    short[] generateArray(short min, short max, int count);
}
