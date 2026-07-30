package expression;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

public abstract class UnaryOperation implements MyExpression {
    protected final MyExpression expression;

    protected UnaryOperation(MyExpression expression) {
        this.expression = expression;
    }

    protected abstract int apply(final int x);

    protected int listApply(final int x) {
        return this.apply(x);
    }

    protected abstract String getOperator();

    @Override
    public String toString() {
        return this.getOperator() + "(" + expression.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (getPriority() <= expression.getPriority()) {
            return this.getOperator() + " " + expression.toMiniString();
        } else {
            return this.getOperator() + "(" + expression.toMiniString() + ")";
        }
    }


    // :NOTE: вынести само вычисление в асбстрактный метод +
    @Override
    public int evaluate(final int var) {
        return this.apply(expression.evaluate(var));
    }

    @Override
    public int evaluate(final List<Integer> variables) {
        return this.listApply(expression.evaluate(variables));
    }

    @Override
    public boolean isAssociative() {
        return false;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UnaryOperation op = (UnaryOperation) obj;
        return Objects.equals(expression, op.expression);
    }

    @Override
    public int hashCode(){
        return Objects.hash(expression, this.getClass());
    }

    @Override
    public int evaluate(final int var1, final int var2, final int var3) {
        return 0; //так как для унарных операций не требуется поддержание TripleExpression
    }

    @Override
    public BigInteger evaluateBi(final List<BigInteger> variables) {
        return null; //так как для унарных операций не требуется поддержание BigIntegerLitExpression
    }

    @Override
    public BigDecimal evaluateBd(final List<BigDecimal> variables) {
        return null;//так как для унарных операций не требуется поддержание BigDecimailLitExpression
    }
}
