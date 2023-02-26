package QuestionSolutions.Question8A;

public class MaxSquareArea {


        public static int getMaxSquareArea(int[][] inputMatrix) {
            int rows = inputMatrix.length;
            int cols = inputMatrix[0].length;
            int[][] dpMatrix = new int[rows][cols];
            int maxArea = 0;

            // initialize first row and first column of dp matrix
            for (int i = 0; i < rows; i++) {
                dpMatrix[i][0] = inputMatrix[i][0];
                maxArea = Math.max(maxArea, dpMatrix[i][0]);
            }
            for (int j = 0; j < cols; j++) {
                dpMatrix[0][j] = inputMatrix[0][j];
                maxArea = Math.max(maxArea, dpMatrix[0][j]);
            }

            // fill remaining cells of dp matrix
            for (int i = 1; i < rows; i++) {
                for (int j = 1; j < cols; j++) {
                    if (inputMatrix[i][j] == 1) {
                        dpMatrix[i][j] = 0;
                    } else {
                        dpMatrix[i][j] = Math.min(dpMatrix[i - 1][j - 1], Math.min(dpMatrix[i - 1][j], dpMatrix[i][j - 1])) + 1;
                        maxArea = Math.max(maxArea, dpMatrix[i][j]);
                    }
                }
            }

            return maxArea * maxArea;
        }

        public static void main(String[] args) {
            int[][] matrix = {{1, 0, 1, 0, 0},
                    {0, 1, 1, 1, 1},
                    {0, 0, 0, 0, 1},
                    {0, 0, 0, 1, 1},
                    {0, 1, 0, 1, 1}};
            int maxSquareArea = getMaxSquareArea(matrix);
            System.out.println("Maximum area of square made by 0s: " + maxSquareArea);
        }
    }


