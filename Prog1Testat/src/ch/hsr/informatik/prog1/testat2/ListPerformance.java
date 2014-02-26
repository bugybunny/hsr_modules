package ch.hsr.informatik.prog1.testat2;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListPerformance {
    private static NumberFormat nf = DecimalFormat.getInstance();

    // just invoke all methods in the ListPerformanceTest class that are public
    // static. Bad code but saves a lot of c&p and is easy extendable for
    // further comparisons
    public static void measurePerformance(List<Method> someMethodsToPerform) {

        // init with 100000, so only the operation is really measured and not
        // also the expensive copying
        List<Integer> arrayList = new ArrayList<>(100000);
        List<Integer> linkedList = new LinkedList<>();

        System.out.println("method\t\tArrayList(ns)\t\tLinkedList (ns)");
        System.out
                .println("——————————————————————————————————————————————————");

        try {
            for (Method tempMethodToPerform : someMethodsToPerform) {
                ListPerformanceTests.init(arrayList);
                ListPerformanceTests.init(linkedList);
                long startArrayList = System.nanoTime();
                tempMethodToPerform.invoke(null, arrayList);
                long endArrayList = System.nanoTime();

                long startLinkedList = System.nanoTime();
                tempMethodToPerform.invoke(null, linkedList);
                long endLinkedList = System.nanoTime();

                long arrayListTime = endArrayList - startArrayList;
                long linkedListTime = endLinkedList - startLinkedList;

                String smallerOrGreater = arrayListTime > linkedListTime ? ">"
                        : "<";

                System.out.println(tempMethodToPerform.getName() + "\t"
                        + nf.format(arrayListTime) + "\t" + smallerOrGreater
                        + "\t\t" + nf.format(linkedListTime));
            }
        }
        catch (Exception anEx) {
            anEx.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Method[] methods = ListPerformanceTests.class.getMethods();
        List<Method> methodsToPerformList = new ArrayList<>(methods.length - 1);
        for (Method tempMethod : methods) {
            if (Modifier.isPublic(tempMethod.getModifiers())
                    && Modifier.isStatic(tempMethod.getModifiers())) {
                methodsToPerformList.add(tempMethod);
            }
        }
        measurePerformance(methodsToPerformList);
    }
}
