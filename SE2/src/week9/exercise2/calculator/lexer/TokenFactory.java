package week9.exercise2.calculator.lexer;

import java.util.Dictionary;
import java.util.Hashtable;

public class TokenFactory {

    Dictionary<TokenType, TokenSymbol> tokens;

    public TokenFactory() {

        tokens = new Hashtable<TokenType, TokenSymbol>();

        for (TokenType t : TokenType.values())
            tokens.put(t, new TokenSymbol(t));
    }

    public TokenSymbol getToken(TokenType t) {

        return tokens.get(t);
    }
}
