package expression.parser;

import java.util.SplittableRandom;

public interface CharSource {
    boolean hasNext();
    char next();
    boolean check(final String expected);
    IllegalArgumentException error(String massage);
    int getPos();
    String getInput();
}
