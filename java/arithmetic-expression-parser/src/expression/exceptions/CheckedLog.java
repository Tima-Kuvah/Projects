package expression.exceptions;

import expression.Log;
import expression.MyExpression;

public class CheckedLog extends Log {

    public CheckedLog(final MyExpression firstExpr, final MyExpression secondExpr) {
        super(firstExpr, secondExpr);
    }

    @Override
    protected int apply(int x, final int y) {
        int result = 0;
        if (x == 0 || y == 0 || y < 0) {
            return Integer.MIN_VALUE;
        } else if (x == 1){
            if (y == 1) {
                return Integer.MIN_VALUE;
            }
            else {
                return 0;
            }
        } else {
            while (x > y) {
                x = x / y;
                result++;
            }
        }
        return result;
    }
}
