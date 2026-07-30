package expression;

public class Ceil extends UnaryOperation {
    public Ceil(final MyExpression expression) {
        super(expression);
    }


    @Override
    protected int apply(final int x) {
        if (x % 1000 == 0) {
            return x;
        }
        if (x >= 0) {
            return (x + (1000 - x % 1000));
        } else {
            return (x - (x % 1000));
        }
    }

    @Override
    protected String getOperator() {
        return "ceiling";
    }

    @Override
    public short getPriority() {
        return Short.MAX_VALUE - 200;
    }
}
