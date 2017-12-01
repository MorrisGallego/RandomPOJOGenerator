package es.usc.citius.utils.generator.generators;

import java.util.List;

public interface DoubleGenerator {
    double generate(double min, double max);
    List<Double> generate(double min, double max, int count);
    double[] generateArray(double min, double max, int count);
}
