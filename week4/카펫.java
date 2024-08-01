package week4;
import java.util.*;
class 카펫 {
    public static int[][] arr;
    public int[] solution(int brown, int yellow) {
        int total = brown + yellow;

        a + a + b - 2+ b-2 = brown;
        2a + 2b - 4 = brown;
        2(a+b) - 4 =brown;

        int[] answer = new int[2];
        for (int i = 3; i <= total; i++) {
            int col = i;
            int row = total / col;

            if (row < 3) {
                continue;
            }

            if (row >= col) {
                if ((row-2) * (col-2) == yellow) {
                    answer[1] = col;
                    answer[0] = row;
                    break;
                }
            }
        }


        return answer;
    }
}