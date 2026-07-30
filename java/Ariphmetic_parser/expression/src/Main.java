import expression.*;
import expression.exceptions.ExpressionParser;
import expression.exceptions.ParsingException;

import java.util.ArrayList;
import java.util.List;

public class Main {
     public static void main(String[] args) throws ParsingException {
        System.out.println(1362949397 + 40808071 - 10);
        String s = "0 + 0";
        System.out.println(-1 % 2);

        MyExpression expr = (MyExpression) new ExpressionParser().parse(s, List.of());
        System.out.println(expr);
    }
}