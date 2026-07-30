package expression;


public class Reverse extends UnaryOperation {
    public Reverse(final MyExpression expr) {
        super(expr);
    }

    @Override
    protected int apply(final int x) {
        int num = x;
        int reversed = 0;
            while (num != 0) {
                int digit = num % 10;
                reversed = reversed * 10 + digit;
                num = num / 10;
            }

        return reversed;
    }

    @Override
    protected String getOperator() {
        return "reverse";
    }

    @Override
    public short getPriority() {
        return Short.MAX_VALUE - 200;
    }
}
