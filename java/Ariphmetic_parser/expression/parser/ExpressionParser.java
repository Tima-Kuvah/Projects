package expression.parser;

import expression.*;

import java.util.List;

public class ExpressionParser implements ListParser {

    @Override
    public ListExpression parse(String expression, List<String> variables) {
        return new Parser(expression).parseExpression();
    }

    private static final class Parser extends BaseParser {

        public Parser(final String source) {
            super(new StringSource(source));
        }

        private ListExpression parseExpression() {
            final ListExpression result = parseElement();
            if (eof()) {
                return result;
            }
            throw error("End of Exp expected");
        }

        private MyExpression parseElement() {
            return parseMinMax();
        }

        private MyExpression parseMinMax() {
            MyExpression currExpr = parseAddSub();
            while (true) {
//                for (String op : operations[lavel]) {
//                    if (take(op)) {
//                        currExpr = ....(currExpr, parseAddSub());
//                    }
//                }
                if (take("min")) {
                    currExpr = new Min(currExpr, parseAddSub());
                } else if (take("max")) {
                    currExpr = new Max(currExpr, parseAddSub());
                } else {
                    return currExpr;
                }
            }
        }

        private MyExpression parseAddSub() {
            MyExpression currExpr = parseMultiplyDivide();
            while (true) {
                if (take('+')) {
                    currExpr = new Add(currExpr, parseMultiplyDivide());
                } else if (take('-')) {
                    currExpr = new Subtract(currExpr, parseMultiplyDivide());
                } else {
                    return currExpr;
                }
            }
        }

        private MyExpression parseMultiplyDivide() {
            MyExpression currExpr = parseUnariOperarions();
            while (true) {
                if (take('*')) {
                    currExpr = new Multiply(currExpr, parseUnariOperarions());

                } else if (take('/')) { // на расширение кода
                    currExpr = new Divide(currExpr, parseUnariOperarions());

                } else {
                    return currExpr;
                }
            }
        }

        private MyExpression parseUnariOperarions() {
            skipWhitespace();

            if (check("ceiling") || check("floor") ||
                    check("reverse") || check("digits")) {
                // :NOTE: общий код, switch
                if (take("ceiling")) {
                    return new Ceil(parseUnariOperarions());

                }

                if (take("floor")) {
                    return new Floor(parseUnariOperarions());

                }

                if (take("reverse")) {
                    return new Reverse(parseUnariOperarions());

                }

                if (take("digits")) {
                    return new Digits(parseUnariOperarions());

                }
            }
            return parseNum();
        }

        private MyExpression parseNum() {
            MyExpression currExpr = parseInner();
            skipWhitespace();
            return currExpr;
        }

        private MyExpression parseInner() {
            if (take('(')) {
                final MyExpression currExpr = parseElement();
                expect(')');
                return currExpr;

            }

            if (take('-')) {
                if (between('0', '9')) {
                    return parseConst("-");

                } else {
                    return new Negate(parseUnariOperarions());

                }
            }

            if (between('0', '9')) {
                return parseConst("");
            }

            if (take('$')) {
                return parseVariable();
            }
            throw new IllegalStateException("Your String is strange " + getSource() + " " + getCh() + " " + getPos());

        }

        private MyExpression parseVariable() {
            return new Variable(parseInt(""));
        }

        private Const parseConst(final String prefix) {
            return new Const(parseInt(prefix));
        }

        private int parseInt(final String prefix) {
            final StringBuilder sb = new StringBuilder(prefix);
            while (between('0', '9')) {
                sb.append(take());
            }
            return Integer.parseInt(sb.toString());
        }

        private void skipWhitespace() {
            while (Character.isWhitespace(getCh())) {
                take();
                // skip
            }
        }
    }
}
