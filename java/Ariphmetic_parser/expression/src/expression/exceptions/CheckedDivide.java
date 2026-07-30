package expression.exceptions;

import expression.Divide;
import expression.MyExpression;

public class CheckedDivide extends Divide {
    public CheckedDivide(final MyExpression firstExpression, final MyExpression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    protected int apply(final int left, final int right) {
        if (right == 0) {
            throw new DivisionByZeroException("Dividing by zero!! Your expression after evaluate: " + left + " / " + right);
        }
        if (left == Integer.MIN_VALUE && right == -1) {
            throw new OverflowException("Divied give Overflow!! Your expression after evaluate: " + left + " / " + right);
        }
        return super.apply(left, right);
    }
}
