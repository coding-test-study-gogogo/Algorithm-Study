package week17.olsohee;

import java.util.*;

class n_1_카드게임 {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        Set<Integer> myCard = new HashSet<>();
        Set<Integer> additionalCard = new HashSet<>();

        for (int i = 0; i < n / 3; i++) {
            myCard.add(cards[i]);
        }

        // 게임 시작
        int round = 1;
        int idx = n / 3;
        while (true) {

            // 뽑을 카드가 없으면 종료
            if (idx == n) {
                break;
            }

            // 1. 카드 뭉치에서 2개 뽑기
            additionalCard.add(cards[idx]);
            additionalCard.add(cards[idx + 1]);

            // 2. 카드 내기
            // 2-1. myCard에서 2장
            boolean giveCard = false;
            for (int card : myCard) {
                if (myCard.contains(n + 1 - card)) {
                    giveCard = true;
                    myCard.remove(card);
                    myCard.remove(n + 1 - card);
                    break;
                }
            }

            // 2-2. myCard에서 1장 + additionalCard에서 1장
            if (!giveCard) {
                for (int card : myCard) {
                    if (additionalCard.contains(n + 1 - card)) {
                        giveCard = true;
                        myCard.remove(card);
                        additionalCard.remove(n + 1 - card);
                        coin--;
                        break;
                    }
                }
            }


            // 2-3. additionalCard에서 2장
            if (!giveCard) {
                for (int card : additionalCard) {
                    if (additionalCard.contains(n + 1 - card)) {
                        giveCard = true;
                        additionalCard.remove(card);
                        additionalCard.remove(n + 1 - card);
                        coin -= 2;
                        break;
                    }
                }
            }

            // 낼 카드가 없으면 종료 (낼 카드가 있더라도 coin 범위 이상으로 새로운 카드를 뽑으면 종료)
            if (!giveCard || coin < 0) {
                break;
            }

            idx += 2;
            round++;
        }

        return round;
    }
}
