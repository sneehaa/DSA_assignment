package QuestionSolutions;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Question7A {
    private static final int NUM_THREADS = 4;// number of threads to use

    public static void main(String[] args) {
        int n = 4; // size of matrices
        Random random = new Random();

        // create matrices X and Y with random values
        int[][] X = new int[n][n];
        int[][] Y = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                X[i][j] = random.nextInt();
                Y[i][j] = random.nextInt();
            }
        }

        // create a thread pool with NUM_THREADS threads
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);

        // create an array of Future objects to hold the results of each thread
        Future<Double>[][] futures = new Future[n][n];

        // multiply the matrices using threads
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                final int row = i;
                final int col = j;
                futures[i][j] = pool.submit(() -> {
                    double sum = 0;
                    for (int k = 0; k < n; k++) {
                        sum += X[row][k] * Y[k][col];
                    }
                    return sum;
                });
            }
        }

        // wait for all threads to finish and get the results
        double[][] Z = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                try {
                    Z[i][j] = futures[i][j].get();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        // shutdown the thread pool
        pool.shutdown();

        // print the result
        System.out.println("Result:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(Z[i][j] + " ");
            }
            System.out.println();
        }
    }


}