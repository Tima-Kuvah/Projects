package expression.exceptions;

public interface CharSource {
    boolean hasNext();
    char next();
    boolean check(final String expected);
    ParsingException error(String massage);
    int getPos();
    String getInput();
}
