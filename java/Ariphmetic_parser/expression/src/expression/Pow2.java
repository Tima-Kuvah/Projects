package expression;

public class Pow2 extends UnaryOperation {
    public Pow2(MyExpression expression) {
        super(expression);
    }

    @Override
    protected int apply(final int x) {
        return fastPower2(x);
    }

    protected int fastPower2(int x) {
        int base = 2;
        int result = (x % 2 == 1) ? 2 : 1;
        while (x > 1) {
            base = base * base;
            x = x / 2;
            if (x % 2 == 1) {
                result = result * base;
            }
        }
        return result;
    }

    @Override
    protected String getOperator() {
        return "pow₂";
    }

    @Override
    public short getPriority() {
        return Short.MAX_VALUE - 200;
    }
}
