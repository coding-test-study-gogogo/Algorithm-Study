package week5.AlmondBreez3;

class 좌물쇠와열쇠 {
    public boolean solution(int[][] key, int[][] lock) {
        // 90도씩 회전하며 시도
        for(int i = 0; i < 4; i++) {
            if(i > 0) key = rotate(key); // 열쇠를 회전시킴
            // 열쇠를 자물쇠 위에서 가능한 모든 위치로 이동시킴
            for(int x = -(lock.length - 1); x < lock.length; x++) {
                for(int y = -(lock.length - 1); y < lock.length; y++) {
                    // 맞는지 확인
                    if(isCorrect(key, lock, x, y)) return true;
                }
            }
        }
        return false; // 맞는 경우가 없으면 false 반환
    }

    // 열쇠를 90도 회전시키는 메서드
    int[][] rotate(int[][] key) {
        int[][] result = new int[key.length][key[0].length];
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key[0].length; j++) {
                result[i][j] = key[key.length - 1 - j][i];
            }
        }
        return result;
    }

    // 열쇠가 현재 위치에서 자물쇠에 맞는지 확인하는 메서드
    boolean isCorrect(int[][] key, int[][] lock, int x, int y) {
        for(int i = 0; i < lock.length; i++) {
            for(int j = 0; j < lock.length; j++) {
                // 열쇠가 자물쇠 범위 바깥인 경우
                if(i + x < 0 || i + x >= key.length || j + y < 0 || j + y >= key.length) {
                    if(lock[i][j] == 0) return false; // 자물쇠의 홈이 채워지지 않음
                } else {
                    // 열쇠와 자물쇠의 돌기나 홈이 겹치는 경우
                    if(lock[i][j] == key[i + x][j + y]) return false;
                }
            }
        }
        return true; // 맞는 경우
    }
}
