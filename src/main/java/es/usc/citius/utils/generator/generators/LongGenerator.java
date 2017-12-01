package es.usc.citius.utils.generator.generators;

import java.util.List;

public interface LongGenerator {
    long generate(long min, long max);
    List<Long> generate(long min, long max, int count);
    long[] generateArray(long min, long max, int count);
}
