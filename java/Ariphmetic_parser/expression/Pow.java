package expression;

import java.util.Map;

public class Pow extends BinaryOperation {

    public Pow(final MyExpression firstExpr, final MyExpression secondExpr) {
        super(firstExpr, secondExpr);
    }

    @Override
    protected String getOperator() {
        return "**";
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

    @Override
    protected int apply(final int x, final int y) {
        return fastPower(x, y);
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
