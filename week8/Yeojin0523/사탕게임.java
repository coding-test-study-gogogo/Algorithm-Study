package week8;
// 틀렸습니다??
import java.util.*;

public class 사탕게임 {

	public static char[][] bomboni;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		sc.nextLine();
		
		int maxBomboni = 0;
		bomboni = new char[N][N];

		for (int i = 0; i < N; i++) {
			String line = sc.nextLine(); // char 쓸라면 이렇게 해야돼 
			bomboni[i] = line.toCharArray();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 오른쪽교환
				if (j < N - 1) {
					swap(bomboni, i, j, i, j + 1);
					maxBomboni = Math.max(maxBomboni, countRowOrCols(bomboni));
					swap(bomboni, i, j, i, j + 1);
				}
				// 아래 교환
				if (i < N - 1) {
					swap(bomboni, i, j, i + 1, j);
					maxBomboni = Math.max(maxBomboni, countRowOrCols(bomboni));
					swap(bomboni, i, j, i + 1, j);
				}
			}
		}
		System.out.println(maxBomboni);

		sc.close();
	}

	public static void swap(char[][] bomboni, int x1, int x2, int y1, int y2) {
		char temp = bomboni[x1][y1];
		bomboni[x1][y1] = bomboni[x2][y2];
		bomboni[x2][y2] = temp;
	}

	public static int countRowOrCols(char[][] bomboni) {
		int count = 1, count_row, count_cols = 0;

		for (int i = 1; i < bomboni.length; i++) {
			count_row = 1;
			for (int j = 1; j < bomboni[i].length; j++) {
				if (bomboni[i][j] == (bomboni[i][j - 1])) {
					count_row++; // 이렇게 두면 하나 건너뛰고 같아도 count하자나
				} else
					count_row = 1;
				count = Math.max(count, count_row);
			}
		}
		for (int i = 1; i < bomboni.length; i++) {
			count_cols = 1;
			for (int j = 1; j < bomboni[i].length; j++) {
				if (bomboni[i][j] == (bomboni[i - 1][j])) {
					count_cols++;
				} else {
					count_cols = 1; // 로 둬야 연속적인 때만 count 가능
				}
			}
		}
		count = Math.max(count, count_cols);
		return count;
	}
}
