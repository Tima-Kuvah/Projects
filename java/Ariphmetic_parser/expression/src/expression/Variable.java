package expression;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

public class Variable implements MyExpression {
    private final String name;
    protected final int index;

    public Variable(final String variable) {
        this.name = variable;
        this.index = -1;
    }

    public Variable(final int number) {
        this.name = "$" + number;
        this.index = number;
    }

    @Override
    public int evaluate(final int x) {
        return x;
    }

    @Override
    public int evaluate(final int x, final int y, final int z) {
        return switch (name) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new IllegalArgumentException(); //вообще обработка ошибок, но пока не нужно пусть так
        };
    }

    private int getIndex() {
        return Integer.parseInt(name.substring(1));
    }

    @Override
    public int evaluate(final List<Integer> variables) {
        return variables.get(getIndex());
    }

    @Override
    public BigInteger evaluateBi(final List<BigInteger> variables) {
        return variables.get(getIndex());
    }

    @Override
    public BigDecimal evaluateBd(final List<BigDecimal> variables) {
        return variables.get(getIndex());
    }


    @Override
    public String toString() {
        return String.valueOf(name);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Variable aVar = (Variable) obj;
        return Objects.equals(name, aVar.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
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
