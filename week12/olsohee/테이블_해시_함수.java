package week12.olsohee;

import java.util.*;

class 테이블_해시_함수 {
    public int solution(int[][] data, int col, int row_begin, int row_end) {

        col--;
        row_begin--;
        row_end--;

        int copyCol = col;
        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[copyCol] == o2[copyCol]) {
                    return o2[0] - o1[0];
                }
                return o1[copyCol] - o2[copyCol];
            }
        });

        int answer = 0;

        for (int i = row_begin; i <= row_end; i++) {
            int sum = 0;
            int[] row = data[i];
            for (int value : row) {
                sum += value % (i + 1);
            }
            answer = answer ^ sum;
        }
        return answer;
    }
}
