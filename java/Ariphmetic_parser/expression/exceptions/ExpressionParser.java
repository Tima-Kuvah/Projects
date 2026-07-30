package expression.exceptions;

import expression.*;


import javax.swing.plaf.PanelUI;
import java.util.List;

public class ExpressionParser implements ListParser {

    @Override
    public ListExpression parse(String expression, List<String> variables) throws ParsingException {
        return new Parser(expression).parseExpression();
    }

    private static final class Parser extends BaseParser {

        public Parser(final String source) {
            super(new StringSource(source));
        }

        private ListExpression parseExpression() throws ParsingException {
            final ListExpression result = parseElement();
            if (eof()) {
                return result;
            }
            throw new EndExpectedException("At " + getPos() + " position end of expression expeected, but get char \"" +
                    getCh() + "\" UTF-16 code of this char is " + (int) getCh());
        }

        private MyExpression parseElement() throws ParsingException {
            return parseAddSub();

        }

        private MyExpression parseAddSub() throws ParsingException {
            MyExpression currExpr = parseMultiplyDivide();
            while (true) {
                if (take('+')) {
                    currExpr = new CheckedAdd(currExpr, parseMultiplyDivide());
                } else if (take('-')) {
                    currExpr = new CheckedSubtract(currExpr, parseMultiplyDivide());
                } else {
                    skipWhitespace();
                    return currExpr;
                }
            }
        }

        private MyExpression parseMultiplyDivide() throws ParsingException {

            MyExpression currExpr = parseUnariOperarions();

            while (true) {
                if (take('*')) {
                    skipWhitespace();
                    currExpr = new CheckedMultiply(currExpr, parseUnariOperarions());
                    skipWhitespace();
                } else if (take('/')) { // на расширение кода
                    skipWhitespace();
                    currExpr = new CheckedDivide(currExpr, parseUnariOperarions());
                    skipWhitespace();
                } else {
                    return currExpr;
                }
            }
        }

        private MyExpression parseUnariOperarions() throws ParsingException {
            skipWhitespace();
            if (take("low")) {
                if (between('0', '9')) {
                    throw new ParsingException("Number after high");
                }
                return new Low(parseUnariOperarions());

            }
            if (take("high")) {
                if (between('0', '9')) {
                    throw new ParsingException("Number after high");
                }
                return new High(parseUnariOperarions());

            }

            return parseNum(); // :NOTE: почему это parseNum, если там много разного
        }

        private MyExpression parseNum() throws ParsingException {
            skipWhitespace();
            MyExpression currExpr = parseInner();
            skipWhitespace();
            return currExpr;
        }

        private MyExpression parseInner() throws ParsingException {
            if (take('(')) {
                final MyExpression currExpr = parseElement();
                skipWhitespace();
                expect(')', new CloseBucketException("At position " + getPos() +
                        " expected " + "')'" + ", but found '" + getCh() + "'. " +
                        "UTF-16 code of char is " + (int) getCh()));
                return currExpr;

            }

            if (take('-')) {
                if (between('0', '9')) {
                    return parseConst("-");

                } else {
                    int beginPos = getPos() - 1;
                    try {
                        return new CheckedNegate(parseUnariOperarions());
                    } catch (ParsingException e) {
                        throw new NegativeParseException("В выражении для унарного минуса допущена ошибка: " + /*System.lineSeparator() +*/
                                /*System.lineSeparator() + */
                                "Унарный минус начинается на позиции: " + beginPos + " Исправьте свою строку и попробуйте заново.");
                    }
                } //чет добавить типо ошибка того что после минуса не идет число или унарный минус от выражения не создается
            }

            if (between('0', '9')) {
                return parseConst("");
            }

            if (take('$')) {
                return parseVariable();
            }

            throw new ParsingException("Your String is not supported: " +
                    "at " + getPos() + " position your char \"" + getCh() + "\" is unexpected. " +
                    "UTF-16 code for this char is " + (int) getCh() +
                    System.lineSeparator() + "The original line \"" + getSource() + "\"");

        }

        private MyExpression parseVariable() throws VariableIndexError {
            final String s = parseNumber("");
            int index;

            if (s.isEmpty()) {
                throw new VariableIndexError("Syntax error: Variable index expected after '$'." + System.lineSeparator() +
                        "Input string \"" + getSource() + "\". Error at " + getPos() + " position");
            }

            try {
                index = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                throw new VariableIndexError("Syntax error: Variable index after '$' to lurge." + System.lineSeparator() +
                        "Input string :" + getSource() + ". Your Index is " + s + " but he must be less then " +
                        Integer.MAX_VALUE + "." + System.lineSeparator() +
                        "In input string this index begin at " + (getPos() - s.length()) + " position");
            }

            return new Variable(index);
        }

        private Const parseConst(final String prefix) throws ConstSizeException {
            final String s = parseNumber(prefix);
            int value;

            try {
                value = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                String errMassege;
                if (prefix.isEmpty()) {
                    errMassege = "less then " + Integer.MAX_VALUE + "." + System.lineSeparator();
                } else {
                    errMassege = "more then " + Integer.MIN_VALUE + "." + System.lineSeparator();
                }
                throw new ConstSizeException("Syntax error: input constant to lurge." + System.lineSeparator() +
                        "Input string :" + getSource() + ". Your const is " + s + " but he must be " + errMassege +
                        "In input string this const begin at " + (getPos() - s.length()) + " position");
            }
            return new Const(value);
        }

        private String parseNumber(final String prefix) {
            final StringBuilder sb = new StringBuilder(prefix);
            while (between('0', '9')) {
                sb.append(take());
            }
            return sb.toString();
        }

        private void skipWhitespace() {
            while (Character.isWhitespace(getCh())) {
                take();
                // skip
            }
        }
    }
}
