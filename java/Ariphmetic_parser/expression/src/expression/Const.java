package expression;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;


public record Const(BigDecimal value) implements MyExpression {
    public Const(final int value) {
        this(BigDecimal.valueOf(value));
    }

    public Const(final BigInteger value) {
        this(new BigDecimal(value));
    }

    @Override
    public int evaluate(final int x) {
        return value.intValueExact();
    }

    @Override
    public int evaluate(final List<Integer> variables) {
        return value.intValueExact();
    }

    @Override
    public int evaluate(final int x, final int y, final int z) {
        return value.intValueExact();
    }

    @Override
    public BigInteger evaluateBi(final List<BigInteger> variables) {
        return value.toBigIntegerExact();
    }

    @Override
    public BigDecimal evaluateBd(final List<BigDecimal> variables) {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Const aConst = (Const) obj;
        return Objects.equals(value, aConst.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean isAssociative() {
        return false;
    }

    @Override
    public short getPriority() {
        return Short.MAX_VALUE;
    }


}
