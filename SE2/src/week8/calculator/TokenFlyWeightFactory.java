package week8.calculator;

import java.util.HashMap;
import java.util.Map;

import week8.calculator.lexer.TokenSymbol;
import week8.calculator.lexer.TokenType;

public class TokenFlyWeightFactory {
    private Map<TokenType, TokenSymbol> tokenMap;

    public TokenFlyWeightFactory() {
        tokenMap = new HashMap<>(TokenType.values().length);
        for (TokenType tempTokenType : TokenType.values()) {
            tokenMap.put(tempTokenType, new TokenSymbol(tempTokenType));
        }
    }

    public TokenSymbol get(TokenType aTokenType) {
        return tokenMap.get(aTokenType);
    }
}
