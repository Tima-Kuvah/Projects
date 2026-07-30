package expression;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Min extends BinaryOperation {

    public Min(final MyExpression firstExpr, final MyExpression secondExpr) {
        super(firstExpr, secondExpr);
    }

    @Override
    protected int apply(final int left, final int right) {
        return left > right ? right : left;
    }

    @Override
    protected String getOperator() {
        return "min";
    }

    @Override
    public boolean isAssociative() {
        return true;
    }

    @Override
    public short getPriority() {
        return 9;
    }

    @Override
    public short getPriorityAtFirst() {
        return 11;
    }

    @Override
    public boolean isCommutable() {
        return true;
    }
}
