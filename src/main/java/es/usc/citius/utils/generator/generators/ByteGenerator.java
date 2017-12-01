package es.usc.citius.utils.generator.generators;

import java.util.List;

public interface ByteGenerator {
    byte generate(byte min, byte max);
    List<Byte> generate(byte min, byte max, int count);
    byte[] generateArray(byte min, byte max, int count);
}
