package expression;

public class Negate extends UnaryOperation {

    public Negate(final MyExpression expr) {
        super(expr);
    }

    @Override
    public int apply(final int x) {
        return -x;
    }

    @Override
    public String toString() {
            return "-(" + expression.toString() + ")";
    }

    @Override
    public short getPriority() {
        return Short.MAX_VALUE - 200;
    }

    @Override
    protected String getOperator() {
        return "-";
    }
}
