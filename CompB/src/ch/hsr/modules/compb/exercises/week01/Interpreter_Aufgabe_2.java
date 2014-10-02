package ch.hsr.modules.compb.exercises.week01;

class Interpreter_Aufgabe_2 {
    static void interp(final Stm s) {
        interpStm(s, null);
    }

    private static Table interpStm(final Stm s, final Table t) {
        if (s instanceof CompoundStm) {
            final CompoundStm stm = (CompoundStm) s;
            return interpStm(stm.stm2, interpStm(stm.stm1, t));
        } else {
            return interpExp(s, t);
        }
    }

    private static Table interpExp(final Stm s, final Table t) {
        if (s instanceof PrintStm) {
            return print(((PrintStm) s).exps, t);
        } else if (s instanceof AssignStm) {
            final AssignStm assign = (AssignStm) s;
            final IntAndTable it = interpExp(assign.exp, t);
            return new Table(assign.id, it.i, t);
        }
        return null;
    }

    private static Table print(final ExpList es, final Table t) {
        if (es instanceof PairExpList) {
            return print((PairExpList) es, t);
        } else {
            return print((LastExpList) es, t);
        }
    }

    private static Table print(final PairExpList exps, final Table t) {
        return print(exps.tail, printIt(exps.head, t, false));
    }

    private static Table print(final LastExpList exp, final Table t) {
        return printIt(exp.head, t, true);
    }

    private static Table printIt(final Exp e, final Table t,
            final boolean newLine) {
        final IntAndTable it = interpExp(e, t);
        if (newLine) {
            System.out.println(it.i);
        } else {
            System.out.print(it.i + " ");
        }
        return it.t;
    }

    /* Der Einfachheithalber verzichten wir auf Exceptions und gehen davon aus,
     * dass keine Division durch 0 oder Ähnliches abgefangen werden muss! */
    private static IntAndTable interpExp(final Exp e, final Table t) {
        if (e instanceof IdExp) {
            final IdExp ex = (IdExp) e;
            return new IntAndTable(t.lookup(ex.id), t);
        } else if (e instanceof NumExp) {
            final NumExp ex = (NumExp) e;
            return new IntAndTable(ex.num, t);
        } else if (e instanceof EseqExp) {
            final EseqExp ex = (EseqExp) e;
            final Table t2 = interpStm(ex.stm, t);
            return interpExp(ex.exp, t2);
        } else {
            final OpExp ex = (OpExp) e;
            final IntAndTable it1 = interpExp(ex.left, t);
            final IntAndTable it2 = interpExp(ex.right, it1.t);
            int result = 0;
            switch (ex.oper) {
                case OpExp.Plus:
                    result = it1.i + it2.i;
                    break;
                case OpExp.Minus:
                    result = it1.i - it2.i;
                    break;
                case OpExp.Times:
                    result = it1.i * it2.i;
                    break;
                case OpExp.Div:
                    result = it1.i / it2.i;
                    break;
            }
            return new IntAndTable(result, it2.t);
        }
    }

    public static void main(final String args[]) throws java.io.IOException {
        // Interpretation des Ausdrucks
        System.out.println("[SLP]Interpretation des Programms: ");
        interp(Program.prog);
    }
}

class Table {
    String id;
    int value;
    Table tail;

    Table(final String i, final int v, final Table t) {
        id = i;
        value = v;
        tail = t;
    }

    int lookup(final String key) {
        if (id.equals(key)) {
            return value;
        }
        return tail.lookup(key);
    }
}

class IntAndTable {
    int i;
    Table t;

    IntAndTable(final int ii, final Table tt) {
        i = ii;
        t = tt;
    }
}
