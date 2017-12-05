package es.usc.citius.utils.generator.generators;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface DoubleGenerator {
    double generate(double min, double max);
    default List<Double> generate(double min, double max, int count){
        return Stream.generate(() -> generate(min, max)).limit(count).collect(Collectors.toList());
    }
    default double[] generateArray(double min, double max, int count){
        double[] array = new double[count];
        for(int i=0; i<count; i++)
            array[i] =  generate(min, max);

        return array;
    }
}
