package expression;

public class Low extends UnaryOperation {
    public Low(MyExpression expression) {
        super(expression);
    }

    @Override
    protected int apply(int x) {
        return x & -x;
    }

    @Override
    protected String getOperator() {
        return "low";
    }

    @Override
    public short getPriority() {
        return Short.MAX_VALUE - 200;
    }
}
