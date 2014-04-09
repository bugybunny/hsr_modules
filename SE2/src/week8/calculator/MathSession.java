package week8.calculator;

import week8.calculator.MathLexer.LexerException;
import week8.calculator.MathParser.ParserException;
import week8.calculator.MathSolver.EvaluationException;
import week8.calculator.syntax.SyntaxNode;

public class MathSession {
    MathLexer       lexer;
    MathParser      parser;
    MathSolver      solver;
    VariableContextBuilder memory;

    public MathSession() {
        lexer = new MathLexer();
        parser = new MathParser(lexer);
        solver = new MathSolver();
        memory = new VariableContext();
    }

    public String processCommand(String line) {
        try {
            lexer.initializeInput(line);
            SyntaxNode node = parser.parse();
            return solver.evaluate(node, memory);
        }
        catch (ParserException | LexerException | EvaluationException e) {
            return "ERROR " + e.getMessage();
        }
    }
}
