package expression.exceptions;

import expression.Multiply;
import expression.MyExpression;

import java.security.spec.RSAOtherPrimeInfo;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(final MyExpression firstExpression, final MyExpression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    public int apply(final int left, final int right) {
        if (left > 0) {
            if (right > 0) {
                if (left > Integer.MAX_VALUE / right) {
                    throw new OverflowException("Multiply give overflow");
                }
            } else if (right < 0) {
                if (right < Integer.MIN_VALUE / left) {
                    throw new OverflowException("Multiply give overflow");
                }
            }

        } else if (left < 0) {
            if (right > 0) {
                if (left < Integer.MIN_VALUE / right) {
                    throw new OverflowException("Multiply give overflow");
                }
            } else if (right < 0) {
                if (left < Integer.MAX_VALUE / right) {
                    throw new OverflowException("Multiply give overflow");
                }
            }
        }

        return left * right;
    }
}
