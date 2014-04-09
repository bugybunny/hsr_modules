package week8.calculator;

import week8.calculator.MathLexer.LexerException;
import week8.calculator.lexer.EndSymbol;
import week8.calculator.lexer.IdentifierSymbol;
import week8.calculator.lexer.IntegerSymbol;
import week8.calculator.lexer.LexicalSymbol;
import week8.calculator.lexer.TokenSymbol;
import week8.calculator.lexer.TokenType;
import week8.calculator.syntax.Assignment;
import week8.calculator.syntax.BinaryExpression;
import week8.calculator.syntax.Designator;
import week8.calculator.syntax.Expression;
import week8.calculator.syntax.Number;
import week8.calculator.syntax.Operator;
import week8.calculator.syntax.SyntaxNode;
import week8.calculator.syntax.UnaryExpression;

// Syntax in EBNF:
// ---------------
// Assignment = [ Designator "=" ] Expression.
// Expression = Term { ("+" | "-") Term }.
// Term = Factor { ("*" | "/" | "%") Factor }.
// Factor = Operand | ("+" | "-") Factor | "(" Expression ")".
// Operand = Designator | Integer.
// Designator = Identifier.
// Integer = Digit { Digit }.
// Identifier = Letter { Letter | Digit }.
// Digit = "0" .. "9".
// Letter = "A" .. "Z" | "a" .. "z".
// ---------------

public class MathParser { // LL1 Parser
    private MathLexer     lexer;
    private LexicalSymbol current;

    public MathParser(MathLexer lexer) {
        this.lexer = lexer;
    }

    public SyntaxNode parse() throws ParserException {
        nextSymbol();
        Assignment assignment = parseAssignment();
        if (!(current instanceof EndSymbol)) {
            throw new ParserException("End expected");
        }
        return assignment;
    }

    private Assignment parseAssignment() throws ParserException {
        Designator designator = parseDesignator();
        checkToken(TokenType.EQUAL_SIGN);
        Expression expression = parseExpression();
        return new Assignment(designator, expression);
    }

    private Expression parseExpression() throws ParserException {
        Expression left = parseTerm();
        while (isToken(TokenType.PLUS_SIGN) || isToken(TokenType.HYPHEN_SIGN)) {
            Operator operator = parseOperator();
            Expression right = parseTerm();
            left = new BinaryExpression(left, operator, right);
        }
        return left;
    }

    private Expression parseTerm() throws ParserException {
        Expression left = parseFactor();
        while (isToken(TokenType.ASTERISK_SIGN)
                || isToken(TokenType.SLASH_SIGN)
                || isToken(TokenType.PERCENTAGE_SIGN)) {
            Operator operator = parseOperator();
            Expression right = parseFactor();
            left = new BinaryExpression(left, operator, right);
        }
        return left;
    }

    private Expression parseFactor() throws ParserException {
        if (isToken(TokenType.PLUS_SIGN) || isToken(TokenType.HYPHEN_SIGN)) {
            Operator operator = parseOperator();
            Expression subExpression = parseFactor();
            return new UnaryExpression(operator, subExpression);
        } else if (isToken(TokenType.OPEN_PARENTHESIS)) {
            nextSymbol();
            Expression subExpression = parseExpression();
            checkToken(TokenType.CLOSE_PARENTHESIS);
            return subExpression;
        } else if (current instanceof IdentifierSymbol) {
            return parseDesignator();
        } else if (current instanceof IntegerSymbol) {
            int value = parseInteger();
            return new Number(value);
        } else {
            throw new ParserException("Factor expected");
        }
    }

    private Operator parseOperator() throws ParserException {
        if (!(current instanceof TokenSymbol)) {
            throw new ParserException("Operator expected");
        }
        Operator op;
        TokenType tokenType = ((TokenSymbol) current).getTokenType();
        switch (tokenType) {
            case PLUS_SIGN:
                op = Operator.ADD;
                break;
            case HYPHEN_SIGN:
                op = Operator.SUB;
                break;
            case ASTERISK_SIGN:
                op = Operator.MUL;
                break;
            case SLASH_SIGN:
                op = Operator.DIV;
                break;
            case PERCENTAGE_SIGN:
                op = Operator.MOD;
                break;
            default:
                throw new ParserException("Operator expected");
        }
        nextSymbol();
        return op;
    }

    private Designator parseDesignator() throws ParserException {
        String identifier = parseIdentifier();
        return new Designator(identifier);
    }

    private String parseIdentifier() throws ParserException {
        if (!(current instanceof IdentifierSymbol)) {
            throw new ParserException("Identifier expected");
        }
        String identifier = ((IdentifierSymbol) current).getIdentifier();
        nextSymbol();
        return identifier;
    }

    private int parseInteger() throws ParserException {
        if (!(current instanceof IntegerSymbol)) {
            throw new ParserException("Integer expected");
        }
        int value = ((IntegerSymbol) current).getValue();
        nextSymbol();
        return value;
    }

    private boolean isToken(TokenType tokenType) {
        return current instanceof TokenSymbol
                && ((TokenSymbol) current).getTokenType() == tokenType;
    }

    private void checkToken(TokenType tokenType) throws ParserException {
        if (isToken(tokenType)) {
            nextSymbol();
        } else {
            throw new ParserException("Expected token " + tokenType);
        }
    }

    private void nextSymbol() throws ParserException {
        try {
            current = lexer.nextSymbol();
        }
        catch (LexerException e) {
            throw new ParserException("Lexical error " + e.getMessage());
        }
    }

    public class ParserException extends Exception {
        private static final long serialVersionUID = -4064527315382893541L;

        public ParserException(String message) {
            super(message);
        }
    }
}
