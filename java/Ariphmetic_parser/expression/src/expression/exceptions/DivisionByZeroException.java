package expression.exceptions;

public class DivisionByZeroException extends MathException {
    public DivisionByZeroException(String massage) {
        super(massage);
    }
}
