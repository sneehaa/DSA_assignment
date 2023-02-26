package QuestionSolutions;

import java.util.ArrayList;
import java.util.List;

public class Question8B {
    public static int findKthMissingEvenNumber(int[] inputArray, int kthMissing) {
        List<Integer> missingEvens = new ArrayList<>();
        int j = 0;
        for (int i = inputArray[0]; i < inputArray[inputArray.length - 1]; i += 2) {
            if (j < inputArray.length && inputArray[j] == i) {
                j++;
                continue;
            }
            missingEvens.add(i);
            if (missingEvens.size() == kthMissing) {
                return i;
            }
        }
        return inputArray[inputArray.length - 1] + 2 * kthMissing;
    }

    public static void main(String[] args) {
        int[] inputArray = {0, 2, 6, 18, 22};
        int kthMissing = 6;
        System.out.println(findKthMissingEvenNumber(inputArray, kthMissing));
    }
}
