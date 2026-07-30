package expression.exceptions;

public class StringSource implements CharSource {
    private final String input;
    private int position;

    public StringSource(final String input) {
        this.input = input;
    }

    @Override
    public boolean hasNext() {
        return position < input.length();
    }

    @Override
    public char next() {
        return input.charAt(position++);
    }

    @Override
    public boolean check(final String expected) {
        if (position > 0 && position <= input.length() - expected.length()) {
            String str = input.substring(position - 1, position + expected.length() - 1);
            return str.equals(expected);
        }
        return false;
    }

    @Override
    public ParsingException error(String massage) {
        return new ParsingException(position + ": " + massage);
    }

    @Override
    public int getPos() {
        return position;
    }

    @Override
    public String getInput() {
        return input;
    }
}
