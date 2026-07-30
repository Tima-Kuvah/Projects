package expression;

import java.util.Map;

public class Log extends BinaryOperation {
    public Log(MyExpression firstExpr, MyExpression secondExpr) {
        super(firstExpr, secondExpr);
    }

    @Override
    protected String getOperator() {
        return "//";
    }

    private int fastPower(int x, int y) {
        int base = x;
        int result = (y % 2 == 1) ? x : 1;
        while (y > 1) {
            base = base * base;
            y = y / 2;
            if (y % 2 == 1) {
                result = result * base;
            }
        }
        return result;
    }

    protected int fastLog(final int x, final int y) {
        if (x < y) return 0;

        int left = 0;
        int right = 31;
        int result = 0;


        while (left <= right) {
            int mid = left + (right - left) / 2;
            long power = fastPower(y, mid);

            if (power <= x) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    @Override
    protected int apply(final int x, final int y) {
        return fastLog(x, y);
    }

    @Override
    public short getPriority() {
        return 2000;
    }

    @Override
    public boolean isAssociative() {
        return false;
    }
}
