package ch.hsr.modules.compb.exercises.week01;

class Program {
    // a:=5+3; b:=(print(a, a-1), 10*a); print(b);
    static Stm prog =
    // a = 5 + 3;
    new CompoundStm(new AssignStm("a", new OpExp(new NumExp(5), OpExp.Plus,
            new NumExp(3))),

    // b = (print(a, a-1), 10*a);
            new CompoundStm(new AssignStm("b", new EseqExp(new PrintStm(
                    new PairExpList(new IdExp("a"), new LastExpList(new OpExp(
                            new IdExp("a"), OpExp.Minus, new NumExp(1))))),
                    new OpExp(new NumExp(10), OpExp.Times, new IdExp("a")))),
                    new PrintStm(new LastExpList(new IdExp("b")))));

}
