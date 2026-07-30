package expression.exceptions;

import expression.MyExpression;
import expression.Subtract;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(final MyExpression firstExpression, MyExpression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    protected int apply(final int left, final int right) throws OverflowException {
        if (right > 0) {
            if (left < Integer.MIN_VALUE + right) {
                throw new OverflowException("Subtruct give overflow");
            }
        } else if (right < 0) {
            if (left > Integer.MAX_VALUE + right) {
                throw new OverflowException("Subtruct give overflow");
            }
        }
        return left - right;
    }
}
