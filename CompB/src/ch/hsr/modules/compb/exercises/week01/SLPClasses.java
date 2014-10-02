package ch.hsr.modules.compb.exercises.week01;

/*
 * Straight-Line Program Interpreter
 * Grammar:
 * Stm = Stm; Stm				// Composition
 * Stm = id := Expr				// AssignStm 
 * Stm = print(ExpList)			// PrintStm
 * Exp = id						// IdExp
 * Exp = num					// NumExp
 * Exp = Exp Binop Exp			// OpExp
 * Exp = (Stm, Stm)				// EseqExp
 * ExpList = Exp, ExpList		// PairExpList
 * ExpList = Exp				// LastExpList
 * Binop = +					// Addition / Plus
 * Binop = .					// Minus
 * Binop = *					// Times
 * Binop = /					// Division
 */
abstract class Stm {
}

class CompoundStm extends Stm {
    Stm stm1, stm2;

    CompoundStm(final Stm s1, final Stm s2) {
        stm1 = s1;
        stm2 = s2;
    }

    @Override
    public String toString() {
        return stm1 + "; " + stm2;
    }

}

class AssignStm extends Stm {
    String id;
    Exp exp;

    AssignStm(final String i, final Exp e) {
        id = i;
        exp = e;
    }

    @Override
    public String toString() {
        return id + " = " + exp;
    }
}

class PrintStm extends Stm {
    ExpList exps;

    PrintStm(final ExpList e) {
        exps = e;
    }

    @Override
    public String toString() {
        return exps + "";
    }

}

abstract class Exp {
}

// Exp = id // IdExp
class IdExp extends Exp {
    String id;

    IdExp(final String i) {
        id = i;
    }

    @Override
    public String toString() {
        return id;
    }

}

class NumExp extends Exp {
    int num;

    NumExp(final int n) {
        num = n;
    }

    @Override
    public String toString() {
        return num + "";
    }

}

class OpExp extends Exp {
    Exp left, right;
    int oper;
    final static int Plus = 1, Minus = 2, Times = 3, Div = 4;

    OpExp(final Exp l, final int o, final Exp r) {
        left = l;
        oper = o;
        right = r;
    }

    @Override
    public String toString() {
        char opChar = '?';
        switch (oper) {
            case Plus:
                opChar = '+';
                break;
            case Minus:
                opChar = '-';
                break;
            case Times:
                opChar = '*';
                break;
            case Div:
                opChar = '/';
                break;
            default:
                opChar = '?';
                break;
        }
        return left + "" + opChar + "" + right;
    }
}

class EseqExp extends Exp {
    Stm stm;
    Exp exp;

    EseqExp(final Stm s, final Exp e) {
        stm = s;
        exp = e;
    }

    @Override
    public String toString() {
        return "(" + stm + ", " + exp + ")";
    }

}

abstract class ExpList {
}

class PairExpList extends ExpList {
    Exp head;
    ExpList tail;

    public PairExpList(final Exp h, final ExpList t) {
        head = h;
        tail = t;
    }

    @Override
    public String toString() {
        return head + ", " + tail;
    }

}

class LastExpList extends ExpList {
    Exp head;

    public LastExpList(final Exp h) {
        head = h;
    }

    @Override
    public String toString() {
        return head + "";
    }
}
