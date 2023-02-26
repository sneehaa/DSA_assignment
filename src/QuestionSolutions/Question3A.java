package QuestionSolutions;

import java.util.Arrays;

public class Question3A {
    public static int calculateMinProductDifference(int[] nums) {
        Arrays.sort(nums); // sort the input array
        int length = nums.length;
        int minDiff = Integer.MAX_VALUE;
        // calculate the difference between two subarrays with the minimum product difference
        for (int i = 0; i < length / 2; i++) {
            int product1 = nums[i] * nums[length - i - 1];
            int product2 = nums[length / 2 + i] * nums[length - length / 2 - i - 1];
            int diff = Math.abs(product1 - product2);
            minDiff = Math.min(minDiff, diff);
        }
        return minDiff;
    }

    public static void main(String[] args) {
        int[] nums = {5, 2, 4, 11};
        System.out.println(calculateMinProductDifference(nums)); // outputs 2
    }
}
