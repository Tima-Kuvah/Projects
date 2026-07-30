package expression.exceptions;

import expression.MyExpression;
import expression.Pow2;

public class CheckedPow2 extends Pow2 {
    public CheckedPow2(MyExpression expression) {
        super(expression);
    }

    @Override
    protected int fastPower2(int x) throws MathException {
        if (x < 0) {
            throw new PowException("The base of the pow must be not negative");
        }
        if (x >= 31) {
            throw new OverflowException("Pow2 giving overflow");
        }
        return super.fastPower2(x);
    }
}
