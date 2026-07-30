package expression;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Add extends BinaryOperation {

    public Add(final MyExpression firstExpression, final MyExpression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    protected int apply(final int left, final int right) {
        return left + right;
    }

    @Override
    protected BigInteger biApply(final BigInteger left, final BigInteger right) {
        return left.add(right);
    }

    @Override
    protected BigDecimal bdApply(final BigDecimal left, final BigDecimal right) {
        return left.add(right);
    }

    @Override
    protected String getOperator() {
        return "+";
    }

    @Override
    public boolean isAssociative() {
        return true;
    }

    @Override
    public short getPriority() {
        return 99;
    }

    @Override
    public short getPriorityAtFirst() {
        return 100;
    }


}
