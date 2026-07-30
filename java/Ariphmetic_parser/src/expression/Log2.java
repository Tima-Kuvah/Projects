package expression;

public class Log2 extends UnaryOperation {
    public Log2(MyExpression expression) {
        super(expression);
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

    protected int fastLog2(final int x) {
        final int y = 2;
        if (x < y) return 0;

        int left = 0;
        int right = 30;
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
    protected int apply(final int x) {
        return fastLog2(x);
    }

    @Override
    protected String getOperator() {
        return "log₂";
    }

    @Override
    public short getPriority() {
        return Short.MAX_VALUE - 200;
    }
}
