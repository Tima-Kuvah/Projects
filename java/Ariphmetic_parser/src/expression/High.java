package expression;

public class High extends UnaryOperation {
    public High(MyExpression expression) {
        super(expression);
    }

    @Override
    protected int apply(int x) {
        return Integer.highestOneBit(x);
    }

    @Override
    protected String getOperator() {
        return "high";
    }

    @Override
    public short getPriority() {
        return Short.MAX_VALUE - 200;
    }
}
