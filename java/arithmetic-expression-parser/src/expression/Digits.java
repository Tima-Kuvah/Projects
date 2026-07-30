package expression;

public class Digits extends UnaryOperation {

    public Digits(final MyExpression expression) {
        super(expression);
    }

    @Override
    protected int apply(final int x) {
        int num = x;
        int answer = 0;
        while (num != 0) {
            int digit = num % 10;
            answer = answer + digit;
            num = num / 10;
        }

        return answer;
    }

    @Override
    protected String getOperator() {
        return "digits";
    }

    @Override
    public short getPriority() {
        return Short.MAX_VALUE - 200;
    }
}
