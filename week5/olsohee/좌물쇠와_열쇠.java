package week5.olsohee;

import java.util.*;

class 좌물쇠와_열쇠 {

    int m, n;
    int[][] key;
    int[][] lock;
    int[][] newLock;

    public boolean solution(int[][] key, int[][] lock) {

        this.key = key;
        this.lock = lock;
        m = key.length;
        n = lock.length;

        newLock = new int[(m - 1) * 2 + n][(m - 1) * 2 + n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newLock[i + (m - 1)][j + (m - 1)] = lock[i][j];
            }
        }

        for (int rotateCnt = 0; rotateCnt < 4; rotateCnt++) {
            if (rotateCnt > 0) rotateKey();

            for (int i = 0; i < n + m - 1; i++) {
                for (int j = 0; j < n + m - 1; j++) {
                    boolean result = matching(i, j);
                    if (result) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean matching(int y, int x) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int yLocation = m - 1 + i;
                int xLocation = m - 1 + j;
                if (yLocation >= y && yLocation < y + m && xLocation >= x && xLocation < x + m) {
                    if (newLock[yLocation][xLocation] == key[yLocation - y][xLocation - x]) {
                        return false;
                    }
                } else {
                    if (newLock[yLocation][xLocation] == 0) return false;
                }
            }
        }

        return true;
    }

    private void rotateKey() {
        int[][] newKey = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                newKey[i][j] = key[m - j - 1][i];
            }
        }
        key = newKey;
    }
}
