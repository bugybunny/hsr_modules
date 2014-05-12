package week9.exercise1.calculator;

import java.util.ArrayList;

import week9.exercise1.calculator.MathLexer.LexerException;
import week9.exercise1.calculator.MathParser.ParserException;
import week9.exercise1.calculator.syntax.SyntaxNode;

public class MathSession {
    MathLexer                   lexer;
    MathParser                  parser;
    MathSolver                  solver;
    VariableContext             memory;

    ArrayList<StatisticBuilder> statistics;

    public MathSession() {
        lexer = new MathLexer();
        parser = new MathParser(lexer);
        solver = new MathSolver();

        statistics = new ArrayList<>();
        statistics.add(new StatAccessCount());
        statistics.add(new StatAccessSequence());

        memory = new VariableContext(statistics);
    }

    public String processCommand(String line) {
        try {
            if (line.equals("cstat")) {
                return statistics.get(0).toString();
            }
            if (line.equals("sstat")) {
                return statistics.get(1).toString();
            }

            lexer.initializeInput(line);
            SyntaxNode node = parser.parse();
            return solver.evaluate(node, memory);
        }
        catch (ParserException | LexerException | EvaluationException e) {
            return "ERROR " + e.getMessage();
        }
    }
}
