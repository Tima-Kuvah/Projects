package expression;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Divide extends BinaryOperation {

    public Divide(final MyExpression firstExpression, MyExpression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    protected BigInteger biApply(final BigInteger left, final BigInteger right) {
        return left.divide(right);
    }

    @Override
    protected BigDecimal bdApply(final BigDecimal left, final BigDecimal right) {
        return left.divide(right);
    }

    @Override
    protected int apply(final int left, final int right) {
        return left / right;
    }

    @Override
    protected String getOperator() {
        return "/";
    }

    @Override
    public boolean isAssociative() {
        return false;
    }

    @Override
    public short getPriority() {
        return 1000;
    }
}
