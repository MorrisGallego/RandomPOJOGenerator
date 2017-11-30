package es.usc.citius.utils.generator.generators;

import java.util.List;

public interface FloatGenerator {
    float generate(float min, float max);
    List<Float> generate(float min, float max, int count);
}
