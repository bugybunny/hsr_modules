public class DynamicProgrammingKnapsack {
    public static int[][] valTable;

    public static void main(String[] args) {
        int[] values = { 1, 4, 5, 1 };
        int[] weights = { 1, 3, 4, 2 };
        knapsack(5, values, weights);
    }

    public static void knapsack(int capacity, int[] v, int[] w) {
        int items = v.length;
        valTable = new int[items + 1][capacity + 1];
        for (int i = 1; i <= items; i++) {
            int vi = v[i - 1];
            int wi = w[i - 1];
            for (int j = 1; j <= capacity; j++) {
                int rest = j - w[i - 1];
                if (rest >= 0) {
                    valTable[i][j] = Math.max(valTable[i - 1][j],
                            valTable[i - 1][rest] + v[i - 1]);
                } else {
                    valTable[i][j] = valTable[i - 1][j];
                }
            }
        }

        for (int i = 0; i < valTable.length; i++) {
            for (int j = 0; j < valTable[i].length; j++) {
                System.out.print(valTable[i][j] + ",");
            }
            System.out.println();
        }
    }
}
