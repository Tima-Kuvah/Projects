package expression;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Max extends BinaryOperation {
    public Max(final MyExpression firstExpr, final MyExpression secondExpr) {
        super(firstExpr, secondExpr);
    }

    @Override
    protected int apply(final int left, final int right) {
        return left > right ? left : right;
    }

    @Override
    protected String getOperator() {
        return "max";
    }

    @Override
    public boolean isAssociative() {
        return true;
    }

    @Override
    public short getPriority() {
        return 10;
    }

    @Override
    public boolean isCommutable() {
        return true;
    }
}
