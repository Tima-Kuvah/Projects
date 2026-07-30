package expression.exceptions;

import expression.Log2;
import expression.MyExpression;

public class CheckedLog2 extends Log2 {
    public CheckedLog2(MyExpression expression) {
        super(expression);
    }

    @Override
    protected int fastLog2(int x) throws MathException {
        if (x <= 0) {
            throw new LogException("The base of the logarithm must be positive");
        }
        return super.fastLog2(x);
    }
}
