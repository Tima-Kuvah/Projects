package expression.exceptions;

import expression.Add;
import expression.MyExpression;

public class CheckedAdd extends Add  {
    public CheckedAdd(final MyExpression firstExpression, final MyExpression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    protected int apply(final int left, final int right) {
        if (left > 0 && right > Integer.MAX_VALUE - left) {
            throw new OverflowException("Add give Overflow Exception");
        }
        if (left < 0 && right < Integer.MIN_VALUE - left) {
            throw new OverflowException("Add give Overflow Exception");
        }

        return  left + right;
    }
}
