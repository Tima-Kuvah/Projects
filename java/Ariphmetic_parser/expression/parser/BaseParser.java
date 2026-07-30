package expression.parser;

import javax.xml.transform.Source;

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

    protected void expect(final char expected) {
        if (!take(expected)) {
            throw error("Expected '" + expected + "', found '" + ch + "'");
        }
    }

    protected void expect(final String value) {
        for (final char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected int getPos() {
        return source.getPos();
    }

    protected boolean eof() {
        return take(END);
    }

    protected String getSource(){
        return source.getInput();
    }


    protected IllegalArgumentException error(final String message) {
        return source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }
}