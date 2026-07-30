package expression.exceptions;

public class BaseParser {
    private static final char END = '\0';
    private final CharSource source;
    private char ch = 0xffff;

    public BaseParser(final CharSource source) {
        this.source = source;
        take();
    }

    protected char getCh() {
        return ch;
    }

    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected boolean check(final char expected) {
        return ch == expected;
    }

    protected boolean take(final char expected) {
        if (check(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected boolean check(final String expected) {
        return source.check(expected);
    }

    protected boolean take(final String expected) {
        if (check(expected)) {
            for (int i = 0; i < expected.length(); i++) {
                take();
            }
            return true;
        }
        return false;
    }

    protected void expect(final char expected, final ParsingException e) throws ParsingException {
        if (!take(expected)) {
            throw e;
            //throw error("At posExpected '" + expected + "', found '" + ch + "'");
        }
    }

    protected void expect(final String value, final  ParsingException e) throws ParsingException {
        for (final char c : value.toCharArray()) {
            expect(c, e);
        }
    }

    protected int getPos() {
        return source.getPos();
    }

    protected boolean eof() {
        return take(END);
    }

    protected ParsingException error(final String message) {
        return source.error(message);
    }

    protected String getSource(){
        return source.getInput();
    }
    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }
}