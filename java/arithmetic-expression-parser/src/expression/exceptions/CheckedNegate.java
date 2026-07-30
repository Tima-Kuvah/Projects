package expression.exceptions;

import expression.MyExpression;
import expression.Negate;

public class CheckedNegate extends Negate {
    public CheckedNegate(final MyExpression expression) {
        super(expression);
    }

    @Override
    public int apply(int x) throws OverflowException {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException("Negate giving overflow: " + "- " + Integer.MIN_VALUE);
        }
        return super.apply(x);
    }
}
