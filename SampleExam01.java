package org.codeboy;

public class SampleExam01 {

    public static int solution(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) {
            return 0;
        }

        int n = A.length;
        int m = A[0].length;


        for (int i = 1; i < n; i++) {
            if (A[i].length != m) {
                throw new IllegalArgumentException("All rows must have the same length.");
            }
        }


        int[] rowSums = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rowSums[i] += A[i][j];
            }
        }


        int[] colSums = new int[m];
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                colSums[j] += A[i][j];
            }
        }

        int count = 0;

        for (int p = 0; p < n; p++) {
            for (int q = 0; q < m; q++) {
                int topSum = 0, bottomSum = 0, leftSum = 0, rightSum = 0;

                for (int i = 0; i < p; i++) {
                    topSum += rowSums[i];
                }

                for (int i = p + 1; i < n; i++) {
                    bottomSum += rowSums[i];
                }

                for (int j = 0; j < q; j++) {
                    leftSum += colSums[j];
                }

                for (int j = q + 1; j < m; j++) {
                    rightSum += colSums[j];
                }

                if (topSum == bottomSum && leftSum == rightSum) {
                    count++;
                }
            }
        }

        return count;
    }
}



