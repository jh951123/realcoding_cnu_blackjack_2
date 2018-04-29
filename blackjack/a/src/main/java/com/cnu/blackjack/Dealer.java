package com.cnu.blackjack;

import java.util.concurrent.ThreadLocalRandom;

public class Dealer {

    public int getDealerScore() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int score = random.nextInt(17, 25);
        System.out.println("dealer의 값은 : "+score);
        return score;
    }
}
