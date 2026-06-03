public class HDFCNetBankingBIT_CO2 {

    static int[] bit;
    static int n;

    static void update(int index, int value) {
        while (index <= n) {
            bit[index] += value;
            index += index & (-index);
        }
    }

    static int prefixSum(int index) {
        int sum = 0;

        while (index > 0) {
            sum += bit[index];
            index -= index & (-index);
        }

        return sum;
    }

    static int rangeSum(int left, int right) {
        return prefixSum(right) - prefixSum(left - 1);
    }

    public static void main(String[] args) {

        int[] spend = {
            0,
            1200, 800, 0, 2400, 1500,
            600, 0, 0, 3500, 0,
            1100, 950, 700, 0
        };

        n = spend.length - 1;
        bit = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            update(i, spend[i]);
        }

        System.out.println("=== HDFC NetBanking Daily Spend Analysis ===");

        System.out.println("\nDaily Spending Data:");
        for (int i = 1; i <= n; i++) {
            System.out.println("Day " + i + " = Rs." + spend[i]);
        }

        int prefix12 = prefixSum(12);
        int prefix4 = prefixSum(4);
        int totalSpend = rangeSum(5, 12);

        System.out.println("\nRange Query: Jan 5 to Jan 12");

        System.out.println("Prefix Sum(12) = " + prefix12);
        System.out.println("Prefix Sum(4) = " + prefix4);

        System.out.println("\nTotal Spend (Jan 5 - Jan 12) = Rs." + totalSpend);

        System.out.println("\nFenwick Tree Operations Completed Successfully");
    }
}