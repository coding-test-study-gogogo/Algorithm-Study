package week15.olsohee;

class 숫자_카드_나누기 {

    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;

        // 각 집합의 최대 공약수 찾은 후, 상대 집합을 나눌 수 없는지 확인
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];
        for (int i = 0; i < arrayA.length; i++) {
            gcdA = findGcd(gcdA, arrayA[i]);
            gcdB = findGcd(gcdB, arrayB[i]);
        }

        if (notDivisible(gcdA, arrayB)) {
            answer = Math.max(answer, gcdA);
        }

        if (notDivisible(gcdB, arrayA)) {
            answer = Math.max(answer, gcdB);
        }

        return answer;
    }

    private int findGcd(int n1, int n2) {
        // n1 <= n2로 만들기
        if (n2 < n1) {
            int temp = n1;
            n1 = n2;
            n2 = temp;
        }

        if (n2 % n1 == 0) return n1;
        return findGcd(n1, n2 % n1);
    }

    private boolean notDivisible(int n, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] % n == 0) return false;
        }
        return true;
    }
}
