package week8.calculator.lexer;

public class TokenSymbol implements LexicalSymbol {
    private TokenType tokenType;

    public TokenSymbol(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public TokenType getTokenType() {
        return tokenType;
    }
}
