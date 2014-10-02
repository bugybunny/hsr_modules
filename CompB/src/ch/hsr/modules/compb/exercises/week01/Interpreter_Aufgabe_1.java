package ch.hsr.modules.compb.exercises.week01;

class Interpreter_Aufgabe_1 {
    static int maxargs(final Stm s) {
        if (s instanceof CompoundStm) {
            final CompoundStm stm = (CompoundStm) s;
            return Math.max(maxargs(stm.stm2), maxargs(stm.stm1));
        } else if (s instanceof AssignStm) {
            final AssignStm stm = (AssignStm) s;
            return maxargs(stm.exp);
        } else {
            final PrintStm stm = (PrintStm) s;
            return Math.max(length(stm.exps), maxargs(stm.exps));
        }
    }

    private static int length(final ExpList es) {
        if (es instanceof PairExpList) {
            final PairExpList exps = (PairExpList) es;
            return 1 + length(exps.tail);
        } else {
            return 1;
        }
    }

    private static int maxargs(final Exp e) {
        if (e instanceof IdExp) {
            return 0;
        } else if (e instanceof NumExp) {
            return 0;
        } else if (e instanceof EseqExp) {
            final EseqExp ex = (EseqExp) e;
            return Math.max(maxargs(ex.stm), maxargs(ex.exp));
        } else {
            final OpExp ex = (OpExp) e;
            return Math.max(maxargs(ex.left), maxargs(ex.right));
        }
    }

    private static int maxargs(final ExpList es) {
        if (es instanceof PairExpList) {
            final PairExpList pex = (PairExpList) es;
            return Math.max(maxargs(pex.head), maxargs(pex.tail));
        } else {
            final LastExpList les = (LastExpList) es;
            return maxargs(les.head);
        }
    }

    public static void main(final String args[]) throws java.io.IOException {
        // Ausgabe der Anzahl Argumente
        System.out.println("[SLP]Anzahl Parameter im print-Statement: "
                + maxargs(Program.prog));
    }
}
