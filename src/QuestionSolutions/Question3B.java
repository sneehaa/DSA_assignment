package QuestionSolutions;

import java.util.Scanner;

class Question3B {
    public static boolean patternMatch(String inputStr, String pattern) {
        int inputIndex = 0;
        int patternIndex = 0;
        while (inputIndex < inputStr.length() && patternIndex < pattern.length()) {
            if (pattern.charAt(patternIndex) == '@') {
                return true;
            } else if (pattern.charAt(patternIndex) == '#' && inputIndex < inputStr.length()) {
                inputIndex++;
                patternIndex++;
            } else if (inputStr.charAt(inputIndex) == pattern.charAt(patternIndex)) {
                inputIndex++;
                patternIndex++;
            } else {
                return false;
            }
        }
        return inputIndex == inputStr.length() && patternIndex == pattern.length();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter input string: ");
        String inputStr = scanner.nextLine();

        System.out.print("Enter pattern string: ");
        String pattern = scanner.nextLine();

        boolean isMatch = patternMatch(inputStr, pattern);
        System.out.println("Pattern match result: " + isMatch);
    }

    //given cases in question
    //Input: String inputStr = "tt", pattern ="@"
    //Output: true
    //Input: String inputStr = "ta", pattern ="t"
    //Output: false
    //Input: String inputStr = "ta", pattern ="t#"
    //Output: true
}
