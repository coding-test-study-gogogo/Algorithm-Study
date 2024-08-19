package week6.olsohee;

import java.util.*;

class 다단계_칫솔_판매 {

    String[] enroll;
    int[] answer;
    int[] parent;
    Map<String, Integer> idxMapByName = new HashMap<>();

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        this.enroll = enroll;
        answer = new int[enroll.length];
        parent = new int[enroll.length];

        for (int i = 0; i < enroll.length; i++) {
            idxMapByName.put(enroll[i], i);
        }

        for (int i = 0; i < referral.length; i++) {
            parent[i] = (referral[i].equals("-")) ? -1 : idxMapByName.get(referral[i]);
        }

        // 수익금 계산
        for (int i = 0; i < seller.length; i++) {
            dfs(idxMapByName.get(seller[i]), amount[i] * 100);
        }

        return answer;
    }

    private void dfs(int idx, int money) {
        int parentMoney = (int)(money * 0.1);


        if (parent[idx] == -1) {
            answer[idx] += (parentMoney == 0) ? money : money - parentMoney;
            return;
        }

        if (parentMoney == 0) {
            answer[idx] += money;
        } else {
            answer[idx] += money - parentMoney;
            dfs(parent[idx], parentMoney);
        }
    }
}
