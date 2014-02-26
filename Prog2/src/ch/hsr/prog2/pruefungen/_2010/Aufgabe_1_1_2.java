package ch.hsr.prog2.pruefungen._2010;

public class Aufgabe_1_1_2 {
    enum Tag {
        MO, DI, MI, DO, FR, SA, SO
    };

    public static void main(String[] args) {
        Tag tag = Tag.MO;
        report(tag);
        Tag nTag = Tag.valueOf("SA");

    }

    public static void report(Tag wd) {
        switch (wd) {
            case DI:
                break;
            case DO:
                break;
            case FR:
                break;
            case MI:
                break;
            case MO:
                break;
            case SA:
                break;
            case SO:
                break;
            default:
                break;

        }
    }

}
