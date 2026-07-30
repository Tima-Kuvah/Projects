package expression;

public interface MyExpression extends Expression, TripleExpression, ListExpression, BigIntegerListExpression, BigDecimalListExpression {
    boolean isAssociative();
//
    default boolean isCommutable() {
        return false;
    }

    short getPriority();

    default short getPriorityAtFirst() {
        return getPriority();
    }
}


