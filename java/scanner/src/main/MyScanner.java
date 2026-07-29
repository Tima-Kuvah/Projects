import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

public class MyScanner implements Closeable {
    private final char[] curData = new char[DEFAULT_BUFFER_SIZE];
    private int curLength;
    private int curIndex;
    private final String lineSeparator = System.lineSeparator();
    private final BufferedReader reader;
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    private StringBuilder partOfLineSeparator = new StringBuilder();

    private void read() throws IOException {
        curLength = reader.read(curData);
        curIndex = Math.min(curLength, 0);

    }

    public MyScanner(final Path path, final Charset charset) throws IOException {
        if (charset == null) {
            throw new NullPointerException("Charset must not be null");
        }
        if (path == null) {
            throw new NullPointerException("Path must not be null");
        }
        reader = Files.newBufferedReader(path, charset);
        read();
    }

    public MyScanner(final InputStream in, final Charset charset) throws IOException {
        if (in == null ) {
            throw new NullPointerException("Stream must be not null");
        }
        if (charset == null) {
            throw new NullPointerException("Charset must not be null");
        }

        reader = new BufferedReader(
                new InputStreamReader(in, charset),
                DEFAULT_BUFFER_SIZE);
        read();
    }

    public MyScanner(final String data) throws IOException {
        if (data == null) {
            throw new NullPointerException("Data must be not null");
        }
        this.reader = new BufferedReader(new StringReader(data),
                DEFAULT_BUFFER_SIZE);
        read();
    }

    private boolean twiceCheckChar() throws IOException {
        if (checkEndBuffer()) {
            read();
            if (isEOF()) {
                return true;
            }
        }
        return false;
    }

    private Character curChar() {
        return curData[curIndex];
    }

    private void nextChar() throws IOException {
        if (!twiceCheckChar()) {
            curIndex++;
            twiceCheckChar();
        }
    }

    private boolean hasNext(final Predicate<Character> begin, final boolean inOneLine) throws IOException {
        if (!partOfLineSeparator.isEmpty()) {
            for (int i = 0; i < partOfLineSeparator.length(); i++) {
                if (begin.test(partOfLineSeparator.charAt(i))) {
                    partOfLineSeparator.delete(0, i);
                    return true;
                }
            }
            partOfLineSeparator.setLength(0);
        }
        if (!inOneLine) {
            while (!checkEndBuffer() && !begin.test(curChar())) {
                nextChar();
            }
        } else {
            while (!checkEndBuffer() && !begin.test(curChar())) {
                if (isNextPartLSWithStep()) {
                    return false;
                } else if (partOfLineSeparator.isEmpty()) {
                    nextChar();
                } else {
                    if (begin.test(partOfLineSeparator.charAt(0))) {
                        return true;
                    } else {
                        nextChar();
                    }
                }
            }
        }
        return !isEOF();
    }

    public boolean hasNext(final Predicate<Character> begin) throws IOException {
        return hasNext(begin, false);
    }

    public boolean hasNextInLine(final Predicate<Character> begin) throws IOException {
        return hasNext(begin, true);
    }


    public String next(final Predicate<Character> middle) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (!partOfLineSeparator.isEmpty()) {
            sb.append(partOfLineSeparator.charAt(0));
            for (int i = 1; i < partOfLineSeparator.length(); i++) {
                if (middle.test(partOfLineSeparator.charAt(i))) {
                    sb.append(partOfLineSeparator.charAt(i));
                } else {
                    partOfLineSeparator.delete(0, i);
                    return sb.toString();
                }
            }
        }
        do {
            sb.append(curChar());
            nextChar();
            if (twiceCheckChar()) {
                return sb.toString();
            }
        } while (middle.test(curChar()));
        return sb.toString();
    }

    public boolean hasNextLine() throws IOException {
        return !twiceCheckChar();
    }

    private boolean isNextPartLSWithStep() throws IOException {
        int insideIndex = curIndex;
        for (int i = 0; i < lineSeparator.length(); i++) {
            if ((insideIndex + i) == curLength) {
                read();
                insideIndex = -i;
            }
            if (curData[insideIndex + i] != lineSeparator.charAt(i)) {
                partOfLineSeparator = new StringBuilder(lineSeparator.substring(0, i));
                curIndex = insideIndex + i;
                return false;
            }
        }
        curIndex = insideIndex + lineSeparator.length();
        return true;
    }

    private boolean checkEndBuffer() {
        return curIndex == curLength;
    }

    private boolean isEOF() {
        return curLength < 1;
    }


    @Override
    public void close() throws IOException {
        reader.close();
    }
}
