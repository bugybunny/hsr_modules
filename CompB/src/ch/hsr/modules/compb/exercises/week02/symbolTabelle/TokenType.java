package ch.hsr.modules.compb.exercises.week02.symbolTabelle;

/*
 * Die Reihenfolge ist so, 
 * dass jedes reservierte Wort < EOF ist.
 */
public enum TokenType {
	Bool, 
	Char, 
	Else, 
	False, 
	Float, 
	If, 
	Int, 
	Main, 
	True, 
	While, 
	Eof, 
	LeftBrace, RightBrace, 
	LeftBracket, RightBracket, LeftParen, RightParen, 
	Semicolon, Comma, Assign, Equals, Less, LessEqual, 
	Greater, GreaterEqual, Not, NotEqual, Plus, Minus, 
	Multiply, Divide, And, Or, Identifier, IntLiteral, 
	FloatLiteral, CharLiteral
}
