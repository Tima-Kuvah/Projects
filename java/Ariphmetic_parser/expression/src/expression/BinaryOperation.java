package expression;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

public abstract class BinaryOperation implements MyExpression {
    private final MyExpression firstExpr;
    private final MyExpression secondExpr;

    public BinaryOperation(final MyExpression firstExpr, final MyExpression secondExpr) {
        this.firstExpr = firstExpr;
        this.secondExpr = secondExpr;
    }

    public int evaluate(final int x) {
        return apply(firstExpr.evaluate(x), secondExpr.evaluate(x));
    }

    public int evaluate(final int x, final int y, final int z) {
        return apply(firstExpr.evaluate(x, y, z), secondExpr.evaluate(x, y, z));
    }


    @Override
    public BigInteger evaluateBi(final List<BigInteger> variables) {
        return biApply(firstExpr.evaluateBi(variables), secondExpr.evaluateBi(variables));
    }

    protected BigInteger biApply(final BigInteger left, final BigInteger right) {
        return null;
    };

    @Override
    public BigDecimal evaluateBd(final List<BigDecimal> variables) {
        return bdApply(firstExpr.evaluateBd(variables), secondExpr.evaluateBd(variables));
    }

    // :NOTE: +
    protected BigDecimal bdApply(final BigDecimal left, final BigDecimal right) {
        return null;
    };

    @Override
    public int evaluate(final List<Integer> variables) {
        return listApplay(firstExpr.evaluate(variables), secondExpr.evaluate(variables));
    }

    protected int listApplay(final int left, final int right) {
        return this.apply(left, right);
    };


    // :NOTE: +
    protected abstract int apply(final int left, final int right);

    @Override
    public String toString() {
        return "(" + firstExpr.toString() + " " + this.getOperator() + " " + secondExpr.toString() + ")";
    }

    @Override
    public String toMiniString() {
        String answer;
        if (leftParentheses()) {
            answer = "(" + firstExpr.toMiniString() + ") " + this.getOperator() + " ";
        } else {
            answer = firstExpr.toMiniString() + " " + this.getOperator() + " ";
        }
        if (rightParentheses()) {
            answer += "(" + secondExpr.toMiniString() + ")";
        } else {
            answer += secondExpr.toMiniString();
        }
        return answer;
    }

    private boolean leftParentheses() {
        return firstExpr.getPriorityAtFirst() < this.getPriority();
    }

    private boolean rightParentheses() {
        return this.getPriority() > secondExpr.getPriority() ||
                (this.getPriority() == secondExpr.getPriority() &&
                        (!secondExpr.isAssociative() || !this.isAssociative())) ||
                (this.isCommutable() && secondExpr.isCommutable() &&
                this.getPriorityAtFirst() != secondExpr.getPriorityAtFirst());
    }

    // :NOTE:
    protected abstract String getOperator();

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BinaryOperation op = (BinaryOperation) obj;
        return Objects.equals(firstExpr, op.firstExpr) && Objects.equals(secondExpr, op.secondExpr);
    }

    @Override
    public int hashCode() {
        // :NOTE:
        return Objects.hash(firstExpr, secondExpr, this.getClass()); //берет
    }

}
